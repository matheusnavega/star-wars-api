FROM openjdk:8-alpine
MAINTAINER Matheus Navega

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY target/starWars-*.jar $PROJECT_HOME/app.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./app.jar"]
