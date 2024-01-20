#!/bin/bash

echo "Downing running services"
docker compose down


echo "Starting springboot services "
docker compose up --build

$SHELL