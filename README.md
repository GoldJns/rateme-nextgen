# rateme-nextgen

Discover great restaurants with ease! Use our map 🗺️ to find amazing places, see what others think with star ratings ⭐, and share your own reviews hassle-free. Sign up quickly and start rating your favorite spots right away 🚀. Add photos to your reviews 📸 and manage them easily. This project involves migrating an existing application to the cloud ☁️ 


## Installation

The Installation and development process is documented in [INSTALLATION.md](https://github.com/GoldJns/rateme-nextgen/blob/main/INSTALLATION.md)

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

The whole setup is monitored with prometheus, loki and node exporter.
Metrics and logs are visualized using Grafana


## Services

The backend consists of 3 services:

`Poi Service`: The Poi Service manages locations of the restaurants.

`User Service`: The User Service handles user-related operations, including authentication, user profiles, and access control within the platform.

`Rating Service`: The Rating Service focuses on managing and storing ratings and reviews provided by users for different points of interest or establishments.


## Changelog/Releasenotes

The Release notes are pusblished as artifacts of a github release.
Take a look here: [Releasenotes](https://github.com/GoldJns/rateme-nextgen/releases)

## CI/CD

We use Github Actions to build the projects/dockerfiles and pusht them to the Github Container Registry.
Currently there are 3 published containers:
- `rateme-nextgen-ui`
- `rateme-nextgen-backend`
- `rateme-nextgen-database`

## Credits
This project includes code adapted from [rateme](https://github.com/alex9849/rateme) by [alex9849](https://github.com/alex9849) 


