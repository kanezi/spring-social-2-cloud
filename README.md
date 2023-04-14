# Spring social 2 Cloud

Goal of the application is to create secured Spring Web application, that enables sign in via Oauth2 (github) and Oidc (google) providers.

PostgreSQL database is used to provide persistence.

Application is intended to be deployed to the cloud.


## Youtube tutorials
Series of Youtube tutorials where application is developed step by step accompanies the repository.

Youtube playlist:

[![IMAGE_ALT](DOCS/images/spring_social_4_cloud.png)](https://youtube.com/playlist?list=PLLhgRnf2WBVQe1iPUuNZnMmlqK6vd_o59)


## Cloud providers

Various cloud providers will be used to deploy the app.

### Railway.app

[Railway.app](https://railway.app?referralCode=kanezi) is used as one of the cloud providers to deploy our app.

After provisioning Postgres instance on railway.app following dynamic url can be used to connect spring application to database:

```jdbc:postgresql://${{Postgres.PGHOST}}:${{Postgres.PGPORT}}/${{Postgres.PGDATABASE}}```