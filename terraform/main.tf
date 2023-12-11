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


resource "google_service_account" "gke_sa" {
  account_id   = "gke-cluster"
  display_name = "Service Account"
}

resource "google_container_cluster" "gke_cluster" {
  name     = "rateme-nextgen-cluster"
  location = "US_CENTRAL1"

  remove_default_node_pool = true
  initial_node_count       = 1
}

resource "google_container_node_pool" "gke_nodes" {
  name       = "nodes"
  location   = "USE_CENTRAL1"
  cluster    = google_container_cluster.gke_cluster.name
  node_count = 1
  version    = "1.23.8"

  node_config {
    preemptible     = true
    machine_type    = "e2-small"
    service_account = google_service_account.gke_sa.email
    oauth_scopes = [
      "https://www.googleapis.com/auth/cloud-platform"
    ]
  }
}
