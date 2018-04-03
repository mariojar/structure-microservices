#!/bin/bash

			docker run -d -p 8081:8101 \
			--log-driver json-file \
			--log-opt max-size=20m \
			--log-opt max-file=25 \
			--name spanish-traductor \
			microservices-study/traductor-spanish