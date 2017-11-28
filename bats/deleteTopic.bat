@echo off
call C:\workspaces\workEclipse\worksymbio\server\kafka_2.12-1.0.0\bin\windows\kafka-run-class.bat kafka.admin.TopicCommand --delete --topic %1 --zookeeper localhost:2181
