#!/bin/bash

echo "Deploying services using Helm..."

NAMESPACE="default"

deploy_with_helm() {
    local release_name=$1
    local chart_path=$2

    helm upgrade --install $release_name $chart_path --namespace $NAMESPACE
}

deploy_with_helm "ui-release" "./charts/ui"
deploy_with_helm "backend-release" "./charts/backend"
deploy_with_helm "database-release" "./charts/database"
deploy_with_helm "common-release" "./charts/common"