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

## Features Added
-Display all pois in map
-Display information of poi when it selectd
#  Backend
## Classes Added
### poicontroller class in api package
A java Rest Controller Class have two Autowired object:
-PoiDAO PoiDaorepo
-PoiTagRepository Poitagrepo
and have two functions for Get mapping requestes:
-getAllPois : which do not  take any arguments and retrun Response Entity have List of Poi Entity-Modell in it's Body
-getTagsByOsmId:which takes Long argument (osmid) and return Responce Entity have List of PoiTag Entity-Modell in it's Body
### Poi Class in Model Package
A java Entity Class resperent Poi Table in Database (rateme_poi) 
### poiTag Class in Model Package
A java Entity Class resperent PoiTag Table in Database (rateme_poi_tag)
### POiTagId Class in Model Package
A java Class which is IDENTITY for PoiTag Class 
### Position Class in Model Package
A java Class which is dto for Position Attribuite in Database
### PositionConverter Class in Converter Package
A java Class implementation Position Class 
### PoiDAO Interface in dao Package
A java Interface extended JpaRepository for Poi Entity
### PoiTagRepository Interface in dao Package
A java Interface extended JpaRepository for PoiTag Entity and have Function to return List of PoiTag by osmid

## Database Configuration
-in application.properties added Datasource with Value: 
spring.datasource.url=jdbc:mysql://database:3306/rateme_swtp
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=none
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
Note: in Docker sql_server name is (database) in Lockal Maschine sql_server name is (localhost)
## Dependencies 
-mysql-connector-java
-spring-boot-starter-data-jpa
#  Frontend
## Files Added 
-Added Icons images in Icons Folder for Marker Pois in Maps
## Changes:
-Hide aside Content to display Poi Information when it selected 
-Added div for Poi Information with css Styles
-Added js Function to get Poi Info.


## Credits
This project includes code adapted from [rateme](https://github.com/alex9849/rateme) by [alex9849](https://github.com/alex9849) 


