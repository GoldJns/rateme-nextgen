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
  initial_node_count       = 0
}

resource "google_container_node_pool" "gke_nodes" {
  name       = "nodes"
  location   = var.region
  cluster    = google_container_cluster.gke_cluster.name
  node_count = 1
  version    = "1.23.8"

  node_config {
    preemptible     = true
    machine_type    = "g1-small"
    service_account = google_service_account.gke_sa.email
    oauth_scopes = [
      "https://www.googleapis.com/auth/cloud-platform"
    ]
  }
}
