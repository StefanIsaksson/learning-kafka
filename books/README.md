# Simple Kafka example

## Description

## Usage
Run `BookProducerApp.java` to create a Kafka message on the topic named books.
Run `BookConsumerApp.java` to read all uncommited/(not yet consumed) Kafka messages on the topic named books.
Both Apps require Zookeeper and Kafka Broker server to be running on localhost on their default ports (see Install section).

## Install

[Download Kafka](https://kafka.apache.org/downloads) and extract content of the compressed files to `C:\Tools\.`.
From now on this directory is referred to in instructions as Kafka home directory. 

### 1. Start Zookeeper (required)
Open console in Kafka home directory and run: 
`bin\windows\zookeeper-server-start.bat config\zookeeper.properties`
This starts a Zookeeper server running on TCP port 2181. 
Data files will be written to `C:\tmp\zookeeper`.

### 2. Start Kafka Broker (required)
Open another console in Kafka home directory and run: 
`bin\windows\kafka-server-start.bat config\server.properties`
This starts a Kafka broker server running on TCP port 9092. 
Data files will be written to `C:\tmp\kafka-logs`.

### 3. Create a Kafka topic (required)
Open a third console in Kafka home directory and run:
`bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic books`
This will create a new topic with the name **books**.
Related data files will be stored under `C:\tmp\kafka-logs\books-0`

Too see all available the topics run the command:
`bin\windows\kafka-topics.bat --list --zookeeper localhost:2181`

### 4. Test write and read to topic (optional)

#### 4.1 Start Kafka Producer Console
Open another console in Kafka home directory and run:
`bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic books`
Each row entered while create a new Message to the topic **books**.

#### 4.2 Start Kafka Consumer Console
Open another console in Kafka home directory and run:
`bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic books --from-beginning`