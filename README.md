# rateme-nextgen



## Build

Run `./mvnw clean package` to build the project.

## Docker

Build a new image:

```sh
  docker build -t <image-name>:<tag> .
`````

Run image:
```sh
  docker run  -d -p 8080:8080 <image-name>:<tag>
```

## Observability

Follow guide to start the project:

Go to path: `./rateme/monitoring`

```sh
  docker compose up --build
```

Following services will start:

- prometheus on port `9090`
- grafana on port `3000`
- node-exporter on port `9100`
- springboot app on port `8080`
