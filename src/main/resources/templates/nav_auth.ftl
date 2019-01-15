<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="/" class="brand-logo">
            <i class="material-icons">bar_chart</i>
            NoIdle</a>
        <#if admin?? && admin>
         <ul class="right hide-on-med-and-down">
             <li><a href="/admin/users/save"><i class="material-icons left">group_add</i>Add users</a></li>
             <li><a href="/logout"><i class="material-icons left">exit_to_app</i>Log out</a></li>
         </ul>
        <#else>
        <ul class="right hide-on-med-and-down">
            <li><a href="/dashboard"><i class="material-icons left">dashboard</i>Dashboard</a></li>
            <li><a href="/teams"><i class="material-icons left">group</i>Teams</a></li>
            <li><a href="/profile"><i class="material-icons left">person</i>Profile</a></li>
            <li><a href="/logout"><i class="material-icons left">exit_to_app</i>Log out</a></li>
        </ul>
        </#if>

            <#if admin?? && admin>
         <ul id="nav-mobile" class="sidenav">
             <li><a href="/admin/users/save"><i class="material-icons left">group_add</i>Add users</a></li>
             <li><a href="/logout"><i class="material-icons left">exit_to_app</i>Log out</a></li>
         </ul>
            <#else>
        <ul id="nav-mobile" class="sidenav">
            <li><a href="/dashboard"><i class="material-icons left">dashboard</i>Dashboard</a></li>
            <li><a href="/teams"><i class="material-icons left">group</i>Teams</a></li>
            <li><a href="/profile"><i class="material-icons left">person</i>Profile</a></li>
            <li><a href="/logout"><i class="material-icons left">exit_to_app</i>Log out</a></li>
        </ul>
        </#if>
        <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    </div>
</nav>