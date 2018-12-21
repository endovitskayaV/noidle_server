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
    <link href="css/input.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
<!--  Scripts-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/collapsible.js"></script>

 <#include "nav_auth.ftl">

<div class="row">
    <div class="col s12">
        <div class="row col s12">
            <div class="row col s3 offset-s4">
                <h2 class="header right orange-text">Teams</h2>
            </div>
            <div class="row col s2 offset-s1">
                <a href="/teams/add" class="btn-floating waves-effect waves-light red" style="margin-top: 17%">
                    <i class="material-icons">add</i></a>
            </div>
        </div>
        <div class="container">
             <#assign i=2>
             <#list teams as team>
            <div class="col s8 offset-s2">

                <ul class="collapsible popout expandable">
                    <li>
                        <div class="collapsible-header">
                 <#if  i%2==0>
                 <div class="center light-blue-text"><i class="material-icons">supervised_user_circle</i></div>
                 <#else>
                     <div class="center orange-text"><i class="material-icons">supervised_user_circle</i></div>
                 </#if>

                            ${team.name}</div>
                        <div class="collapsible-body">
                            <div class="chips" id="chips${i}">
                            </div>
                        </div>

                        <script>
                            <#--var arr = [-->
                                <#--// {tag:'test_user'}, {tag:'test_user2'},];-->
                            <#--<#list team.users as user>-->
                            <#--{tag: '${user.name}'},-->
                            <#--</#list>-->
                            <#--];-->
                            $('#chips${i}').chips({
                                placeholder: 'Enter user name',
                                secondaryPlaceholder: '+Member',
                                data: [
                                <#list team.users as user>
                                {tag: '${user.name}'},
                                </#list>
                                ]
                            });
                            <#--$.each(arr, function () {-->
                                        <#--M.Chips.getInstance(document.querySelector('#chips${i}')).addChip(this);-->
                                    <#--}-->
                            <#--);-->
                        </script>
                    </li>
                </ul>
            </div>
                 <#assign i=i+1>
             </#list>
        </div>
    </div>
</div>

 <#include "footer_bottom_auth.ftl">
</body>
</html>