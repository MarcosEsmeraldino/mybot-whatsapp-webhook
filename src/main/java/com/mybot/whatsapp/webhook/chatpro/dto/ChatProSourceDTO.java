package com.mybot.whatsapp.webhook.chatpro.dto;

import java.time.LocalDateTime;

public record ChatProSourceDTO(
        ChatProKeyDTO key,
        ChatProMessageDTO message,
        LocalDateTime messageTimestamp,
        String participant,
        Integer status
) {}
