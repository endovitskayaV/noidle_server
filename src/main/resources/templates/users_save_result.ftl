<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <link rel="shortcut icon" href="/img/icon.png" type="image/png">
    <title>NoIdle&nbsp;&bull;&nbsp;Result</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="/css/custom.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
<!--  Scripts-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/js/materialize.js"></script>
<script type="text/javascript" src="/js/base.js"></script>

 <#include "nav_auth.ftl">
<main>
    <br>

    <div class="section no-pad-bot" id="index-banner">
        <div class="container">
            <div class="row center">
                <h5 class="header col s12 orange-text">Save</h5>
            </div>
        </div>
    </div>
    <br>
    <div class="container">
        <div class="container">
            <div class="card horizontal">
                <div class="card-stacked">
                    <div class="card-content">
                        <p class="center-align"><span>${message}</span></p>

                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

 <#include "footer_auth.ftl">

</body>
</html>
