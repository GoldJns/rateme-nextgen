# rateme-nextgen

<img src="docs/logo.png" />

### Cloud Technologies
![docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)
![kubernetes](https://img.shields.io/badge/kubernetes-326ce5.svg?&style=for-the-badge&logo=kubernetes&logoColor=white)
![helm](https://img.shields.io/badge/Helm-0F1689?style=for-the-badge&logo=Helm&labelColor=0F1689)
![terraform](https://img.shields.io/badge/Terraform-7B42BC?style=for-the-badge&logo=terraform&logoColor=white)
![Google Cloud](https://img.shields.io/badge/GoogleCloud-%234285F4.svg?style=for-the-badge&logo=google-cloud&logoColor=white)

### Frontend Technologies
![html](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![css](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![js](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)


### Backend Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![springboot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

### Monitoring & Tools
![grafana](https://img.shields.io/badge/Grafana-F2F4F9?style=for-the-badge&logo=grafana&logoColor=orange&labelColor=F2F4F9)
![prometheus](https://img.shields.io/badge/Prometheus-000000?style=for-the-badge&logo=prometheus&labelColor=000000)




Discover great restaurants with ease! Use our map 🗺️ to find amazing places, see what others think with star ratings ⭐, and share your own reviews hassle-free. Sign up quickly and start rating your favorite spots right away 🚀. Add photos to your reviews 📸 and manage them easily. This project involves migrating an existing application to the cloud ☁️ 

## Instances

Running Instances are available:

- Dev: [https://dev.rateme-nextgen.com](https://dev.rateme-nextgen.com)
- Prod: [https://prod.rateme-nextgen.com](https://prod.rateme-nextgen.com)
- Monitoring dashboards [https://monitoring.rateme-nextgen.com](https://monitoring.rateme-nextgen.com)

## Installation

The Installation and development process is documented in [INSTALLATION.md](./docs/INSTALLATION.md)

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

Take a look at the dashboard [http://monitoring.rateme-nextgen.com](http://monitoring.rateme-nextgen.com)


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

## IaC

We use terraform to provision our cloud ressources(e.g. gke cluster). Terraform files are located in "./terraform" folder.

## Credits
This project includes code adapted from [rateme](https://github.com/alex9849/rateme) by [alex9849](https://github.com/alex9849) 

## Sponsor
<a href="https://www.buymeacoffee.com/goldjns" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: 41px !important;width: 174px !important;box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;-webkit-box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;" ></a>
