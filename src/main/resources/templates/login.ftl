<!--<!doctype html>-->
<!--<html>-->
<!--<head>-->
    <!--<meta charset="utf-8"/>-->
    <!--<title>Login</title>-->
    <!--<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css"/>-->
    <!--<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>-->
    <!--<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>-->
    <!--<script type="text/javascript" src="/webjars/js-cookie/js.cookie.js"></script>-->
<!--</head>-->
<!--<body>-->
<!--<div class="container">-->
    <!--<div class="container unauthenticated">-->
        <!--<div>-->
            <!--<h1>Login</h1>-->
            <!--<p><a href="/callback/github">Github</a></p>-->
            <!--<p><a href="/callback/google">Google</a></p>-->
            <!--<p><a href="/callback/gitlab">Gitlab</a></p>-->
        <!--</div>-->
    <!--</div>-->
<!--</div>-->
<!--</body>-->
<!--</html>-->



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
<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="/" class="brand-logo"><i class="material-icons">highlight</i>NoIdle</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="http://plugins.jetbrains.com/plugin/7697-unvired-sdk-installer">Download plugin</a></li>
            <li><a href="/">About</a></li>
        </ul>

        <ul id="nav-mobile" class="sidenav">
            <li><a href="http://plugins.jetbrains.com/plugin/7697-unvired-sdk-installer">Download plugin</a></li>
            <li><a href="/">About</a></li>
        </ul>
        <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    </div>
</nav>
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

<footer class="page-footer orange" style="position:fixed;bottom: -4px;">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Valentina Endovitskaya</h5>
                <p class="grey-text text-lighten-4"> I am a student of Voronezh State University and working on this
                    project as a final task.
                    My aim is to make every developer realise the amount of work and team contribution they do every
                    day, help team members to know about each other a little bit more.</p>
            </div>

            <div class="col l3 s12">
            </div>

            <div class="col l3 s12">
                <h5 class="white-text">Connect</h5>
                <ul>
                    <li><a class="white-text" href="https://gitlab.com/endovitskayaV">GitLab</a></li>
                    <li><a class="white-text" href="https://github.com/endovitskayaV">GitHub</a></li>
                    <li><a class="white-text" href="mailto:evvEndovitskaya@yandex.ru">Email</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            Made by <a class="orange-text text-lighten-3" href="https://gitlab.com/endovitskayaV">endovitskayaV</a>
        </div>
    </div>
</footer>


<!--  Scripts-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/init.js"></script>

</body>
</html>
