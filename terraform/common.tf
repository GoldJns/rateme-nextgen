resource "google_service_account" "gke_prod_service_account" {
  account_id   = "gke-prod-service-account"
  display_name = "GKE Prod Service Account"
  project      = "rateme-nextgen"
}

resource "kubernetes_service_account" "prod_service_account" {
  metadata {
    name      = "prod-service-account"
    namespace = "prod"

    annotations = {
      "iam.gke.io/gcp-service-account" = "${google_service_account.gke_prod_service_account.email}"
    }
  }
}


resource "google_project_iam_binding" "secretmanager_access_binding" {
  project = "rateme-nextgen"
  role    = "roles/secretmanager.secretAccessor"

  members = [
    "serviceAccount:${google_service_account.gke_prod_service_account.email}"
  ]
}



