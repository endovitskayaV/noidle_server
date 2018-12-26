<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>NoIdle&bull;Add team</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/custom.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>

<!--  Scripts-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/collapsible.js"></script>
<script type="text/javascript" src="js/editMembers.js"></script>
<script type="text/javascript" src="js/modal.js"></script>
<script type="text/javascript" src="js/editTeam.js"></script>
 <#include "nav_auth.ftl">

<main>
    <div class="container">

        <h2 class="header center orange-text">Teams</h2>

         <#if teams?size==0>

        <a href="/teams/add">
            <div class="center light-blue-text">
                <i class="material-icons" style="font-size: 40px">add</i>
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
        <a href="/teams/add">
            <div class="center light-blue-text">
                <i class="material-icons" style="font-size: 30px">add</i>
            </div>
        </a>
             <#assign i=0>

        <div class="container">

             <#list teams as team>

                 <ul class="collapsible popout expandable">
                     <li>
                         <div class="collapsible-header">

                 <#if  i%2==0>
                 <div class="center light-blue-text"><i class="material-icons">supervised_user_circle</i></div>
                 <#else>
                     <div class="center orange-text"><i class="material-icons">supervised_user_circle</i></div>
                 </#if>
                             <span class="truncate" id="team_name${i}"> ${team.name}</span>
                             <div class="row"></div>
                             <div>
                                 <a onclick="openEditTeamModal('${team.id}', 'team_name${i}')">
                                     <i class="material-icons faded">edit</i>
                                 </a>
                             </div>
                             <div id="delete${i}"><i class="material-icons faded">delete</i></div>


                         </div>
                         <div class="collapsible-body">
                             <div class="chips" id="chips${i}">
                             </div>
                         </div>
                         <script>
                             $('#chips${i}').chips({
                                 placeholder: 'Enter member name',
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
                                     onChipDeleteHandler(data, '${team.id}')
                                 }
                             });
                         </script>
                     </li>
                 </ul>
                 <#assign i=i+1>
             </#list>
        </div>
         </#if>
    </div>


    <!-- Modal Structure -->
    <div class="modal">
        <form method="post" action="/teams/edit">
            <div class="modal-content">
                <input type="text" id="team_key" hidden>
                <input type="text" id="team_id" name="id" hidden>
                <div class="input-field col s8">
                    <input class="input-field" type="text" id="team_name" name="name" required
                           minlength="2" maxlength="120" pattern="\S([\s*\w*]|[\w*\s*])*\S"
                           title="Not allowed: spaces in the beginning and end, blank names">
                    <span class="helper-text">Team name</span>
                </div>
            </div>
            <div class="modal-footer">
                <button class="waves-effect waves-orange btn-flat" type="submit" onclick="editTeamHandler()">Ok</button>
                <button class="waves-effect waves-orange btn-flat" type="reset">Reset</button>
                <button class="modal-close waves-effect waves-orange btn-flat" type="reset">Exit</button>
            </div>
        </form>
    </div>

</main>

 <#include "footer_auth.ftl">

</body>
</html>