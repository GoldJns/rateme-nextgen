
#kubectl create configmap prometheus-config --from-file prometheus/prometheus.yaml >> Only one time

echo "Deploy monitoring stack..."

echo "Deploy prometheus..."
prometheusDir=./prometheus

kubectl apply -f $prometheusDir/prometheus-deployment.yaml
kubectl apply -f $prometheusDir/prometheus-service.yaml
kubectl apply -f $prometheusDir/prometheus-roles.yaml

