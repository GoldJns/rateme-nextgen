# Loki Grafana Installation with helm

helm show values grafana/loki-distributed > loki-distributed-overrides.yaml

helm upgrade --install --values loki-distributed-overrides.yaml loki grafana/loki-distributed -n grafana-loki --create-namespace