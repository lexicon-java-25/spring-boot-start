
# -------- build stage ------------
FROM eclipse-temurin:17-ubi9-minimal AS builder

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY src ./src



RUN ./mvnw clean package -DskipTests

# --------- runtime stage -------------
FROM eclipse-temurin:17-ubi9-minimal

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]



