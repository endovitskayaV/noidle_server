<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="/" class="brand-logo">
            <i class="material-icons">bar_chart</i>
            NoIdle</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="http://plugins.jetbrains.com/plugin/7697-unvired-sdk-installer">
                <i class="material-icons left">file_download</i>Download plugin</a>
            </li>
            <li><a href="/login">
                <i class="material-icons left">exit_to_app</i>Log in</a>
            </li>
        </ul>

        <ul id="nav-mobile" class="sidenav">
            <li><a href="http://plugins.jetbrains.com/plugin/7697-unvired-sdk-installer">
                <i class="material-icons left">file_download</i>Download plugin</a>
            </li>
            <#if doNotLogin?? && doNotLogin==true>
            <#else>
            <li><a href="/login">
                <i class="material-icons left">exit_to_app</i>Log in</a>
            </li>
            </#if>
        </ul>
        <a href="#" data-target="nav-mobile" class="sidenav-trigger">
            <i class="material-icons left">menu</i>
        </a>
    </div>
</nav>