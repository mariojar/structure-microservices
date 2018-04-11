#!/bin/bash

			docker run -d -p 8762:8762 \
			--log-driver json-file \
			--log-opt max-size=20m \
			--log-opt max-file=25 \
			--name eureka-server-node2 \
			microservices-study/eureka-server
