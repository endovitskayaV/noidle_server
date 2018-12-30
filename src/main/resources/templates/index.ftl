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

 <#include "nav_unauth.ftl">

<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <h1 class="header center orange-text">Realise your achievements</h1>
        <div class="row center">
            <h5 class="header col s12 light">Open source plugin for self control and command interaction</h5>
        </div>
        <div class="row center">
            <br>
            <a href="/login" id="download-button"
               class="btn-large waves-effect waves-light orange">Get Started</a>
        </div>
    </div>
</div>

<div class="container">
    <div class="section">

        <!--   Icon Section   -->
        <div class="row">
            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center light-blue-text"><i class="material-icons">flash_on</i></h2>
                    <h5 class="center">User experience</h5>

                    <p class="light">Plugin measures your activity and provide statistics in vivid and clear way on the
                        website, toolwindow.
                        Additionally, it sendd notifications which can be set in ide or on the web cite. </p>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center light-blue-text"><i class="material-icons">group</i></h2>
                    <h5 class="center">Team interaction</h5>

                    <p class="light">You can create a team to be able to get your colleagues achievements and realise
                        each member contribution.</p>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center light-blue-text"><i class="material-icons">settings</i></h2>
                    <h5 class="center">Easy to work with</h5>

                    <p class="light">There are instructions to help
                        new users get started. I am also always open to feedback and can answer any questions a user
                        may have about NoIdle.</p>
                </div>
            </div>
        </div>

    </div>
</div>

 <#include "footer.ftl">

</body>
</html>
