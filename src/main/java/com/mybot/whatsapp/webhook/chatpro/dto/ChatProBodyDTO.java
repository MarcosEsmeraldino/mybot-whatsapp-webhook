package com.mybot.whatsapp.webhook.chatpro.dto;

import jakarta.validation.constraints.NotNull;

public record ChatProBodyDTO(
        @NotNull
        ChatProInfoDTO info,
        Float length,
        String type,
        String content,
        String caption,
        String thumbnail,
        String text
) {}
