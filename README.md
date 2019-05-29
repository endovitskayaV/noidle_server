# NoIdle server
Server for [NoIdle plugin](https://gitlab.com/endovitskayaV/NoIdle)

## Information
This server is used to store plugin and users` data and to process achievements
<details>
    <summary><i>Screenshots</i></summary>
    <p><br><img src="https://github.com/endovitskayaV/noidle_server/blob/screenshots/main.png" height="500"></p>
    <p><br><img src="https://github.com/endovitskayaV/noidle_server/blob/screenshots/dashboard.png" height="500"></p>
    <p><br><img src="https://github.com/endovitskayaV/noidle_server/blob/screenshots/achievements.png" height="500"></p>
</details><br>

### Deployment
This version is used to be self hosted:
1. [Download .war](https://github.com/endovitskayaV/noidle_server/releases)
2. Set all system properties listed in [application.yml](https://github.com/endovitskayaV/noidle_server/blob/custom_server/src/main/resources/application.yml), for example<ul>
    <li>JDBC_DATABASE_URL = <i>jdbc:postgresql://localhost:5432/noidle</i></li>
    <li>JDBC_DATABASE_USERNAME = <i>postgres</i></li>
    <li>JDBC_DATABASE_PASSWORD = <i>postgres</i></li>
    <li>DRIVER_CLASS_NAME = <i>org.postgresql.Driver</i></li>
    <li>DATABASE_PLATFORM = <i>org.hibernate.dialect.PostgreSQL9Dialect</i></li>
    <li>DATABASE_DIALECT = <i>org.hibernate.dialect.PostgreSQL9Dialect</i></li></ul>    
3. Deploy to any server you like.
4. Visit server page and finish installation by setting up admin account <br>
   and adding users` information
 <details>
    <summary><i>Screenshots</i></summary>
    <p><br><img src="https://github.com/endovitskayaV/noidle_server/blob/screenshots/admin_setup.png" height="500"></p>
    <p><br><img src="https://github.com/endovitskayaV/noidle_server/blob/screenshots/add_users.png" height="455"></p>
</details><br>  

### Modificataions
Its main version is deployed to [Heroku](https://noidle.herokuapp.com). <br>
[See documentation](https://github.com/endovitskayaV/noidle_server)
