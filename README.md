# kafka-learn

Apache kafka project created for educational purposes. Built in `kafka 3.5.1` and `JAVA 17`.
Consists of one kafka producer, one kafka consumer and a kafka cluster with one broker in raft mode.

## Producer

The `Producer.java` class contains the producer application. It instantiates and configures a kafka producer.
After that, it parses a JSON dataset line by line using the `Jackson` library, and it maps the objects to a POJO.
The dataset describes comets and asteroids that are in near-earth orbit. Parsed objects are sent
to the broker as strings as soon as they're read. A callback function that runs in the producer's IO thread is
responsible for catching any possible exceptions during message delivery (each record is sent  asynchronously).

Keys of the kafka messages are the date each stellar object was observed in the `YYYYMMDD` format. Values are the string
representations of the POJO objects that are defined in the `toString()` method of the `StellarObject` class.


## Consumer

IThe `Consumer.java` class contains the consumer application. It instantiates and configures a kafka consumer.
After that, it subscribes to the target kafka topic and logs messages it receives. It checks  for messages every 100ms
until manually stopped.

## Kafka Cluster

The kafka cluster consists of a single kafka broker container that is running with raft instead of Zookeeper.
For more information about the configuration of the cluster see the `compose.yaml` file.

## Monitoring

Basic kafka cluster monitoring capabilities are set up via `provectus` which is configured and created as a
service in the `compose` file. User can view information about clusters, topics, partitions, messages etc. by visiting
`localhost:8080`. 