package com.mybot.whatsapp.webhook.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybot.whatsapp.webhook.dto.MyBotMensagem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class MyBotWhatsappProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public CompletableFuture<SendResult<String, String>> enviarMensagemDoCliente(MyBotMensagem mensagem) {
        try {
            String key = mensagem.cliente().contato();
            String value = objectMapper.writeValueAsString(mensagem);

            var completableFuture = kafkaTemplate.sendDefault(key, value);
            return completableFuture
                    .whenComplete((sendResult, throwable) -> {
                        if (throwable != null) {
                            handleFailure(key, value, throwable);
                        } else {
                            handleSuccess(key, value, sendResult);

                        }
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleFailure(String key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
    }

    private void handleSuccess(String key, String value, SendResult<String, String> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}",
                key, value, result.getRecordMetadata().partition());
    }
}
