#Drone-Backend

1.Set Up Java 17 SDK

2.Install docker

3.Clone the project from url https://github.com/hemanta001/drone

4.open the project

5.From Root path of project, Run 'docker-compose -f docker-compose-mysql.yml up' for running mysql. This will automatically create database named 'drones'

6.open application.properties file and update the environment variables

7.From Root path of project , Run './gradlew build' cmd to build application

8.From Root path of project , Run './gradlew bootRun' cmd to run application

9.Application Serves on http://localhost:8080