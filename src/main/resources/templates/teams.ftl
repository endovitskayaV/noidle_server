<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <link rel="shortcut icon" href="img/icon.png" type="image/png">
    <title>NoIdle&nbsp;&bull;&nbsp;Teams</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/custom.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>

<!--  Scripts-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/collapsible.js"></script>
<script type="text/javascript" src="js/editMembers.js"></script>
<script type="text/javascript" src="js/modal.js"></script>
<script type="text/javascript" src="js/editTeam.js"></script>
<script type="text/javascript" src="js/deleteTeam.js"></script>
<script type="text/javascript" src="js/addTeam.js"></script>
<script>
    var currentUserName;
    document.addEventListener('DOMContentLoaded', function () {
        currentUserName = '${currentUser.name}';
    });
</script>
 <#include "nav_auth.ftl">

<main>
    <div class="container">

        <h2 class="header center orange-text">Teams</h2>
        <div id="no-team">
         <#if teams?size==0>

        <a href="/teams/add">
            <div class="center light-blue-text">
                <a onclick="openAddTeamModal()" class="link">
                    <i class="material-icons" style="font-size: 40px">add</i>
                </a>
            </div>
        </a>

        <div class="container">
            <div class="container">
                <div class="container">
                    <div class="card horizontal">
                        <div class="card-stacked">
                            <div class="card-content">
                                <p class="center-align"><span>No teams yet</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

         <#else>
<div id="teams-not-empty-layout">


          <a href="/teams/add">
              <div class="center light-blue-text">
                  <a onclick="openAddTeamModal()" class="link">
                      <i class="material-icons" style="font-size: 30px">add</i>
                  </a>
              </div>
          </a>
             <#assign i=0>
 <div class="container" id="teams-container">
             <#list teams as team>


                 <#if  i%2==0>

                 <ul class="collapsible popout expandable" id="${team.id}-card">
                     <li>
                         <div class="collapsible-header">
                 <div class="center light-blue-text"><i class="material-icons">supervised_user_circle</i></div>

                 <#else>
                 <ul class="collapsible popout expandable o" id="${team.id}-card">
                     <li>
                         <div class="collapsible-header">
                     <div class="center orange-text"><i class="material-icons">supervised_user_circle</i></div>
                 </#if>
                             <span class="truncate" id="team_name${i}"> ${team.name}</span>
                             <div class="row"></div>
                             <div>
                                 <a onclick="openEditTeamModal('${team.id}', 'team_name${i}')">
                                     <i class="material-icons faded">edit</i>
                                 </a>
                             </div>
                             <div>
                                 <a onclick="deleteTeam('${team.id}', '${team.name}', false)">
                                     <i class="material-icons faded">delete</i>
                                 </a>
                             </div>


                         </div>
                         <div class="collapsible-body">
                             <div class="chips" id="chips${i}"></div>
                         </div>
                         <script>
                             $('#chips${i}').chips({
                                 placeholder: 'Members` names',
                                 secondaryPlaceholder: '+Member',
                                 data: [
                                <#list team.users as user>
                                {tag: '${user.name}'},
                                </#list>
                                 ],
                                 onChipAdd: function (e, data) {
                                     onChipAddHandler(data, document.querySelector('#chips${i}'), '${team.id}')
                                 },
                                 onChipDelete: function (e, data) {
                                     onChipDeleteHandler(data, document.querySelector('#chips${i}'), '${i}', '${team.id}', '${team.name}')
                                 },
                                 onChipSelect: function (e, data) {
                                     var user = "";
                                     var userName = data.childNodes[0].data;
                                     if (currentUserName!=='undefined' && currentUserName !== userName) {
                                         user = "/" + userName.replace(/ /g, "_");
                                     }
                                     window.location.href = document.location.origin + '/achievements' + user;
                                 }
                             });
                         </script>
                     </li>
                 </ul>
                 <#assign i=i+1>
             </#list>
        </div>
         </#if>
        </div></div>

        </div>
        <!-- Modals-->
        <div class="modal" style="width: 50% !important;" id="edit-modal">
            <form action="" onsubmit="return false">
                <div class="modal-content">
                    <input type="text" id="team_key" hidden>
                    <input type="text" id="team_id" name="id" hidden>
                    <div class="input-field col s8">
                        <input class="input-field" type="text" id="team_name" name="name" required
                               minlength="2" maxlength="120" pattern="\S.*\S"
                               title="Not allowed: spaces in the beginning and end, blank names">
                        <span class="helper-text">Team name</span>
                    </div>

                </div>
                <div class="modal-footer">
                    <button class="waves-effect waves-orange btn-flat"
                            onclick="editTeamHandler()">Ok
                    </button>
                    <button class="modal-close waves-effect waves-orange btn-flat" type="reset">Exit</button>
                </div>
            </form>
        </div>


        <div class="modal" style="width: 50% !important;" id="add-modal">
            <form action="" onsubmit="return false">
                <div class="modal-content">
                    <div class="input-field col s8">
                        <input class="input-field" type="text" id="add_team_name" required
                               minlength="2" maxlength="120" pattern="\S.*\S"
                               title="Not allowed: spaces in the beginning and end, blank names">
                        <span class="helper-text">Team name</span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="waves-effect waves-orange btn-flat" onclick="addTeamHandler()">Ok</button>
                    <button class="modal-close waves-effect waves-orange btn-flat" type="reset">Exit</button>
                </div>
            </form>
        </div>

</main>

 <#include "footer_auth.ftl">

</body>
</html>