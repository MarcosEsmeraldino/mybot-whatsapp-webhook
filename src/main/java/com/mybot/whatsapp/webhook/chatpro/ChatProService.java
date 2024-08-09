package com.mybot.whatsapp.webhook.chatpro;

import com.mybot.whatsapp.webhook.chatpro.dto.ChatProMensagemDTO;
import com.mybot.whatsapp.webhook.producer.MyBotWhatsappProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatProService {

    @Autowired
    private MyBotWhatsappProducer producer;

    @Autowired
    private ChatProMensagemConverter converter;

    public void enviarMensagem(String host, ChatProMensagemDTO mensagem) {
        log.info("Mensagem recebida para {}", host);
        var myBotMensagem = converter.converter(mensagem, host);
        producer.enviarMensagemDoCliente(myBotMensagem);
    }
}
