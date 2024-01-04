#!/bin/bash

echo "Deploying services using Helm..."

NAMESPACE="${1:-default}"  # Using parameter expansion to set NAMESPACE

echo "Using namespace $NAMESPACE"

deploy_with_helm() {
    local release_name="$1"
    local chart_path="$2"
    echo "Deploy $release_name"
    helm upgrade --install "$release_name" "$chart_path" --namespace "$NAMESPACE"
}

deploy_with_helm "main-release" "./charts/parent"