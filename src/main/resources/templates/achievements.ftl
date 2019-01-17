<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <link rel="shortcut icon" href="img/icon.png" type="image/png">
    <title>NoIdle&nbsp;&bull;&nbsp;Dashboard</title>

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
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var elemsCol = document.querySelectorAll('.collapsible');
        var instancesCol = M.Collapsible.init(elemsCol);
        var elemsToolt = document.querySelectorAll('.tooltipped');
        var instancesToolt = M.Tooltip.init(elemsToolt);
    });
</script>
<#include "nav_auth.ftl">
<main>
    <br>
    <div class="container">
        <h2 class="header center orange-text">Achievements</h2>
        <br>
        <#if !levels?? && !extras?? && !teams??>

            <div class="container">
                <div class="container">
                    <div class="card horizontal">
                        <div class="card-stacked">
                            <div class="card-content">
                                <p class="center-align"><span>No achievements found</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        <#else>

            <#if levels?? && levels?size gt 0>
        <ul class="col s6 collapsible popout expandable">
            <li>
                <div class="collapsible-header">
                    <div class="light-blue-text"><i class="material-icons">sort</i></div>
                    <span>Levels</span>
                </div>

                <div class="collapsible-body">
                    <#list levels as level>
                        <#if level.requirements?? && level.requirements?size gt 0>
                        <p style="display:flex">
                            <span><i><b><u>${level.level}.&nbsp;${level.name}</u></b></i></span>
                            <#if level.achievedComments?? && level.achievedComments?size gt 0>
                                <#list  level.achievedComments as comment>
                                    <i class="tooltipped orange-text material-icons"
                                       data-position="top" data-tooltip="${comment}"
                                       style="margin-left: 5px">
                                        star</i>
                                </#list>
                            </#if>
                        </p>
                        <div style="margin-left: 20px">
                    <#list level.requirements as req>
                        <p style="display: flex">
                    <#if req.left?contains("time")>
                        <i class="light-blue-text material-icons">access_time</i>
                    <#elseif req.left?contains("symbols")>
                       <i class="orange-text material-icons">text_rotation_none</i>
                    <#elseif req.left?contains("executions") && req.left?contains("successful")>
                        <i class="green-text material-icons">play_arrow</i>
                    <#elseif req.left?contains("executions") && req.left?contains("failed")>
                        <i class="red-text material-icons">play_arrow</i>
                    <#elseif req.left?contains("commits") && req.left?contains("successful")>
                         <i class="green-text material-icons">done</i>
                    <#elseif req.left?contains("commits") && req.left?contains("failed")>
                         <i class="red-text material-icons">close</i>
                    <#else>
                         <i class="material-icons">keyboard</i>
                    </#if>
                            <span style="margin-left: 5px"><i style="color:#78909c">${req.left}:</i>&nbsp;${req.middle}</span>
                        </p>
                    </#list>
                        </div>
                        </#if>
                    </#list>
                </div>
            </li>
        </ul>
            </#if>
            <#if extras?? && extras?size gt 0>
        <ul class="col s6 collapsible popout expandable">
            <li>
                <div class="collapsible-header">
                    <div class="light-blue-text"><i class="material-icons">fitness_center</i></div>
                    <span>Extra achievements</span>
                </div>
                <div class="collapsible-body">
                    <#list extras as extra>
                         <#if extra.requirements?? && extra.requirements?size gt 0>
                        <p style="display:flex">
                            <span><i><b><u>${extra.name}</u></b></i></span>
                            <#if extra.achievedComments?? && extra.achievedComments?size gt 0>
                                <#list  extra.achievedComments as comment>
                                    <i class="tooltipped orange-text material-icons"
                                       data-position="top" data-tooltip="${comment}"
                                        style="margin-left: 5px">
                                        star</i>
                                </#list>
                            </#if>
                        </p>
                        <div style="margin-left: 20px">
                    <#list extra.requirements as req>
                        <p style="display: flex">
                    <#if req.left?contains("time")>
                        <i class="light-blue-text material-icons">access_time</i>
                    <#elseif req.left?contains("symbols")>
                        <i class="orange-text material-icons">text_rotation_none</i>
                    <#elseif req.left?contains("executions") && req.left?contains("successful")>
                        <i class="green-text material-icons">play_arrow</i>
                    <#elseif req.left?contains("executions") && req.left?contains("failed")>
                        <i class="red-text material-icons">play_arrow</i>
                    <#elseif req.left?contains("commits") && req.left?contains("successful")>
                         <i class="green-text material-icons">done</i>
                    <#elseif req.left?contains("commits") && req.left?contains("failed")>
                         <i class="red-text material-icons">close</i>
                    <#else>
                        <i class="material-icons">keyboard</i>
                    </#if>
                            <span style="margin-left: 5px"><i style="color:#78909c">${req.left}:</i>&nbsp;${req.middle}</span>
                        </p>
                    </#list>
                        </div>
                         </#if>
                    </#list>
                </div>
            </li>
        </ul>
            </#if>


            <#if teams?? && teams?size gt 0>
        <ul class="col s6 collapsible popout expandable">
            <li>
                <div class="collapsible-header">
                    <div class="light-blue-text"><i class="material-icons">group</i></div>
                    <span>Team contributions</span>
                </div>
                <div class="collapsible-body">
                    <#assign i=0>
                    <#list teams as team>
                        <#if team.requirements?? && team.requirements?size gt 0>
                        <p style="display:flex">
                            <span><i><b><u>${team.name}</u></b></i></span>
                            <#if team.achievedComments?? && team.achievedComments?size gt 0>
                                <#list  team.achievedComments as comment>
                                    <i class="tooltipped orange-text material-icons"
                                       data-position="top" data-tooltip="${comment}"
                                       style="margin-left: 5px">
                                        star</i>
                                </#list>
                            </#if>
                        </p>
                    <div style="margin-left: 20px">
                    <#list team.requirements as req>
                        <p style="display: flex">
                    <#if req.left?contains("time")>
                        <i class="light-blue-text material-icons">access_time</i>
                    <#elseif req.left?contains("symbols")>
                        <i class="orange-text material-icons">text_rotation_none</i>
                    <#elseif req.left?contains("executions") && req.left?contains("successful")>
                        <i class="green-text material-icons">play_arrow</i>
                    <#elseif req.left?contains("executions") && req.left?contains("failed")>
                        <i class="red-text material-icons">play_arrow</i>
                    <#elseif req.left?contains("commits") && req.left?contains("successful")>
                         <i class="green-text material-icons">done</i>
                    <#elseif req.left?contains("commits") && req.left?contains("failed")>
                         <i class="red-text material-icons">close</i>
                    <#else>
                         <i class="material-icons">keyboard</i>
                    </#if>
                            <span style="margin-left: 5px"><i
                                    style="color:#78909c">${req.left}:</i>&nbsp;${req.middle}&emsp;
                     <#if req.right??>
                         <#if req.right gt 0.79>
                     <i style="color:#F44336">
                         <#elseif req.right gt 0.49>
                     <i style="color:#ff9800">
                         <#else>
                     <i style="color:#4CAF50">
                         </#if>
                         ${req.right?string.percent}
                         <#if i==0>* <#assign i=1></#if>
                     </#if></i></p>
                    </#list>
                    </div>
                        </#if>
                    </#list>

                    <br><br><br>
                    <span>* of overall team contribution</span>
                </div>
            </li>
        </ul>
            </#if>

        </#if>

    </div>
</main>
        <#include "footer_auth.ftl">


</body>
</html>
