**Configure JBoss EAP to Use a Remote JBoss AMQ Server**

1. Create the remote connector. 

```shell
/subsystem=messaging-activemq/server=default/remote-connector=netty-remote-throughput:add(socket-binding=messaging-remote-throughput)
```

2. Add a pooled connection factory for the remote connector. 

```shell
/subsystem=messaging-activemq/server=default/pooled-connection-factory=activemq-ra-remote:add(transaction=xa,entries=[java:/RemoteJmsXA, java:jboss/RemoteJmsXA],connectors=[netty-remote-throughput])
```

3. Add the remote server to the remote destination outbound socket binding group. 

```shell
/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=messaging-remote-throughput:add(host=localhost, port=61616)
```