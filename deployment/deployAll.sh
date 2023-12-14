#/bin/sh

echo "Deploy services using helm..."

NAMESPACE="default"

MODE=upgrade

helm $MODE ui-release ./charts/ui \
    --namespace $NAMESPACE \

helm $MODE backend-release ./charts/backend \
    --namespace $NAMESPACE \

helm $MODE database-release ./charts/database \
    --namespace $NAMESPACE \

kubectl apply -f ingress.yaml
$SHELL