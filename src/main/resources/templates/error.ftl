<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <#if url??>
    <link rel="shortcut icon" href="${url}/img/icon.png" type="image/png">
    <#else>
    <link rel="shortcut icon" href="img/icon.png" type="image/png">
    </#if>
    <title>NoIdle&nbsp;&bull;&nbsp;Error</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <#if url??>
    <link href="${url}/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="${url}/css/custom.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <#else>
   <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/custom.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </#if>

</head>
<body>
<!--  Scripts-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

 <#if url??>
<script type="text/javascript" src="${url}/js/materialize.js"></script>
<script type="text/javascript" src="${url}/js/base.js"></script>
 <#else>
  <script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/base.js"></script>
 </#if>


<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="/" class="brand-logo"><i class="material-icons">bar_chart</i>NoIdle</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="https://yadi.sk/d/X2B2WL1ohD5KwA" download>
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

 <#if url??>
     <div class="header col s12"><img src="${url}/img/error.png"></div>
 <#else>
     <div class="header col s12"><img src="img/error.png"></div>
 </#if>

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
