FROM openjdk:19
COPY target/
ENTRYPOINT ["java","-jar","/app.jar"]