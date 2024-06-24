FROM gradle:8.4.0-jdk17  AS build

WORKDIR /app
COPY . .

RUN gradle :composeApp:wasmJsBrowserDistribution --no-daemon

FROM nginx:alpine
COPY --from=build /app/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html
EXPOSE 80
CMD nginx -g 'daemon off;'
