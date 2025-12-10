# Dockerfile
FROM eclipse-temurin:17-jdk-jammy

# Crear directorio de trabajo
WORKDIR /app


# Copiar el archivo de tareas pendientes al directorio de trabajo
COPY tareas_pendientes.json ./
COPY target/TareasPendientes-1.0.0.jar app.jar

# Exponer puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]