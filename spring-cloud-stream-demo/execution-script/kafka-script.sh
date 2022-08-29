
# List of topics
kafka-topics --list --bootstrap-server localhost:9092

kafka-console-producer --bootstrap-server localhost:9092 --topic toUppercase-in-0
>hello spring
>hello
>java 9

kafka-console-consumer --bootstrap-server localhost:9092 --topic toUppercase-out-0
HELLO SPRING
HELLO
JAVA 9