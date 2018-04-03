#!/bin/bash

			docker run -d -p 8761:8761 \
			--log-driver json-file \
			--log-opt max-size=20m \
			--log-opt max-file=25 \
			--name eureka-server \
			microservices-study/eureka-server
