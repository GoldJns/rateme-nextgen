
variable "project" {
  type        = string
  description = "ID Google project"
  default     = "rateme-nextgen"
}

variable "region" {
  type        = string
  description = "Region Google project"
  default     = "US_CENTRAL1"
}

variable "cluster-name" {
  type        = string
  description = "Cluster name"
  default     = "rateme-nextgen-cluster"
}
variable "service-account-id" {
  type        = string
  description = "Service account id"
}

variable "terraform-state-bucket-name" {
  type        = string
  description = "Name of bucket that stores terraform state"
  default     = "terraform-state-rateme-nextgen"
}
variable "terraform-state-bucket-prefix" {
  type        = string
  description = "Prefix for terraform state bucket"
  default     = "prod"
}
