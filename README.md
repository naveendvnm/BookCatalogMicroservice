# Book Catalog - SpringBoot Microservices

Softwares:
1.	Java 11
2.	JDK1.8
3.	Spring Boot
4.	STS
5.	Kafka
6.	Git & GitHub
7.	MySql
8.	Postman

Spring Boot application:

•	Built REST APIs for Book Catalog where it perfoms CRUD operations

Rest APIs endpoints:
POST: 		http://localhost:9191/bookcatalog/addBook
POST: 		http://localhost:9191/bookcatalog/addBooks
GET: 		http://localhost:9191/bookcatalog/bookById/1
GET: 		http://localhost:9191/bookcatalog/bookByIsbn/978-93-8067-432-2
GET:		http://localhost:9191/bookcatalog/bookByAuthName/Lalit%20Kumar
PUT: 		http://localhost:9191/bookcatalog/update
DELETE: 	http://localhost:9191/bookcatalog/deleteById/1


Microservices:

•	Considering bookcatalog is a just single microservice and registered the same in service-registry.
•	Built Api Cloud-Gateway to route user requests to the microservices(here it is bookcatalog).

 

Kafka:

•	Used Apache Kafka to publish messages in Kafka-Topic whenever we create a new book into the catalog.

To run ZooKepper:
C:\kafka>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

To start the Apache Kafka-
C:\kafka>.\bin\windows\kafka-server-start.bat .\config\server.properties

To create a topic with name javatopic, that has only one partition & one replica.
C:\kafka>.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic javatopic

Finally Open a new command prompt and start the consumer which listens to the topic javatopic we just created above. We will get the message we had sent using the producer
C:\kafka>.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic javatopic --from-beginning
