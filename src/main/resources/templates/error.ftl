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
<script type="text/javascript" src="js/base.js"></script>

<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="/" class="brand-logo"><i class="material-icons">highlight</i>NoIdle</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="http://plugins.jetbrains.com/plugin/7697-unvired-sdk-installer">
                <i class="material-icons left">file_download</i>Download plugin</a>
            </li>
        </ul>

        <ul id="nav-mobile" class="sidenav">
            <li><a href="http://plugins.jetbrains.com/plugin/7697-unvired-sdk-installer">Download plugin</a></li>
        </ul>
        <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    </div>
</nav>
<main>
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <h1 class="header center orange-text">Page not found</h1>
        <div class="row center">
            <div class="header col s12"><img src="img/error.png"></div>
        </div>
        <div class="row center">
            <br><br>
            <a href="/" id="download-button"
               class="btn-large waves-effect waves-light light-blue lighten-1">Home</a>
        </div>
    </div>
</div>
</main>
 <#include "footer_unauth.ftl">

</body>
</html>
