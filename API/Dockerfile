FROM openjdk:8-jdk-alpine
COPY ./target/ticket-manager-*.jar /ticket-manager.jar
ENTRYPOINT ["java", "-jar", "/ticket-manager.jar"]