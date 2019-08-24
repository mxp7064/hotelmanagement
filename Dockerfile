FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9090
COPY target/hotelmanagement-0.0.1-SNAPSHOT-spring-boot.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]