#!/bin/bash
echo "Deploy monitoring stack..."

echo "Install helm repos"
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update

echo "Install monitoring-release"

helm upgrade --install monitoring-release prometheus-community/kube-prometheus-stack

