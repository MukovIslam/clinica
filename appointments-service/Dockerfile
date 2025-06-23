# 1. Берём базовый образ с Java 17
FROM openjdk:17-jdk-slim

# 2. Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# 3. Копируем .jar файл из проекта внутрь контейнера
COPY target/appointments-service-0.0.1-SNAPSHOT.jar app.jar

# 4. Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]