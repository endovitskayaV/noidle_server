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
        var elems = document.querySelectorAll('.collapsible');
        var instances = M.Collapsible.init(elems);
    });

</script>
<#include "nav_auth.ftl">
<main>
    <br><br>
    <div class="container">


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
                        <div class="orange-text"><i class="material-icons">sort</i></div>
                        <span>Levels</span>
                </div>
                <div class="collapsible-body">
                    <#list levels as level>
                    </#list>
                <#--<p><span><i>overall</i>:&emsp;</span><span><b>${statistics["timeper_day"]}</b></span></p>-->

                </div>
            </li>
        </ul>
            </#if>
            <#if extras?? && extras?size gt 0>
        <ul class="col s6 collapsible popout expandable">
            <li>
                <div class="collapsible-header">
                        <div class="orange-text"><i class="material-icons">star</i></div>
                        <span>Extra achievements</span>
                </div>
                <div class="collapsible-body">
                </div>
            </li>
        </ul>
            </#if>


            <#if teams?? && teams?size gt 0>
        <ul class="col s6 collapsible popout expandable">
            <li>
                <div class="collapsible-header">
                        <div class="orange-text"><i class="material-icons">group</i></div>
                        <span>Team contributions</span>
                </div>
                <div class="collapsible-body">
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
