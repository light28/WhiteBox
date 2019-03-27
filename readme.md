# Accounts application
Server side rendering spring-boot app with unit, integration and e2e tests


# Initialize local environment

**1. Install JDK 8**

**2. install gradle**

**3. Install MySQL 5.7 Server**

**3.1. Create database named 'accounts'** 

Edit local application configuration (we need to tell our application what user name and password can be used for database access):
```
/accounts/src/main/resources/application.properties
```

```
spring.datasource.url=jdbc:mysql://localhost:3306/accounts
spring.datasource.username=root
spring.datasource.password=
```


Start application:
```bash

Unix system (Ubuntu, Mac):
./gradlew bootRun

Windows:
gradlew.bat bootRun

```

Application starts on:
```
http://localhost:8080
```

Configure webdriver path:

Download chrome webdriver:

http://chromedriver.chromium.org/downloads

```
copy webdriver.exe/webdriver to /drivers

In application properties:
/accounts/src/main/resources/application.properties

Set path to chromedriver
webdriver.path=drivers/chromedriver

Set path to chromedriver
webdriver.path=drivers/chromedriver.exe
```

Run tests:
```
Unit tests:
./gradlew clean test

Unit + Integration tests:
./gradlew clean integrationTest

Unit + Integration + e2e tests:
./gradlew clean e2e

To run e2e tests in chrome headless mode
./gradlew clean e2e -De2e.headless=true
```


Default credentials:
```
username: tommy.johnson@gmail.com
password: 1111
```
