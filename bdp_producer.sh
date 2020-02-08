#Nos trasladamos a la ruta donde esta instalado kafka
cd /home/kafka/kafka_2.11-2.4.0/bin
#Crear el topic 
./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic practicaBDP
#Listamos los topics para verificar que el topic fue creado
./kafka-topics.sh --list --zookeeper localhost:2181
#Copiamos el archivo a enviar al productor
cp /home/keepcoding/Descargas/personal.json .
ls
cd ..
#Ejecutar el productor en el topic creado
cat bin/personal.json | bin/kafka-console-producer.sh --broker-list localhost:9092 --topic practicaBDP

#Codigo para eliminar el topic (no se ejecuta)
#./kafka-topics.sh --zookeeper localhost:2181 --delete --topic practicaBDP
