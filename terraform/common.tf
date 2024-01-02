resource "google_service_account" "gke_prod_service_account" {
  account_id   = "gke-prod-service-account"
  display_name = "GKE Prod Service Account"
  project      = "rateme-nextgen"
}


resource "google_project_iam_binding" "secretmanager_access_binding" {
  project = "rateme-nextgen"
  role    = "roles/secretmanager.secretAccessor"

  members = [
    "serviceAccount:${google_service_account.gke_prod_service_account.email}"
  ]
}



