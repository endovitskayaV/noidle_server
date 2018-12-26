
function deleteTeam(teamId) {
    $.post(document.location.origin + '/teams/delete/' + teamId).always(
        function () {
            var card = $("#" + teamId + "-card");
            card.remove();

            if ($('#teams-container')[0].innerText.length === 0) {
                window.location.href = document.location.origin + '/teams';
            }
        }
    )
}