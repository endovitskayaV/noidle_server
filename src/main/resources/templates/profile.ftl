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
 <#include "nav_auth.ftl">
<main>
    <div class="container">
        <h2 class="header center orange-text">Profile</h2>
        <div class="container">
            <div class="card horizontal">
                <div class="card-image">
                    <img src="${user.photo}" style="max-width:200px">
                </div>
                <div class="card-stacked">
                    <div class="card-content left-align">
                        <p><i class="material-icons">vpn_key</i>&ensp;<a href="#" id="key">Click to see</a>
                            <span id="my_user_real_key"></span></p>
                        <p><i class="material-icons">email</i>&ensp;<span>${user.email}</span></p>
                    </div>
                    <div class="card-action left-align">
                        <a href="/teams">Teams</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<br><br>
 <#include "footer_auth.ftl">

<script>
    $('#key').click(function () {
        this.text = "";
        $('#my_user_real_key')[0].textContent = "${user.id}"

    });
</script>
</body>
</html>
