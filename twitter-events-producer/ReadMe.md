Start Zookeeper : bin/zookeeper-server-start.sh config/zookeeper.properties

Start Broker : kafka-server-start.sh config/server.properties
               kafka-server-start.sh config/server-1.properties
               kafka-server-start.sh config/server-2.properties

Start Consumer Group : kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic library-events --group my-first-app --from-beginning 
