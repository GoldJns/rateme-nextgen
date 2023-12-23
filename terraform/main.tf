    terraform {
      backend "gcs" {
        bucket = "terraform-state-rateme-nextgen"
        prefix = "prod"
      }
    }

    provider "google" {
      project = var.project
      region  = var.region
    }

resource "google_container_cluster" "gke_cluster" {
  name                     = var.cluster-name
  location                 = var.region
  remove_default_node_pool = true
  initial_node_count       = 1
}



module "namespace" {
  source  = "blackbird-cloud/gke-namespace/google"
  version = "~> 1"

  cluster_name = "my-cluster"
  location     = "europe-west4"

  name = "mynamespace"

  labels = {
    my = "label"
  }
  annotations = {
    my = "annotation"
  }
}


resource "google_container_node_pool" "gke_nodes" {
  name       = "nodes"
  location   = var.region
  cluster    = google_container_cluster.gke_cluster.name
  node_count = 1
  node_config {
    preemptible  = true
    machine_type = "g1-small"
    oauth_scopes = [
      "https://www.googleapis.com/auth/cloud-platform"
    ]
  }
}
