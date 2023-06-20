FROM openjdk:19
COPY target/gih-back-1.0-SNAPSHOT-shaded.jar app.jar
#EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]