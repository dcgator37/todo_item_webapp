FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY target/todo-item_webapp.war app.war
CMD ["java", "-jar", "app.war"]