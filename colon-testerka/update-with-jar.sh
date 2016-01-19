#!/bin/bash

echo "Downloading file $1..."

curl -o colon-testerka-jar-with-dependencies.jar $1

echo "Building docker image..."

docker build -t sjchmiela/colon-testerka .

sudo systemctl restart colon-testerka
