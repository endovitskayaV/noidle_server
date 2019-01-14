<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <link rel="shortcut icon" href="img/icon.png" type="image/png">
    <title>NoIdle&nbsp;&bull;&nbsp;Login</title>

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

 <#include "nav_unauth.ftl">
<main>
    <div class="section no-pad-bot" id="index-banner">
        <div class="container">
            <div class="row center">
                <h3 class="header col s12 orange-text">Log in to NoIdle</h3>
                <br><br><br>
                 <#if errorMessage??>
                 <br><span style="color: red;">${errorMessage}</span><br><br>
                 <#else>
                  <br><br><br>
                 </#if>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="container">
            <form action="/login" method="post">

                <div class="row">
                    <div class="input-field">
                        <i class="material-icons prefix">account_circle</i>
                        <#if login??>
                            <input type="text" required name="name" value="${login}">
                        <#else>
                             <input type="text" required name="name">
                        </#if>
                        <span class="helper-text">Login</span>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field">
                        <i class="material-icons prefix">lock</i>
                        <input type="password" required name="password">
                        <span class="helper-text">Password</span>
                    </div>
                </div>


                <div class="center">
                    <button class="waves-effect waves-orange btn-flat" type="submit">Ok</button>
                </div>
            </form>
        </div>
    </div>
</main>

 <#include "footer_unauth.ftl">

</body>
</html>
