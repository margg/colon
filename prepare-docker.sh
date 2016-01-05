#!/bin/bash
echo 'May need runnning with sudo if user group not set for docker...'
docker build --no-cache -t colon/testerka .