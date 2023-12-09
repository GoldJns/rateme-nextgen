terraform {
  backend "gcs" {
    bucket = terraform-state-rateme-nextgen
  }
}

provider "google" {
  project = var.project
  region = var.region
}


