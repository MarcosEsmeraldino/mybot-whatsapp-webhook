package com.mybot.whatsapp.webhook.chatpro.dto;

import jakarta.validation.constraints.NotNull;

public record ChatProMensagemDTO(
        @NotNull
        ChatProMensagemTypeEnum type,
        @NotNull
        ChatProBodyDTO body,
        String url
) {}
