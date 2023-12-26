# ensure the user has this role in IAM: Cloud Datastore Owner

resource "google_firestore_database" "my-firestore-db-pro" {
  project = "test-project-1-406807"
  #Each project may have a single database named “(default)” which qualifies it for free-tier quota.
  name                        = "(default)"
  location_id                 = "nam5"
  type                        = "FIRESTORE_NATIVE"
  delete_protection_state     = "DELETE_PROTECTION_DISABLED"
  deletion_policy             = "DELETE"
  app_engine_integration_mode = "DISABLED"
}
