function deleteTeam(teamId, teamName) {
    $.post(document.location.origin + '/teams/delete/' + teamId).always(
        function () {
            var card = $("#" + teamId + "-card");
            card.remove();

            var toastHTML = 'Team&nbsp;<i><b>' + teamName + '</b></i>&nbsp;removed' +
                '<button class="btn-flat toast-action" onclick="doAddTeam(\'' + teamName + '\');">Undo</button>';

            M.toast({
                html: toastHTML,
                classes: 'rounded'
            });

            if ($('#teams-container')[0].innerText.length === 0) {
                $('#teams-not-empty-layout').remove();
                $('#no-team').append('  <a href="/teams/add">\n' +
                    '            <div class="center light-blue-text">\n' +
                    '                <a onclick="openAddTeamModal()" class="link">\n' +
                    '                    <i class="material-icons" style="font-size: 40px">add</i>\n' +
                    '                </a>\n' +
                    '            </div>\n' +
                    '        </a>\n' +
                    '\n' +
                    '        <div class="container">\n' +
                    '            <div class="container">\n' +
                    '                <div class="container">\n' +
                    '                    <div class="card horizontal">\n' +
                    '                        <div class="card-stacked">\n' +
                    '                            <div class="card-content">\n' +
                    '                                <p class="center-align"><span>No teams yet</span></p>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>')
            }
        }
    )
}