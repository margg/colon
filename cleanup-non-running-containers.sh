#!/bin/bash
echo 'May need runnning with sudo if user group not set for docker...'
docker rm -v $(docker ps -a -q)