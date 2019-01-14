<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <link rel="shortcut icon" href="/img/icon.png" type="image/png">
    <title>NoIdle&nbsp;&bull;&nbsp;Add users</title>

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
    <br> <br> <br> <br> <br>
    <div class="container">
        <div class="container">
            <form action="/users/save" method="post">
                <div class="row">
                    <div class="input-field">
                        <textarea id="users_string" class="materialize-textarea" name="userData"></textarea>
                        <label for="users_string">Users</label>
                    </div>
                </div>
                <div class="center">
                    <button class="waves-effect waves-orange btn-flat" type="submit">Save</button>
                </div>
            </form>
        </div>
    </div>
</main>

 <#include "footer_auth.ftl">

</body>
</html>
