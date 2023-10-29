FROM openjdk:17-jdk-alpine
VOLUME [ "/tmp" ]
COPY target/rateme-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]