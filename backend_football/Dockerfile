FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/backend_football.jar backend_football.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","backend_football.jar"]