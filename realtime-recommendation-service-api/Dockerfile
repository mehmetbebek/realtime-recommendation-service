ARG COMPILE_IMAGE="maven:3.8.4-openjdk-17-slim"

FROM ${COMPILE_IMAGE} as maven_build

WORKDIR /app

COPY .. /app

RUN mvn clean install -DskipTests

EXPOSE 8002

CMD ["java", "-jar", "target/realtime-recommendation-service-api-0.0.1-SNAPSHOT.jar"]