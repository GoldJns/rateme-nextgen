#!/bin/bash

echo "Deploying services using Helm..."

NAMESPACE="${1:-default}"  # Using parameter expansion to set NAMESPACE

echo "Using namespace $NAMESPACE"

deploy_with_helm() {
    local release_name="$1"
    local chart_path="$2"
    local db_password="$3"
    local db_root_password="$4"
    echo "Deploy $release_name"
    helm upgrade --install "$release_name" "$chart_path" --namespace "$NAMESPACE" --set db_password="$db_password"
}

deploy_with_helm "main-release" "./charts/parent"