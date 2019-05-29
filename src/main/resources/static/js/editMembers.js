function onChipAddHandler(data, elem, teamId) {
    var memberName = data.childNodes[0].data;
    var userId;

    $.get("/users?name=" + memberName).always(function (data) {
        userId = data.id;

        if (typeof userId === "undefined") {
            var instance = M.Chips.getInstance(elem);
            instance.deleteChip(instance.chipsData.length - 1);
            return;
        }

        $.ajax({
            url: document.location.origin + '/users/' + userId + '/teams/add/' + teamId,
            type: "POST",
            statusCode: {
                404: function () {
                    M.toast({
                        html: 'Member&nbsp;<i><b>' + memberName + '</b></i>&nbsp;not found',
                        classes: 'rounded'
                    })
                }
            }
        })
    });
}


function undoDeleteMember(i, memberName) {
    M.Chips.getInstance($('#chips' + i)).addChip({tag: memberName});
}

function onChipDeleteHandler(data, elem, i, teamId, teamName) {
    var memberName = data.childNodes[0].data;

    var userId;
    //-----------------------------------------do delete---------------------------------//
    $.get("/users?name=" + memberName).always(function (data) {
        userId = data.id;
        if (typeof userId === "undefined") {
            M.toast({
                html: 'Member&nbsp;<i><b>' + memberName + '</b></i>&nbsp;not found',
                classes: 'rounded'
            });
            return;
        }

        $.post(document.location.origin + '/users/' + userId + '/teams/remove/' + teamId)
        //-------------------------------------------------------------------------------//

            .always(function () {
                    var len = M.Chips.getInstance(elem).chipsData.length;
                    if (len < 1) {
                        deleteTeam(teamId, teamName, true, memberName);
                    } else {
                        var toastHTML = '<span>Member&nbsp;<i><b>' + memberName + '</b></i>&nbsp;excluded</span>' +
                            '<button class="btn-flat toast-action" onclick="undoDeleteMember(' + i + ',\'' + memberName + '\');">Undo</button>';

                        M.toast({
                            html: toastHTML,
                            classes: 'rounded'
                        });
                    }
            })
    });
}