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


