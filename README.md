# Spring social 2 Cloud

Goal of the application is to create secured Spring Web application, that enables sign in via Oauth2 (github) and Oidc (google) providers.

PostgreSQL database is used to provide persistence.

Application is intended to be deployed to the cloud.

## Prerequisites

Prerequisites for developing / running app locally (__checked__ are mandatory)

- [x] java
- [x] docker
- [ ] git
- [ ] maven


## Running app locally

To start application locally, run following commands from the root of the project:

> 1. start required auxiliary services  
> **```docker compose up -d```**

> 2. run spring application using spring-boot runner  
> **```./mvnw spring-boot:run```**

> 3. access the app at:  
> ***http://localhost:8080***

---

To use Google and Github log in locally / in cloud, you'll have to register oauth2 applications with github (Oauth2) and google (Oidc).

You'll be given **CLIENT_ID** and **CLIENT_SECRET** which you'll need to place in your **environment variables**:

**GOOGLE**
* ```GOOGLE_CLIENT_ID```
* ```GOOGLE_CLIENT_SECRET```

**GITHUB**
* ```GITHUB_CLIENT_ID```
* ```GITHUB_CLIENT_SECRET```

Application will automatically pick up and use given values.


## Youtube tutorials
Series of Youtube tutorials where application is developed step by step accompanies the repository.

Youtube playlist:

[![IMAGE_ALT](docs/images/spring_social_4_cloud.png)](https://youtube.com/playlist?list=PLLhgRnf2WBVQe1iPUuNZnMmlqK6vd_o59)


## Cloud providers

Various cloud providers will be used to deploy the app.

### Railway.app

[Railway.app](https://railway.app?referralCode=kanezi) is used as one of the cloud providers to deploy our app.

After provisioning Postgres instance on railway.app following dynamic url can be used to connect spring application to database:


| property | value                                                                                     |
|:---------|:------------------------------------------------------------------------------------------|
| DB_USER  | ```${{Postgres.PGUSER}}```                                                                |
| DB_PASS  | ```${{Postgres.PGPASSWORD}}```                                                            |
| DB_URL   | ```jdbc:postgresql://${{Postgres.PGHOST}}:${{Postgres.PGPORT}}/${{Postgres.PGDATABASE}}```|


