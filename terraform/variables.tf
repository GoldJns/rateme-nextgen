  locals {
      labels = {
          "data-project" = var.data-project
      }
  }

  variable "project" {
      type= string
      description = "ID Google project"
  }

  variable "region" {
      type= string
      description = "Region Google project"
  }