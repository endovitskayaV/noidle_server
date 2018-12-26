var afterAdd = false;

function onChipAddHandler(data, elem, teamId) {
    var memberName = data.childNodes[0].data;
    var userId;

    $.get("/users?name=" + memberName).always(function (data) {
        userId = data.id;

        if (typeof userId === "undefined") {
            var instance = M.Chips.getInstance(elem);
            afterAdd = true;
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

function doDelete(teamId, memberName) {
    var userId;

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
    });
}

function undoDelete(i, memberName) {
    M.Chips.getInstance($('#chips' + i)).addChip({tag: memberName});
}

function onChipDeleteHandler(data, i, teamId) {
    var memberName = data.childNodes[0].data;
    doDelete(teamId, memberName);

    if (afterAdd) {
        afterAdd = false;
    } else {
        var toastHTML = '<span>Member&nbsp;<i><b>' + memberName + '</b></i>&nbsp;excluded</span>' +
            '<button class="btn-flat toast-action" onclick="undoDelete(' + i + ',\'' + memberName + '\');">Undo</button>';

        M.toast({
            html: toastHTML,
            classes: 'rounded'
        });
    }
}

function clearTeams() {
    $('[id$="-card"]').each(function (index, value) {
        var len = value.children[0].childNodes[3].firstElementChild.M_Chips.chipsData.length;
        if (len < 1) {
            var teamId = value.id.substring(0, value.id.length - 5);
            $.post(document.location.origin + '/teams/delete/' + teamId)
        }
    });
}