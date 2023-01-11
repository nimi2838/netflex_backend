FROM openjdk:17-alpine
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE_PATH=*.jar
COPY ${JAR_FILE_PATH} sbb-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "sbb-0.0.1-SNAPSHOT.jar"]