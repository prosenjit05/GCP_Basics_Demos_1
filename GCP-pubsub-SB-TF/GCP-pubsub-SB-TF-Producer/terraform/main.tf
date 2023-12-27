# Pro - I had to run terraform apply 3 times each time it created only 1 resource below
# Ideally it should wait for the dependent resources and then try to create it in order

resource "google_pubsub_schema" "my_pubsub_schema_pro" {
  name = "my_pubsub_schema_pro"
  type = "AVRO"

  definition = <<EOF
{
  "type": "record",
  "name": "my_message_pro",
  "fields": [
    {"name": "bookId", "type": "int"},
    {"name": "bookName", "type": "string"},
    {"name": "bookAuthor", "type": "string"},
    {"name": "bookSerialNumber", "type": "int"}
  ]
}
EOF
}

# Create the Pub/Sub topic with the schema attached
resource "google_pubsub_topic" "my_topic_pro" {
  name = "my_topic_pro"

  schema_settings {
    schema   = "projects/test-project-1-406807/schemas/my_pubsub_schema_pro"
    encoding = "JSON"
  }
}

# Create a subscription for the topic
resource "google_pubsub_subscription" "my_subscription_pro" {
  name  = "my_subscription_pro"
  topic = "projects/test-project-1-406807/topics/my_topic_pro"

  # Optional: Adjust ack deadline and retention duration
  ack_deadline_seconds       = 60
  message_retention_duration = "604800s" # 7 days
}
