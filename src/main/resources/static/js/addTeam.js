function openAddTeamModal() {
    $('#add-modal').modal('open');
}

function addTeamHandler() {
    var teamNameInput = $("#add_team_name")[0];
    var valid = teamNameInput.validity.valid;
    var name = teamNameInput.value;
    if (valid) {
      doAddTeam(name)
    }
}

function  doAddTeam(name){
    $.ajax({
        url: '/teams/add/',
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({name: name}),
        statusCode: {
            400: function () {
                $('#add-modal').modal('close');
                M.toast({
                    html: 'Team&nbsp;<i><b>' + name + '</b></i>&nbsp;already exists',
                    classes: 'rounded'
                });
            },
            200: function () {
                window.location.href = document.location.origin + '/teams';
            }
        }
    })
}