# Before starting the application, run
`docker-compose up -d` 
to start IBM MQ.

The default configuration variables:

```
ibm:
  mq:
    queueManager: QM1
    channel: DEV.ADMIN.SVRCONN
    connName: localhost(1414)
    user: admin
    password: passw0rd

# The queue DEV.QUEUE.1 is pre-created by the IBM MQ for Developers container
queue:
  name: DEV.QUEUE.1

```

# Starting application 
`mvn spring-boot:run`
