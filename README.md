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