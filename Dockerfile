FROM gradle:8.4.0-jdk17 AS builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle wasmJsBrowserDistribution --no-daemon

FROM nginx:alpine

COPY --from=builder /home/gradle/src/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]