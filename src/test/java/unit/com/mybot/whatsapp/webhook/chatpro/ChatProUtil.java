package com.mybot.whatsapp.webhook.chatpro;

import com.mybot.whatsapp.webhook.chatpro.dto.*;

import java.time.LocalDateTime;

public class ChatProUtil {

    public static ChatProMensagemDTO getChatProMensagemTexto() {
        var info = new ChatProInfoDTO(
                null,
                "+5551999887766",
                null,
                null,
                LocalDateTime.of(2024, 01, 01, 0, 0),
                null,
                null,
                null,
                null
        );
        var body = new ChatProBodyDTO(info, null, null, null, null, null, "texto");
        return new ChatProMensagemDTO(ChatProMensagemTypeEnum.receveid_message, body, null);
    }

    public static ChatProMensagemDTO getChatProMensagemTextoInvalida() {
        return new ChatProMensagemDTO(null, null, null);
    }
}
