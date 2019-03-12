# REST-API using Spring Boot, Spring MVC and Kafka
The focus is on learning Kafka.

## Uses
- **[Spring Boot](https://expressjs.com/)**, framework designed to make it easier to develop a new Spring applications
- **[Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)**, a java web application framework
- **[Spring Kafka](https://spring.io/projects/spring-kafka)**, simplifies the use of Kafka (like Spring JDBC does for JDBC API).
- **[Kafka](https://kafka.apache.org/)**, a distributed streaming platform (used for building real-time data pipelines and streaming apps).
- **[Spring JDBC](https://spring.io/projects/spring-data-jdbc)**, (a part of Spring Data) used to simplify implementing data access layer.
- **[SQLite](https://www.sqlite.org/index.html)**, a SQL database
- **[Flyway](https://flywaydb.org/)**, version control for database migrations.


## API
|Service                             |Type  |Description                   |
|------------------------------------|------|-------------------------------
|api/v1/receipt                      |GET   |Lists all receipts            |
|api/v1/receipt                      |POST  |Create new receipt            |

### Example Data:

```json
{
  "receiptNumber": "1",
  "clientName": "Spooky Ghost",
  "clientAddress": "House on the hill",
  "paidAmount": 5000,
  "paymentDate": "2019-01-12"
}
```

## Prerequisite
Kafka (and Zookeeper) installed and running locally on default ports (Zookeeper at 2181, and Kafka Broker at 9092).

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
`bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic new_reciepts`
This will create a new topic with the name **books**.
Related data files will be stored under `C:\tmp\kafka-logs\new_reciepts-0`