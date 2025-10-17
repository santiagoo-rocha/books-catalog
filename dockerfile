FROM eclipse-temurin:17-jre
WORKDIR /app

ARG JAR=docker/app.jar
COPY ${JAR} /app/app.jar

ENV PORT=8080
EXPOSE 8080
CMD ["java","-Dserver.port=${PORT}","-jar","/app/app.jar"]