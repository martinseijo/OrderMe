# Usar una imagen oficial de Maven para compilar el proyecto
FROM maven:3.8.5-openjdk-17 AS build

# Establecer el directorio de trabajo para la compilación
WORKDIR /app

# Copiar los archivos de configuración y código fuente
COPY pom.xml .
COPY src ./src

# Construir el archivo JAR
RUN mvn clean package -DskipTests

# Usar una imagen oficial de Java para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo para la ejecución
WORKDIR /app

# Copiar el JAR compilado desde la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
