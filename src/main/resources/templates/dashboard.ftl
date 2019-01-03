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
<script>
    var selectedDate = "${selectedDate}"
    var selectedStartDate="${selectedStartDate}"
    var selectedEndDate ="${selectedEndDate}"
</script>
<script type="text/javascript" src="js/dashboardHandler.js"></script>
<#include "nav_auth.ftl">
<main>
    <br><br>
    <div class="container" id="main-container">

        <div class="row">
            <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons light-blue-text"
                                                                           style="margin:0 30px;">more_horiz</i></a>


            <ul id="slide-out" class="sidenav">

                <li>
                    <a class="subheader">Period</a>
                    <form action="#" class="nav-content">
                        <p>
                            <label>
                                <input id="overall-radio" name="groupMain" type="radio"
                                       onclick="
                                    disableElem($('#date-input'));
                                    disableElem($('#start-date-input'));
                                    disableElem($('#end-date-input'));"/>
                                <span>overall</span>
                            </label>
                        </p>
                        <p>
                            <label>
                                <input name="groupMain" type="radio" id="date-picker-radio"
                                       onclick="
                                           enableElem($('#date-input'));
                                           disableElem($('#start-date-input'));
                                           disableElem($('#end-date-input'));"/>
                                <span><input id="date-input" type="text" class="datepicker radio-text"></span>
                            </label>
                        </p>
                        <p>
                            <label>
                                <input name="groupMain" type="radio" id="start-date-picker-radio"
                                       onclick="
                                              enableElem($('#start-date-input'));
                                                 enableElem($('#end-date-input'));
                                           disableElem($('#date-input'));"/>
                                <span><span>from<input required id="start-date-input" type="text"
                                                       class="datepicker period radio-text"></span></span>
                            </label>
                        </p>
                        <p>
                            <label>
                                <input type="radio" hidden name="hidden">
                                <span class="hidden" style="margin-top: 10px"><span>to
                                    <input required id="end-date-input" type="text" class="datepicker period radio-text"></span></span>
                            </label>
                        </p>
<#switch selectedPeriod>
    <#case "overall">
        <script>
            checkedElem($("#overall-radio"));
            disableElem($("#date-input"));
            disableElem($("#start-date-input"));
            disableElem($("#end-date-input"));
        </script>
        <#break>
    <#case "date">
        <script>
            checkedElem($("#date-picker-radio"));
            disableElem($("#start-date-input"));
            disableElem($("#end-date-input"));
        </script>
        <#break>
    <#case "range">
        <script>
            checkedElem($("#start-date-picker-radio"));
            disableElem($("#date-input"));
        </script>
        <#break>
