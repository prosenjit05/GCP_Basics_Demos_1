module "google_storage_bucket" {
  source                                = "./modules/my_google_storage_bucket"
  my_bucket_name                        = var.my_root_var_gcp_bucket_name
  my_bucket_location                    = var.my_root_var_gcp_bucket_location
  my_bucket_uniform_bucket_level_access = var.my_root_var_gcp_bucket_uniform_bucket_level_access
}

