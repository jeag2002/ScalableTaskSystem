@echo off
call C:\workspaces\workEclipse\worksymbio\server\kafka_2.12-1.0.0\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic %1
