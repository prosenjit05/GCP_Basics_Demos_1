#Pro Note:
#For now unable to do dynamic upload from code.
#But for now able to upload a already succesful function: HelloHttpFunction.zip
#see the structure in the zip pro

resource "google_storage_bucket" "my_bucket_pro" {
  name                        = "test-project-1-406807-my-bucket-pro"
  location                    = "us-central1"
  uniform_bucket_level_access = true
  force_destroy               = true
}

resource "google_storage_bucket_object" "my_source_code_pro" {
  name   = "my_source_code_pro"
  bucket = google_storage_bucket.my_bucket_pro.name
  source = "C:/Pro/MyITWorldPro/Workspaces/workspace1/GCP_Basics_Demos_1/GCP-CloudFunction-SB-TF/target/HelloHttpFunction.zip"
}

resource "google_cloudfunctions2_function" "my_function_pro" {
  name        = "my_function_pro"
  description = "My function pro demo"
  location    = "us-central1"

  build_config {
    runtime     = "java17"
    entry_point = "gcfv2.HelloHttpFunction"
    source {
      storage_source {
        bucket = google_storage_bucket.my_bucket_pro.name
        object = google_storage_bucket_object.my_source_code_pro.name
      }
    }
  }

  service_config {
    max_instance_count = 1
    available_memory   = "128Mi"
    timeout_seconds    = 60
  }
}