</#switch>
                    </form>
                </li>
                <li>
                    <div class="divider" style="margin-top: 40px;"></div>
                </li>
                <li>
                    <a class="subheader" style="padding-top: 12px; padding-bottom: 42px">Team</a>
                    <form action="#" class="nav-content">
                            <#if selectedTeamId??>
                              <p>
                                  <label>
                                      <input name="groupMain" type="radio" onclick="disableTeams()"/>
                                      <span>out of team</span>
                                  </label>
                              </p>
                            <p>
                                <label>
                                    <input name="groupMain" type="radio" id="teamRadio" checked
                                           onchange="enableTeams()"/>
                                    <span>teams:</span>
                                </label>
                            </p>
                            <#else>
                               <p>
                                   <label>
                                       <input name="groupMain" type="radio" checked onclick="disableTeams()"/>
                                       <span>out of team</span>
                                   </label>
                               </p>
                            <p>
                                <label>
                                    <input name="groupMain" type="radio" id="teamRadio" onclick="enableTeams()"/>
                                    <span>teams:</span>
                                </label>
                            </p>
                            </#if>

                        <div class="nav-content" style="padding-left: 32px">
                                <#list teams as team>
                                     <#if selectedTeamId??>

                                         <#if team.id==selectedTeamId>
                                     <p>
                                         <label>
                                             <input name="groupTeam" type="radio" class="with-gap" checked
                                                    id="${team.id}"/>
                                             <span>${team.name}</span>
                                         </label>
                                     </p>
                                         <#else>
                                        <p>
                                            <label>
                                                <input name="groupTeam" type="radio" class="with-gap" id="${team.id}"/>
                                                <span>${team.name}</span>
                                            </label>
                                        </p>
                                         </#if>
                                     <#else>
                                       <p>
                                           <label>
                                               <input name="groupTeam" type="radio" class="with-gap" disabled="disabled"
                                                      id="${team.id}"/>
                                               <span>${team.name}</span>
                                           </label>
                                       </p>
                                     </#if>
                                </#list>
                        </div>
                    </form>
                </li>
                <li><br></li>
                <li>
                    <div class="col s3 offset-s8">
                        <button type="submit" class="waves-effect waves-light btn-small blue"
                                style="margin-top: 25px"
                                onclick="applyStatisticsFilters()">Apply
                        </button>
                    </div>
                </li>
            </ul>
        </div>

        <#if statistics?? && statistics?size gt 0>
    <div class="row">
        <ul class="col s6 collapsible popout expandable">
            <li>
                <div class="collapsible-header">
                    <div class="light-blue-text"><i class="material-icons">access_time</i></div>
                    <span>Time</span>
                </div>
                <div class="collapsible-body">
    <#if statistics["timeper_day"]??>
        <p><span><i>overall</i>:&emsp;</span><span><b>${statistics["timeper_day"]}</b></span></p>
    </#if>
    <#if statistics["timecontinuous_per_day"]??>
        <p><span><i>max continuous</i>:&emsp;</span><span><b>${statistics["timecontinuous_per_day"]}</b></span></p>
    </#if>
     <#if statistics["timeper_life"]??>
        <p><span><i>overall</i>:&emsp;</span><span><b>${statistics["timeper_life"]}</b></span></p>
     </#if>
    <#if statistics["timecontinuous_per_life"]??>
        <p><span><i>max continuous</i>:&emsp;</span><span><b>${statistics["timecontinuous_per_life"]}</b></span></p>
    </#if>
                </div>
            </li>
        </ul>
        <ul class="col s6 collapsible popout expandable">
            <li>
                <div class="collapsible-header">
                    <div class="green-text"><i class="material-icons">play_arrow</i></div>
                    <span>Executions</span>
                </div>
                <div class="collapsible-body">
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
                <div class="collapsible-body">
        <#if statistics["symbolper_day"]??>
       <p><span><i>today</i>:&emsp;</span><span><b>${statistics["symbolper_day"]}</b></span></p>
        </#if>
       <#if statistics["symbolcontinuous_per_day"]??>
        <p><span><i>max continuous</i>:&emsp;</span><span><b>${statistics["symbolcontinuous_per_day"]}</b></span></p>
       </#if>
       <#if statistics["symbolper_life"]??>
       <p><span><i>today</i>:&emsp;</span><span><b>${statistics["symbolper_life"]}</b></span></p>
       </#if>
       <#if statistics["symbolcontinuous_per_life"]??>
        <p><span><i>max continuous</i>:&emsp;</span><span><b>${statistics["symbolcontinuous_per_life"]}</b></span></p>
       </#if>
                </div>
            </li>
        </ul>
        <ul class="col s6 collapsible popout expandable">
            <li>
                <div class="collapsible-header">
                    <div class="green-text"><i class="material-icons">check</i></div>
                    <span>Commits</span>
                </div>
                <div class="collapsible-body">
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
            <#if keys?? && keys?size gt 0>
        <div class="row">
            <ul class="col s6 collapsible popout expandable">
                <li>
                    <div class="collapsible-header">
                        <div class="light-blue-text"><i class="material-icons">keyboard</i></div>
                        <span>Keys</span>
                    </div>
                    <div class="collapsible-body">

                        <table class="highlight">
                            <tbody id="keys_table">
                            <#assign i=1>
                            <#list keys as name, value>
                            <tr>
                                <td class="center-align"><b>${name}</b></td>
                                <td><i>${value}</i></td>
                            </tr>
                                <#assign i=i+1>
                                <#if i gt 5>
                                    <#break>
                                </#if>
                            </#list>
                            </tbody>
                        </table>
                    <#if i gt 5>
                        <script>
                            var showMoreKeys = {value: true};
                            var keys = [];
                            <#assign i=1>
                            <#list keys as name, value>
                            keys.push({
                                name: '${name}',
                                value: '${value}'
                            });
                                <#assign i=i+1>
                            </#list>
                        </script>
                        <br>
                        <a id="show_keys" href="#!" class="center"
                           onclick='show($("#show_keys"),$("#keys_table"), keys, showMoreKeys, tableType.keys)'>
                            Show more</a>
                    </#if>
                    </div>
                </li>
            </ul>
        </div>
            </#if>

            <#if languages?? && languages?size gt 0>
        <div class="row">
            <ul class="col s8 collapsible popout expandable">
                <li>
                    <div class="collapsible-header">
                        <div class="orange-text"><i class="material-icons">language</i></div>
                        <span>Languages</span>
                    </div>
                    <div class="collapsible-body">
                <#if languages?? && languages?size gt 0>

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

                        <tbody id="langs_table">
                            <#assign i=1>
                            <#list languages as language>
                            <tr>
                                <td><b>${language.languageName}</b></td>
                                <td><i>${language.time}</i></td>
                                <td><i>${language.symbols}</i></td>
                            </tr>
                                <#assign i=i+1>
                                <#if i gt 5>
                                    <#break>
                                </#if>
                            </#list>
                        </tbody>
                    </table>

                    <#if i gt 5>
                        <script>
                            var showMoreLangs = {value: true};
                            var langs = [];
                            <#assign i=1>
                            <#list languages as language>
                            langs.push({
                                languageName: '${language.languageName}',
                                time: '${language.time}',
                                symbols: '${language.symbols}'
                            });
                                <#assign i=i+1>
                            </#list>
                        </script>
                        <br><a id="show_langs" href="#!" class="center"
                               onclick='show($("#show_langs"), $("#langs_table"), langs, showMoreLangs, tableType.langs)'>
                        Show more</a>
                    </#if>

                    </div>
                </li>
            </ul>
        </div>
                </#if>

            </#if>

        <#else>
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
        <#include "footer_auth.ftl">


</body>
</html>
