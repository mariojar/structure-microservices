#!/bin/bash


			docker run -d -p 8083:8080 \
			--log-driver json-file \
			--log-opt max-size=20m \
			--log-opt max-file=25 \
			--name client-traductor \
			microservices-study/client-traductor
