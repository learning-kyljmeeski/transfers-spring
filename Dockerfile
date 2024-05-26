FROM openjdk:17.0.1
WORKDIR /opt/app
COPY target/*.jar app.jar
ENTRYPOINT [ "java", "-jar",  "app.jar"]