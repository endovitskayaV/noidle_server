function openAddTeamModal() {
    $('#add-modal').modal('open');
}

function addTeamHandler() {
    var teamNameInput = $("#add_team_name")[0];
    var valid = teamNameInput.validity.valid;
    var n = teamNameInput.value;
    if (valid) {

        $.ajax({
            url: '/teams/add/',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({name: teamNameInput.value}),
            statusCode: {
                400: function () {
                    alert("sdf")
                },
                200: function (data) {
                    var team = data.teamDto;
                    var i = data.overall;

                    var div = $("#teams-container");

                    var old = div.innerHTML;
                    div.html = '';

                    var color;
                    var hint="";
                    if (blueColor) {
                        color = "light-blue-text"
                    } else {
                        hint=" o";
                        color = "orange-text"
                    }

                   var text ="<ul class=\"collapsible popout expandable"+hint+"\" id=\"" + team.id + "-card\">\n" +
                        "                     <li>\n" +
                        "                         <div class=\"collapsible-header\">\n"+
                        "                 <div class=\"center " + color + "\"><i class=\"material-icons\">supervised_user_circle</i></div>\n" +
                        "                             <span class=\"truncate\" id=\"team_name" + i + "\"> " + team.name + "</span>\n" +
                        "                             <div class=\"row\"></div>\n" +
                        "                             <div>\n" +
                        "                                 <a onclick=\"openEditTeamModal('" + team.id + "', 'team_name" + i + "')\">\n" +
                        "                                     <i class=\"material-icons faded\">edit</i>\n" +
                        "                                 </a>\n" +
                        "                             </div>\n" +
                        "                             <div>\n" +
                        "                                 <a onclick=\"deleteTeam('" + team.id + "')\">\n" +
                        "                                     <i class=\"material-icons faded\">delete</i>\n" +
                        "                                 </a>\n" +
                        "                             </div>\n" +
                        "                         </div>\n" +
                        "                         <div class=\"collapsible-body\">\n" +
                        "                             <div class=\"chips\" id=\"chips" + i + "\"></div>\n" +
                        "                         </div>\n" +
                        "                         <script>\n" +
                        "                             $('#chips" + i + "').chips({\n" +
                        "                                 placeholder: 'Members` names',\n" +
                        "                                 secondaryPlaceholder: '+Member',\n" +
                        "                                 onChipAdd: function (e, data) {\n" +
                        "                                     onChipAddHandler(data, document.querySelector('#chips" + i + "'), '" + team.id + "')\n" +
                        "                                 },\n" +
                        "                                 onChipDelete: function (e, data) {\n" +
                        "                                     onChipDeleteHandler(data, '" + i + "', '" + team.id + "')\n" +
                        "                                 }\n" +
                        "                             });\n" +
                        "                         </script>\n" +
                        "                     </li>\n" +
                        "                 </ul>";
                    div.append(text);
                    div.append(old);
                    teamNameInput.val = "";
                    $('#add-modal').modal('close');
                }
            }
        })
    }
}