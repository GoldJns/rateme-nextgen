#!/bin/bash

echo "Downing running services"
docker compose down


echo "Starting springboot and monitoring services parallel..."
docker compose up & docker compose  -f ./monitoring/compose/docker-compose-monitoring.yml up

$SHELL