var afterAdd = false;
var undoDone = false;

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

function doDelete(data, teamId, memberName) {
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
    undoDone = true;
    M.Toast.getInstance(document.querySelector('.toast')).dismiss();
    M.Chips.getInstance(document.querySelector('#chips' + i)).addChip({tag: memberName});
}

function onChipDeleteHandler(data, i, teamId) {
    var memberName = data.childNodes[0].data;

    if (afterAdd) {
        afterAdd = false;
        doDelete(data, teamId, memberName);

    } else {
        var toastHTML = '<span>Member&nbsp;<i><b>' + memberName + '</b></i>&nbsp;excluded</span>' +
            '<button class="btn-flat toast-action" onclick="undoDelete(' + i + ',\'' + memberName + '\');">Undo</button>';

        M.toast({
            html: toastHTML,
            classes: 'rounded',
            completeCallback: function () {
                if (undoDone) {
                    undoDone = false;
                } else {
                    doDelete(data, teamId, memberName);
                }
            }
        });
    }
}