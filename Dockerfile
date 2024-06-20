FROM openjdk:17.0.2-jdk-slim AS build
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew :server:installDist --no-daemon
RUN ./gradlew :composeApp:wasmJsBrowserDistribution --no-daemon

FROM nginx:alpine
RUN apk add --no-cache openjdk17-jre
COPY --from=build /app/server/build/install/server /app/server
COPY --from=build /app/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html
EXPOSE 80 8080
CMD /app/server/bin/server & nginx -g 'daemon off;'
