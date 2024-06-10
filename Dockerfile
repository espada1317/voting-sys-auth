FROM maven:latest AS build
WORKDIR /home/app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "cloud-app.jar"]
