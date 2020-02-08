#Nos trasladamos a la ruta donde esta instalado kafka
cd /home/kafka/kafka_2.11-2.4.0
#Ejecutar el consumidor para leer el topic creado
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic practicaBDP --from-beginning
