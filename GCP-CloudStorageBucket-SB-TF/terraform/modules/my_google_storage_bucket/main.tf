resource "google_storage_bucket" "bucket" {
  name                        = var.my_bucket_name
  location                    = var.my_bucket_location
  uniform_bucket_level_access = var.my_bucket_uniform_bucket_level_access
}

