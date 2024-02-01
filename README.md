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
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)


### Backend Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![springboot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

### Monitoring & Tools
![grafana](https://img.shields.io/badge/Grafana-F2F4F9?style=for-the-badge&logo=grafana&logoColor=orange&labelColor=F2F4F9)
![prometheus](https://img.shields.io/badge/Prometheus-000000?style=for-the-badge&logo=prometheus&labelColor=000000)
[![Quality gate](http://sonar.rateme-nextgen.com/api/project_badges/quality_gate?project=rateme-nextgen)](http://sonar.rateme-nextgen.com/dashboard?id=rateme-nextgen)



Discover great restaurants with ease! Use our map 🗺️ to find amazing places, see what others think with star ratings ⭐, and share your own reviews hassle-free. Sign up quickly and start rating your favorite spots right away 🚀. Add photos to your reviews 📸 and manage them easily. This project involves migrating an existing application to the cloud ☁️ 

## Instances

Running Instances are available:

- Dev: [https://dev.rateme-nextgen.com](https://dev.rateme-nextgen.com)
- Prod: [https://prod.rateme-nextgen.com](https://prod.rateme-nextgen.com)
- Monitoring dashboards [https://monitoring.rateme-nextgen.com](https://monitoring.rateme-nextgen.com)
- Swagger Dev:[https://dev.rateme-nextgen.com/swagger-ui/index.html](https://dev.rateme-nextgen.com/swagger-ui/index.html)
- Swagger Prod:[https://prod.rateme-nextgen.com/swagger-ui/index.html](https://prod.rateme-nextgen.com/swagger-ui/index.html)

## Installation

The Installation and development process is documented in [INSTALLATION.md](./docs/INSTALLATION.md)


## Observability

Take a look at the dashboard [http://monitoring.rateme-nextgen.com](http://monitoring.rateme-nextgen.com)


## Sonarqube

We are hosting an instance running on k8s: [http://sonar.rateme-nextgen.com](http://sonar.rateme-nextgen.com)

Repository: [https://github.com/GoldJns/rateme-nextgen-sonarqube](https://github.com/GoldJns/rateme-nextgen-sonarqube)

To ensure code quality and reduce code smells, we setup quality gates that will lead to a failed  pipeline and prevent deployment

## Services

The backend consists of 3 services:

`Poi Service`: The Poi Service manages locations of the restaurants.

`User Service`: The User Service handles user-related operations, including authentication, user profiles, and access control within the platform.

`Rating Service`: The Rating Service focuses on managing and storing ratings and reviews provided by users for different points of interest or establishments.


## Changelog/Releasenotes

The Release notes are pusblished as artifacts of a github release.
Take a look here: [Releasenotes](https://github.com/GoldJns/rateme-nextgen/releases)

=> [Changelog](/CHANGELOG.md)

## CI/CD

### Terraform Workflows
- `Terraform create` to provision cluster and nodes
- `Terraform destroy` to delete cluster and nodes

### Build-push-image Workflow
We use Github Actions to build the projects/dockerfiles and pusht them to the Github Container Registry.
Currently there are 3 published containers:
- `rateme-nextgen-ui`
- `rateme-nextgen-backend`
- `rateme-nextgen-database`

### Deployment Workflows
We are deploying the changes via helm.
- helm-deployment workflow 
This workflow is templated and is called for each environment

### Github environments

We use Github environments to store environment specific data in objects like variables/environment secrets
e.g. the name of the namespace or the db secret.


## IaC

We use terraform to provision our cloud ressources(e.g. gke cluster). Terraform files are located in "./terraform" folder.

## Credits
This project includes code adapted from [rateme](https://github.com/alex9849/rateme) by [alex9849](https://github.com/alex9849). 

Thanks to the prometheus communuity for providing helpful helmcharts [kube-prometheus-stack](https://github.com/prometheus-community/helm-charts/tree/main/charts/kube-prometheus-stack ).

When it comes to terraform and gke we can recommand this guide which helped us a lot: [https://dev.to/admantium/google-kubernetes-engine-mostly-automated-installation-with-terraform-47dg](https://dev.to/admantium/google-kubernetes-engine-mostly-automated-installation-with-terraform-47dg) 

This guide is also very good:[cert-manager-gke-guide](https://cert-manager.io/docs/tutorials/getting-started-with-cert-manager-on-google-kubernetes-engine-using-lets-encrypt-for-ingress-ssl/#7-create-an-issuer-for-lets-encrypt-staging)