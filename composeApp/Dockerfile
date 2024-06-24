FROM gradle:8.4.0-jdk17 AS builder

ARG PORT
ARG API_URL

ENV API_URL=${API_URL}

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle wasmJsBrowserDistribution --no-daemon

FROM nginx:alpine

COPY --from=builder /home/gradle/src/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html

EXPOSE $PORT
CMD ["nginx", "-g", "daemon off;"]
