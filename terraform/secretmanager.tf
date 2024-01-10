
resource "google_secret_manager_secret" "gke_mysql_password" {
  project = var.project
  replication {
    user_managed {
      replicas {
        location = "us-central1"
      }
    }

  }
  secret_id = "gke_mysql_password"
  labels    = { label = "rateme-nextgen-secret" }
}

resource "google_secret_manager_secret" "gke_mysql_root_password" {
  project = var.project
  replication {
    user_managed {
      replicas {
        location = "us-central1"
      }
    }

  }
  secret_id = "gke_mysql_root_password"
  labels    = { label = "rateme-nextgen-secret" }
}


resource "google_secret_manager_secret_version" "db_secret_version" {
  secret      = google_secret_manager_secret.gke_mysql_password.id
  secret_data = var.mysql_password
}

resource "google_secret_manager_secret_version" "db_secret_root_version" {
  secret      = google_secret_manager_secret.gke_mysql_root_password.id
  secret_data = var.mysql_password
}
