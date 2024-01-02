#!/bin/bash

echo "Deploying services using Helm..."

if [ -z "$1" ]; then
  NAMESPACE="default"
else
  NAMESPACE="$1"
fi


echo "Using namespace $1"
deploy_with_helm() {
    local release_name=$1
    local chart_path=$2
    echo "Deploy $release_name"
    helm upgrade --install $release_name $chart_path --namespace $NAMESPACE
}

deploy_with_helm "main-release" "./charts/parent"


$SHELL