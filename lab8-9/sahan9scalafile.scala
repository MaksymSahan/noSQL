import org.apache.spark.eventhubs.{ ConnectionStringBuilder, EventHubsConf, EventPosition }
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

// To connect to an Event Hub, EntityPath is required as part of the connection string.
// Here, we assume that the connection string from the Azure portal does not have the EntityPath part.
val appID = "7eabd50f-3e5e-4fa2-acfd-9ace8d6c77f2"
val password = "l55B_~lw5OK80t.kSw.~7I~CHku4WIsh68"
val tenantID = "d0c2778c-2bb0-4cf8-9233-9ca1afbeed47"
val fileSystemName = "sahan9"
val storageAccountName = "sahan9"
val connectionString = ConnectionStringBuilder("Endpoint=sb://sahan9.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=D9jyzRF+UoUqVASK1QcyrS9jrZRbZOtikSeP+a13v8o=")
  .setEventHubName("sahan9")
  .build
val eventHubsConf = EventHubsConf(connectionString)
  .setStartingPosition(EventPosition.fromEndOfStream)

var streamingInputDF = 
  spark.readStream
    .format("eventhubs")
    .options(eventHubsConf.toMap)
    .load()

val filtered = streamingInputDF.select (
  from_unixtime(col("enqueuedTime").cast(LongType)).alias("enqueuedTime")
     , get_json_object(col("body").cast(StringType), "$.poc").alias("poc")
     , get_json_object(col("body").cast(StringType), "$.datum").alias("datum")
     , get_json_object(col("body").cast(StringType), "$.parameter_name").alias("parameter_name")
     , get_json_object(col("body").cast(StringType), "$.sample_duration").alias("sample_duration")
)


filtered.writeStream
  .format("com.databricks.spark.csv")
  .outputMode("append")
  .option("checkpointLocation", "/mnt/sahan9/lab9dir")
  .start("/mnt/sahan9/lab9dir")