Java project created in Eclipse Mars 2 Release (4.5.2)

Java jdk1.8.0_101 (64 bits)
zookeeper-3.4.10
kafka_2.12-1.0.0
Spring 4.0
Spring Kafka
Spring Netflix Ribbon
Spring Netflix Hyxtrix



Previous steps:

How to create kafka server in windows https://dzone.com/articles/running-apache-kafka-on-windows-os

1)Install kafka_2.12-1.0.0 in c:\workspaces\workEclipse\worksymbio\server\kafka_2.12-1.0.0
2)Install zookeeper-3.4.10 in c:\workspaces\workEclipse\worksymbio\server\zookeeper-3.4.10
3)create folder "logs" in c:\workspaces\workEclipse\worksymbio
4)install folder bats with all the scripts in c:\workspaces\workEclipse\worksymbio

ProcessAllServer. Launch Zookeeper; Launch Kafka; create 5 topics (all 1 node of replication and 1 node of partition)
serverb, client1, client2, server1, server2

Projects:

SymbioRestServer   --> Node Rest-Kafka client: (with parameters --server.topic=server1 --client.topic=client2) (Listen to: localhost:8001)
SymbioRestServer2  --> Node Rest-Kafka client: (with parameters --server.topic=server1 --client.topic=client1) (Listen to: localhost:8002)
SymbioKafkaServer  --> Node Kafka server: (with parameters --server.topic=client2 --client.topic=server1)
SymbioGatewayProxy --> Client side Load Balancer(Ribbon). Fault Controller(Hystrix) (Listen to: localhost:8080)

