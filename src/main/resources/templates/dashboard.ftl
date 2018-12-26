<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>No Idle</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/custom.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
<!--  Scripts-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
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
        <div class="row">
            <ul class="col s6 collapsible popout expandable">
                <li>
                    <div class="collapsible-header">
                        <div class="center light-blue-text"><i class="material-icons">access_time</i></div>
                        <span>Time</span>
                    </div>

                </li>
            </ul>
            <ul class="col s6 collapsible popout expandable">
                <li>
                    <div class="collapsible-header">
                        <div class="center green-text"><i class="material-icons">play_arrow</i></div>
                        <span>Executions</span>
                    </div>

                </li>
            </ul>
        </div>
        <div class="row">
            <ul class="col s6 collapsible popout expandable">
                <li>
                    <div class="collapsible-header">
                        <div class="center orange-text"><i class="material-icons">text_rotation_none</i></div>
                        <span>Symbols</span>
                    </div>

                </li>
            </ul>
            <ul class="col s6 collapsible popout expandable">
                <li>
                    <div class="collapsible-header">
                        <div class="center green-text"><i class="material-icons">check</i></div>
                        <span>Commits</span>
                    </div>

                </li>
            </ul>
        </div>
        <div class="row">
            <ul class="col s6 collapsible popout expandable">
                <li>
                    <div class="collapsible-header">
                        <div class="center light-blue-text"><i class="material-icons">keyboard</i></div>
                        <span>Keys</span>
                    </div>

                </li>
            </ul>
        </div>
        <div class="row">
            <ul class="col s8 collapsible popout expandable">
                <li>
                    <div class="collapsible-header">
                        <div class="center orange-text"><i class="material-icons">language</i></div>
                        <span>Languages</span>
                    </div>

                </li>
            </ul>
        </div>
    </div>
</main>
 <#include "footer.ftl">


</body>
</html>
