# Docker

## Comandos:

Limpiar toda la estancia de Docker para dejar espacio:

    docker rm -f $(docker ps -a -q)

    docker image rm -f $(docker images -a -q)

    docker volume rm $(docker volume ls -q)

Levantar un compose viendo la salida de la terminal

    docker compose up

Levantar un compose en background

    docker compose up -d

Levantar un compose en background y antes construir las imágenes

    docker compose up -d --build

Parar el compose

    docker compose down

Pullear las imagenes del compose

    docker compose pull

Obtener la IP de un contenedor

    docker inspect <containerId> | grep IPAddress
