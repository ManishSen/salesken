FROM openjdk:8-jdk-alpine
ADD target/accomodation-service-0.0.1-SNAPSHOT.jar accomodation-service-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "accomodation-service-0.0.1-SNAPSHOT.jar"]