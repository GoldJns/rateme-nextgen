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
  deletion_protection = false
}



provider "kubernetes" {
  host = "https://${google_container_cluster.primary.endpoint}"
  username = "${google_container_cluster.primary.master_auth.0.username}"
  password = "${google_container_cluster.primary.master_auth.0.password}"
  client_certificate = "${base64decode(google_container_cluster.primary.master_auth.0.client_certificate)}"
  client_key = "${base64decode(google_container_cluster.primary.master_auth.0.client_key)}"
  cluster_ca_certificate = "${base64decode(google_container_cluster.primary.master_auth.0.cluster_ca_certificate)}"
}

resource "kubernetes_namespace" "n" {
  metadata {
    name = "dev"
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
