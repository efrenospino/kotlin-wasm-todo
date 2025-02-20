FROM gradle:8.4.0-jdk17 AS builder

ARG DATABASE_PATH
ENV DATABASE_PATH=${DATABASE_PATH}

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle :server:assemble --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /home/gradle/src/server/build/ /app

EXPOSE 8080

CMD ["java", "-jar", "/app/libs/server-all.jar"]
