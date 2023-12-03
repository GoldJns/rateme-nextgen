#/bin/sh

echo "Deploy services using helm..."

NAMESPACE="default"

MODE=upgrade

UI_TAG=0.1
BACKEND_TAG=0.1
DATABASE_TAG=0.1


helm $MODE ui-release ./charts/ui \
    --namespace $NAMESPACE \

helm $MODE backend-release ./charts/backend \
    --namespace $NAMESPACE \

helm $MODE database-release ./charts/database \
    --namespace $NAMESPACE \

$SHELL