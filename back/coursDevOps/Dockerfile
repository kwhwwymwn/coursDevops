FROM openjdk:21
WORKDIR /app
COPY /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
