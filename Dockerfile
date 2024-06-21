FROM gradle:8.4.0-jdk17  AS build

ARG SERVER_SCHEMA=http
ARG SERVER_HOST=0.0.0.0
ARG SERVER_PORT=8080

ENV SERVER_SCHEMA=${SERVER_SCHEMA}
ENV SERVER_HOST=${SERVER_HOST}
ENV SERVER_PORT=${SERVER_PORT}

WORKDIR /app
COPY . .

RUN gradle :server:installDist :composeApp:wasmJsBrowserDistribution --no-daemon 

FROM nginx:alpine
RUN apk add --no-cache openjdk17-jre
COPY --from=build /app/server/build/install/server /app/server
COPY --from=build /app/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html
EXPOSE 80 8080
CMD /app/server/bin/server & nginx -g 'daemon off;'
