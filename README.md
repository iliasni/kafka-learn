# kafka-learn

Apache kafka project created for educational purposes. Built in `kafka 3.5.1` and `JAVA 17`.
Consists of one kafka producer, one kafka consumer and a kafka cluster with one broker using raft.
Producer sends messages to the kafka broker and consumer receives them.

## Producer

Instantiates and configures kafka producer. Parses JSON dataset line by line using `Jackson` and maps the objects
to a POJO created. The dataset describes comets and asteroids that are in near-earth orbit. Parsed objects are sent
to the broker as strings as soon as they're read. A callback function that runs in the IO thread is responsible for
catching any possible exceptions for the record  being sent each time, since normally each record is sent
asynchronously.

Keys of the kafka messages are the date each stellar object was observed in the `YYYYMMDD` format. Values are the string
representations of the POJO objects that is defined in the `toString()` method.


## Consumer

Instantiates and configures kafka consumer. Subscribes to the target topic and logs messages it receives. It checks
for messages every 100ms until manually stopped.

## Kafka Cluster

The kafka cluster consists of a single kafka broker container node that is running with raft instead of Zookeeper.
For more information about the configuration see the `compose.yaml` file.