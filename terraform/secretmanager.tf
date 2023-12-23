resource "google_secret_manager_secret_iam_member" "secret_accessor" {
  project   = var.project
  secret_id = google_secret_manager_secret.my_secret.secret_id
  role      = "roles/secretmanager.secretAccessor"
  member    = "${google_service_account.gke_prod_service_account.email}"
  }
resource "google_secret_manager_secret" "my_secret" {
  project   = var.project
  replication {
    
  }
  secret_id = "my_secret"
  labels    = "rateme-nextgen-secret"
}

resource "google_secret_manager_secret_version" "my_secret_version" {
  secret      = google_secret_manager_secret.my_secret.id
  secret_data = "this is the secret"
}
