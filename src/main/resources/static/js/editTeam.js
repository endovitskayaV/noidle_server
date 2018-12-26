function openEditTeamModal(id, key) {
    var teamNameSpan = $("#" + key);
    $("#team_name").val(teamNameSpan[0].innerText);
    $("#team_id").val(id);
    $("#team_key").val(key);
    $('#edit-modal').modal('open');
}

function editTeamHandler() {
    var teamNameInput = $("#team_name")[0];
    var id = $("#team_id")[0].value;
    var name = teamNameInput.value;
    var valid = teamNameInput.validity.valid;
    if (valid) {
        $.ajax({
            url: '/teams/edit/',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({id: id, name: name}),
            statusCode: {
                400: function () {
                    $('#edit-modal').modal('close');

                    M.toast({
                        html: 'Team&nbsp;<i><b>' + name + '</b></i>&nbsp;already exists',
                        classes: 'rounded'
                    });
                },
                204: function () {
                    //set team name in card
                    var teamKey = $("#team_key").val();
                    var teamNameSpan = $("#" + teamKey);
                    teamNameSpan[0].innerText = teamNameInput.value;

                    $('#edit-modal').modal('close');
                }
            }
        });
    }
}