package com.mybot.whatsapp.webhook.chatpro;

import com.mybot.whatsapp.webhook.chatpro.dto.ChatProMensagemDTO;
import com.mybot.whatsapp.webhook.chatpro.dto.ChatProMensagemTypeEnum;
import com.mybot.whatsapp.webhook.dto.MyBotCliente;
import com.mybot.whatsapp.webhook.dto.MyBotHost;
import com.mybot.whatsapp.webhook.dto.MyBotMensagem;
import com.mybot.whatsapp.webhook.dto.MyBotTipoEnum;
import org.springframework.stereotype.Component;

import static com.mybot.whatsapp.webhook.dto.MyBotTipoEnum.*;

@Component
public class ChatProMensagemConverter {

    public MyBotMensagem converter(ChatProMensagemDTO dto, String hostContato) {

        String clientContato = limparNumeroContato(dto.body().info().remoteJid());
        var cliente = new MyBotCliente(clientContato);

        var host = new MyBotHost(hostContato);
        var tipo = converter(dto.type());
        var mensagem = dto.body().text();

        return new MyBotMensagem(mensagem, cliente, host, tipo);
    }

    public MyBotTipoEnum converter(ChatProMensagemTypeEnum type) {
        return switch(type) {
            default -> throw new IllegalStateException("Unexpected value: " + type);
            case receveid_message -> TEXTO;
            case receveid_audio_message -> AUDIO;
            case receveid_image_message -> IMAGEM;
        };
    }

    private String limparNumeroContato(String numero) {
        return numero.replaceAll("[a-zA-Zà-úÀ-Ú\\s]+", "");
    }
}
