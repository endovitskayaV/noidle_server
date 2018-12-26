function openEditTeamModal(id, key) {
    var teamNameSpan = $("#" + key);
    $("#team_name").val(teamNameSpan[0].innerText);
    $("#team_id").val(id);
    $("#team_key").val(key);
    $('#edit-modal').modal('open');
}

function editTeamHandler() {
    var teamNameInput = $("#team_name");
    var valid = teamNameInput[0].validity.valid;
    if (valid) {
        var teamKey = $("#team_key").val();
        var teamNameSpan = $("#" + teamKey);
        teamNameSpan[0].innerText = teamNameInput.val();
        $('#edit-modal').modal('close');
    }
}