#!/bin/bash
echo 'May need runnning with sudo if user group not set for docker...'
docker run -v /home/colon/workspace/:/etc/colon -d -p 7777:7777 colon/testerka