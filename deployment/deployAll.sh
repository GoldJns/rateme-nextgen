#/bin/sh

echo "Deploy services..."

NAMESPACE="default"

helm install ui-release ./charts/ui \
    --namespace $NAMESPACE \

helm install backend-release ./charts/backend \
    --namespace $NAMESPACE \

helm install database-release ./charts/database \
    --namespace $NAMESPACE \

$SHELL