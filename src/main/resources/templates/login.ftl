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
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
 <#include "nav_unauth.ftl">

<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <div class="row center">
            <br><br>
            <h5 class="header col s12 light">Log in to NoIdle</h5>
        </div>
    </div>
</div>


<div class="container">
    <div class="section">

        <!--   Icon Section   -->
        <div class="row">
            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center"><img src="img/github.png"></h2>
                    <h5 class="center"><a href="/callback/github">Github</a></h5>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center"><img src="img/gitlab.png"></h2>
                    <h5 class="center"><a href="/callback/gitlab">Gitlab</a></h5>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center"><img src="img/google.png"></h2>
                    <h5 class="center"><a href="/callback/google">Google</a></h5>
                </div>
            </div>
        </div>

    </div>
    <br><br>
</div>

 <#include "footer_bottom.ftl">

<!--  Scripts-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/init.js"></script>

</body>
</html>
