# Stage 1: Build JAR với Maven (nếu muốn build trong Docker)
FROM maven:3.9.3-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml và tải dependencies trước (caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy toàn bộ source code
COPY src ./src

# Build JAR
RUN mvn clean package -DskipTests

# Stage 2: Chạy JAR với JDK 21
FROM eclipse-temurin:21-jdk-jammy

# Tạo thư mục app
WORKDIR /app

# Copy JAR từ stage build
COPY --from=build /app/target/supportkarateapi-0.0.1-SNAPSHOT.jar ./app.jar

# Expose port Spring Boot mặc định
EXPOSE 8080

# Command chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]