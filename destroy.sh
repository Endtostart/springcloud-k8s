#!/bin/bash
for var in "$@"
do
	kubectl delete deployments $var
	kubectl delete svc $var
	kubectl delete ing "ingress-"$var
done
