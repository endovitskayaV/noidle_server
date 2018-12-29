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
        <#if statistics??>
            <div class="row">
                <ul class="col s6 collapsible popout expandable">
                    <li>
                        <div class="collapsible-header">
                            <div class="light-blue-text"><i class="material-icons">access_time</i></div>
                            <span>Time</span>
                        </div>
                        <div class="collapsible-body container">
                            <p><span><i>overall</i>:&emsp;</span><span><b>${statistics["timeper_day"]!"—"}</b></span>
                            </p>
                            <p>
                                <span><i>max continuous</i>:&emsp;</span><span><b>${statistics["timecontinuous_per_day"]!"—"}</b></span>
                            </p>
                        </div>
                    </li>
                </ul>
                <ul class="col s6 collapsible popout expandable">
                    <li>
                        <div class="collapsible-header">
                            <div class="green-text"><i class="material-icons">play_arrow</i></div>
                            <span>Executions</span>
                        </div>
                        <div class="collapsible-body container">
                            <p style="display: flex"><i class="green-text material-icons">done</i>
                                <span><i>successful</i>:&emsp;</span>
                                <span><b>${statistics["execper_lifesuccessful"]!"—"}</b></span>
                            </p>
                            <p style="display: flex"><i class="red-text material-icons">close</i>
                                <span><i>failed</i>:&emsp;</span>
                                <span><b>${statistics["execper_lifefailed"]!"—"}</b></span>
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="row">
                <ul class="col s6 collapsible popout expandable">
                    <li>
                        <div class="collapsible-header">
                            <div class="orange-text"><i class="material-icons">text_rotation_none</i></div>
                            <span>Symbols</span>
                        </div>
                        <div class="collapsible-body container">
                            <p><span><i>today</i>:&emsp;</span><span><b>${statistics["symbolper_day"]!"—"}</b></span>
                            </p>
                            <p>
                                <span><i>continuous</i>:&emsp;</span><span><b>${statistics["symbolcontinuous_per_day"]!"—"}</b></span>
                            </p>
                        </div>
                    </li>
                </ul>
                <ul class="col s6 collapsible popout expandable">
                    <li>
                        <div class="collapsible-header">
                            <div class="green-text"><i class="material-icons">check</i></div>
                            <span>Commits</span>
                        </div>
                        <div class="collapsible-body container">
                            <p style="display: flex"><i class="green-text material-icons">done</i>
                                <span><i>successful</i>:&emsp;</span>
                                <span><b>${statistics["commitper_lifesuccessful"]!"—"}</b></span>
                            </p>
                            <p style="display: flex"><i class="red-text material-icons">close</i>
                                <span><i>failed</i>:&emsp;</span>
                                <span><b>${statistics["commitper_lifefailed"]!"—"}</b></span>
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="row">
                <ul class="col s6 collapsible popout expandable">
                    <li>
                        <div class="collapsible-header">
                            <div class="light-blue-text"><i class="material-icons">keyboard</i></div>
                            <span>Keys</span>
                        </div>
                        <div class="collapsible-body">
                        <#if keys??>
                            <table class="highlight centered">
                                <tbody>
                        <#list keys as name, value>
                        <tr>
                            <td><i>${name}</i></td>
                            <td><b>${value}</b></td>
                        </tr>
                        </#list>
                                </tbody>
                            </table>
                        <#else>
                        </#if>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="row">
                <ul class="col s8 collapsible popout expandable">
                    <li>
                        <div class="collapsible-header">
                            <div class="orange-text"><i class="material-icons">language</i></div>
                            <span>Languages</span>
                        </div>
                        <div class="collapsible-body">
                            <#if languages??>

                                <table class="highlight centered">
                                    <thead>
                                    <tr>
                                        <th>
                                            <div class="orange-text"><i class="material-icons">language</i></div>
                                        </th>
                                        <th>
                                            <div class="light-blue-text"><i class="material-icons">access_time</i></div>
                                        </th>
                                        <th>
                                            <div class="light-blue-text">
                                                <i class="material-icons">text_rotation_none</i>
                                            </div>
                                        </th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <#list languages as language>
                                    <tr>
                                        <td><b>${language.languageName}</b></td>
                                        <td><i>${language.time}</i></td>
                                        <td><i>${language.symbols}</i></td>
                                    </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            <#else>
                            </#if>
                        </div>
                    </li>
                </ul>
            </div>
        <#else>
        <br><br>
              <div class="container">
                  <div class="container">
                      <div class="container">
                          <div class="card horizontal">
                              <div class="card-stacked">
                                  <div class="card-content">
                                      <p class="center-align"><span>No statistics available</span></p>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
        </#if>
    </div>
</main>
 <#include "footer.ftl">


</body>
</html>
