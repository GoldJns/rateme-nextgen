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

### Container:

Following parts are containerized:
-  frontend (nginx image)
-  backend (openjdk image)
-  database (mysql image)

### Docker compose:
There are two docker compose projects:

1. Monitoring project:
  - Responsible for starting containers related to monitoring
2. Main Project
  - Used for local development, starts database frontend and backend


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


## Changelog/Releasenotes

The Release notes are pusblished as artifacts of a github release.
Take a look here: [Releasenotes](https://github.com/GoldJns/rateme-nextgen/releases)

## Credits
This project includes code adapted from [rateme](https://github.com/alex9849/rateme) by [alex9849](https://github.com/alex9849) 


