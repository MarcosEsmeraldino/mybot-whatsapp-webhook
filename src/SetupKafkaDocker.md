# Configuração local do Kafka usando Docker

### Para configurar o Kafka localmente com o Docker, execute o procedimento:

1. Vá até o local onde está **docker-compose.yml** e execute:

```
docker-compose up
```

2. Acesse o broker com o bash:
```
docker exec -it kafka1 bash
```

3. Crie o novo tópico:
```
kafka-topics --bootstrap-server kafka1:19092 \
             --create \
             --topic mybot-whatsapp-topic \
             --replication-factor 1 --partitions 2
```

### Para verificar se as mensagens estão sendo postadas no tópico, execute o procedimento:

1. Crie um consumer
```
docker exec --interactive --tty kafka1  \
kafka-console-consumer --bootstrap-server kafka1:19092 \
                       --topic mybot-whatsapp-topic \
                       --from-beginning \
                       --property "key.separator= - " --property "print.key=true"
```

2. Execute requisições de envio de notificação
```
curl -i \
-d '{"type":"receveid_message","body":{"info":{"remoteJid":"+55(51)99999-8888"},"text":"Hello World!"}}' \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/v1/chatpro/+555199988-7766
```
