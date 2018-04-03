#!/bin/bash

echo "Running container for traductor-spanish..."
			docker run -d -p 8082:8102 \
			--log-driver json-file \
			--log-opt max-size=20m \
			--log-opt max-file=25 \
			--name italian-traductor \
			microservices-study/traductor-italian
echo "traductor-spanish is now running"