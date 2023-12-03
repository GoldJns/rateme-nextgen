#/bin/sh

echo "Deploy services using helm..."

NAMESPACE="default"

UI_TAG=0.1
BACKEND_TAG=0.1
DATABASE_TAG=0.1


helm upgrade ui-release ./charts/ui \
    --namespace $NAMESPACE \

helm upgrade backend-release ./charts/backend \
    --namespace $NAMESPACE \

helm upgrade database-release ./charts/database \
    --namespace $NAMESPACE \

$SHELL