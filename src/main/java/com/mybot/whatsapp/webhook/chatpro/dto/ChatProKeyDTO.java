package com.mybot.whatsapp.webhook.chatpro.dto;

public record ChatProKeyDTO(
        String remoteJid,
        Boolean fromMe,
        String id,
        String participant
) {}
