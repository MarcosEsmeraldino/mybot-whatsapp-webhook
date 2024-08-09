package com.mybot.whatsapp.webhook.dto;

public record MyBotMensagem(
     String mensagem,
     MyBotCliente cliente,
     MyBotHost host,
     MyBotTipoEnum tipo
) { }