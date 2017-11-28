@echo off

cd c:\workspaces\workEclipse\worksymbio\logs

start "Zookeeper" /HIGH /MIN /SEPARATE C:\workspaces\workEclipse\worksymbio\bats\processZookeeper.bat
start "Kafka" /NORMAL /MIN /SEPARATE C:\workspaces\workEclipse\worksymbio\bats\processKafka.bat
start "DeleteTopics" /NORMAL /MIN /SEPARATE call C:\workspaces\workEclipse\worksymbio\bats\deleteTopics.bat
call C:\workspaces\workEclipse\worksymbio\bats\createTopics.bat