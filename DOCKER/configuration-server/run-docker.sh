#!/bin/bash
			docker run -d -p 9000:9000 \
			-v /home/gmaglio/Documenti/WS_TODOS/WS_MFJG/microservice-configuration:/home/gmaglio/Documenti/WS_TODOS/WS_MFJG/microservice-configuration \
			--log-driver json-file \
			--log-opt max-size=20m \
			--log-opt max-file=25 \
			--name configuration-server \
			microservices-study/configuration-server