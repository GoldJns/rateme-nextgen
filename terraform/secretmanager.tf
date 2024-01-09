
resource "google_secret_manager_secret" "db_secret" {
  project = var.project
  replication {
    user_managed {
      replicas {
        location = "US-CENTRAL1"
      }
    }

  }
  secret_id = "db_secret"
  labels    = { label = "rateme-nextgen-secret" }
}

resource "google_secret_manager_secret_version" "db_secret_version" {
  secret      = google_secret_manager_secret.db_secret.id
  secret_data = var.mysql_password
}

