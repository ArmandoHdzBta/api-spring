FROM eclipse-temurin:17 as builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --form=builder /app/tarjet/*.jar app.jar

EXPOSE 8080