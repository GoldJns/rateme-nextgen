resource "google_service_account" "gke_prod_service_account" {
  account_id   = "gke-prod-service-account"
  display_name = "GKE Prod Service Account"
  project      = "rateme-nextgen"
}

resource "google_service_account" "gke_dev_service_account" {
  account_id   = "gke-dev-service-account"
  display_name = "GKE Dev Service Account"
  project      = "rateme-nextgen"
}

resource "google_project_iam_binding" "secretmanager_access_binding_prod" {
  project = "rateme-nextgen"
  role    = "roles/secretmanager.secretAccessor"

  members = [
    "serviceAccount:rateme-nextgen.svc.id.goog[prod/prod-service-account]"
  ]
}

resource "google_project_iam_binding" "secretmanager_access_binding_dev" {
  project = "rateme-nextgen"
  role    = "roles/secretmanager.secretAccessor"

  members = [
    "serviceAccount:rateme-nextgen.svc.id.goog[dev/dev-service-account]"
  ]
}


resource "google_project_iam_binding" "secretmanager_workload_binding_prod" {
  project = "rateme-nextgen"
  role    = "roles/iam.workloadIdentityUser"

  members = [
    "serviceAccount:rateme-nextgen.svc.id.goog[prod/prod-service-account]"
  ]
}

resource "google_project_iam_binding" "secretmanager_workload_binding_dev" {
  project = "rateme-nextgen"
  role    = "roles/iam.workloadIdentityUser"
  members = [
    "serviceAccount:rateme-nextgen.svc.id.goog[dev/dev-service-account]"
  ]
}
