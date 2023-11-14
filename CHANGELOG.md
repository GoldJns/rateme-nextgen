# Changelog

All notable changes to this project will be documented in this file.

## [1.0] - 2023-09-11

### Features Added

#### Frontend

- Display all pois on the map.
- Display information of a poi when it is selected.

#### Backend

- Added the following classes:
  - `PoiController` Class
  - `Poi` Java Entity Class representing the Poi Table in the Database (`rateme_poi`)
  - `PoiTag` Java Entity Class representing the PoiTag Table in the Database (`rateme_poi_tag`)
  - `Position` Class in the Model Package (DTO for Position Attribute in the Database)
  - `PositionConverter` Class in the Converter Package (Java Class implementation for the Position Class)
  - `PoiDAO` Interface in the dao Package (Java Interface extended JpaRepository for Poi Entity)
  - `PoiTagRepository` Interface in the dao Package.

#### Database

- Adjusted Configuration in `application.properties`:
  - Added Datasource with the following values:
    - `spring.datasource.url=jdbc:mysql://database:3306/rateme_swtp`
    - `spring.datasource.username=root`
    - `spring.datasource.password=`
    - `spring.jpa.hibernate.ddl-auto=none`
    - `spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`
    - `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect`

**Note:** In Docker, the SQL server name is `(database)`, and on the Local Machine, the SQL server name is `(localhost)`.

