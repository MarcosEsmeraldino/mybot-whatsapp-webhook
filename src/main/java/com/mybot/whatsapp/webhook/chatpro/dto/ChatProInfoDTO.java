package com.mybot.whatsapp.webhook.chatpro.dto;

import java.time.LocalDateTime;

public record ChatProInfoDTO(
        String id,
        String remoteJid,
        String senderJid,
        Boolean fromMe,
        LocalDateTime timestamp,
        String pushName,
        Integer status,
        String quotedMessageID,
        ChatProSourceDTO source
) {}
