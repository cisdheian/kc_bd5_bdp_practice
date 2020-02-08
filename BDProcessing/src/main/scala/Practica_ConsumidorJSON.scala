import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}
import org.apache.spark.sql.functions.{from_json,col}

object Practica_ConsumidorJSON {
  def main(args: Array[String]): Unit = {
    //declarar la app
    val spark=SparkSession.builder().appName("JsonKafkaConsumer").master("local").getOrCreate()

    //Configurar stream de entrada
    val df=spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers","localhost.localdomain:9092")
      .option("subscribe","practicaBDP")
      .option("startingOffsets","earliest")
      .load()

    // castear los datos leidos en formato kafka para convertirlos en strings
    val res=df.selectExpr("CAST(value AS STRING) as value")

    //Declarar la estructura de los datos
    val schema= new StructType()
      .add("id",IntegerType)
      .add("first_name",StringType)
      .add("last_name",StringType)
      .add("email",StringType)
      .add("gender",StringType)
      .add("ip_address",StringType)

    //Seleccionar y filtrar dos los rows
    val persona=res.select(from_json(col("value") ,schema).as ("data"))
      .select ("data.*")
      .filter("first_name not like \"Giavani\"")
      .filter("first_name not like \"Noell\"")

    //Mostrar los datos ledios del streaming
    println("mostrar los datos por consola")

    persona.writeStream
      .format("console")
      .outputMode("append")
      .start()
      .awaitTermination(60000)
  }
}
