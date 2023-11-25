FROM openjdk:17
EXPOSE 8083

ADD target/MicroService-0.0.1-SNAPSHOT.jar MicroService.jar
ENTRYPOINT ["java","-jar","MicroService.jar"]