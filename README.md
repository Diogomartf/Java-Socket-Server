# Java-Socket-Server

This is a Java Servervthat waits for an Ip adress via sockets and then make a get request via sockets to heartbeat.dsi.uminho.pt/heartbeat.svc/show?ip=' with the ip received.
The java client is basically just sending an Ip and waiting for the response (all the ips present in the heartbeat).


It also has a JavaScript Client in my repositories called JSClient that works with this server.


