terraform {
  backend "gcs" {
    bucket = var.bucketstate
  }
}

provider "google" {
  project = var.project
  region = var.region
}


