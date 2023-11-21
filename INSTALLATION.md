# Installationguide

# Prerequisites

Before starting with this project, ensure you have the following software installed and properly configured:

## Docker

- **Installation**: Docker allows you to package, distribute, and run applications in containers.
- **Operating System**: Compatible with Windows, macOS, and various Linux distributions.

### Installation Guides

- [Docker Installation Guide](https://docs.docker.com/get-docker/)
  - Follow the guide specific to your operating system for Docker installation.

## Docker Compose

- **Purpose**: Docker Compose is a tool for defining and running multi-container Docker applications.
- **Included**: Often included in Docker Desktop for Windows and macOS.


### kubectl

- **Purpose**: `kubectl` is a command-line tool for interacting with Kubernetes clusters.
- **Installation Guides**: 

  - [kubectl Installation Guide](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
    - Follow the guide specific to your operating system for `kubectl` installation.


### Installation Guides

- [Docker Compose Installation Guide](https://docs.docker.com/compose/install/)
  - Follow the guide to install Docker Compose if not included in your Docker installation.

## Java Development Kit (JDK)

- **Purpose**: Java Development Kit is required for running Java applications.

### Installation Guides

- [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://adoptopenjdk.net/)
  - Download and install the JDK appropriate for your operating system.

### Verify Installation

After installation, ensure Docker, Docker Compose, and Java are properly installed by checking their versions using the following commands:

- Docker: `docker --version`
- Docker Compose: `docker-compose --version`
- Java: `java -version`
- kubectl: `kubectl version --client `


### Maven

No further need to install maven. We suggest using the maven wrapper defined in `./rateme/` folder


## Build

Run `./mvnw clean package` to build the project.

## Run

Ensure you have rights to execute start script e.g with linux
```sh
  sudo chown +x start.sh   
```

This commmand will run the two docker-compose projects(Main Project and Monitoring project)
```sh
  ./start.sh
```

## Development

In development or testing it makes sense to start project manually with docker compose. 
Just run 
```sh
  docker compose up --build
```
to rebuild containers after changes.

Alternatively you can start services like db and frontend with docker compose
```sh
  docker compose up database frontend --build
```
and start springboot manually via terminal or vs code extension.

Make sure to run ./mvnw clean package from time to time
