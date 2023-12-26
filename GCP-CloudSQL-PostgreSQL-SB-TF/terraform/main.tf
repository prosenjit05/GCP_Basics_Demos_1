# Note: This would take approx 9 minutes to complete
# Notes:
# There is no direct way to query the data in the tables from the Google UI

resource "google_sql_database" "my-database-pro" {
  name     = "my-database-pro"
  instance = google_sql_database_instance.my-database-pro-instance.name
}

# See versions at https://registry.terraform.io/providers/hashicorp/google/latest/docs/resources/sql_database_instance#database_version
resource "google_sql_database_instance" "my-database-pro-instance" {
  name             = "my-database-pro-instance"
  region           = "us-central1"
  database_version = "POSTGRES_15"
  settings {
    tier = "db-f1-micro"
  }

  deletion_protection = "false"
}
