#/bin/sh

echo "Deploy services..."
kubectl apply -f mysql.yaml
kubectl apply -f mysql-service.yaml
kubectl apply -f backend.yaml
kubectl apply -f backend-service.yaml
kubectl apply -f ui.yaml
kubectl apply -f ui-service.yaml
