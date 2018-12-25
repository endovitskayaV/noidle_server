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
            url: document.location.origin + '/users/' + userId + '/teams/add/'+teamId,
            type: "POST",
            statusCode: {
                204: function () {
                    M.toast({
                        html: 'Member&nbsp;<i><b>' + memberName + '</b></i>&nbsp;added',
                        classes: 'rounded'
                    })
                },
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

function onChipDeleteHandler(data) {
    var memberName = data.childNodes[0].data;
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

        $.ajax({
            url: document.location.origin + '/users/' + userId + '/teams/remove/'+teamId,
            type: "POST",
            statusCode: {
                204: function () {
                    M.toast({
                        html: 'Member&nbsp;<i><b>' + memberName + '</b></i>&nbsp;removed',
                        classes: 'rounded'
                    })
                },
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