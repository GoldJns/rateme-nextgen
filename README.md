# rateme-nextgen



## Build

Run `./mvnw clean package` to build the project.

## Run

Ensure you have rights to execute start script
```sh
  sudo chown +x start.sh   
```

This commmand will start the springboot application and setup additional services e.g. monitoring
```sh
  ./start.sh
```


## Docker

Build a new image:

```sh
  docker build -t <image-name>:<tag> .
```

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
- loki on port `3100`

## Credits
This project includes code adapted from [rateme](https://github.com/alex9849/rateme) by [alex9849](https://github.com/alex9849) 


