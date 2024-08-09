package com.mybot.whatsapp.webhook.chatpro;

import com.mybot.whatsapp.webhook.chatpro.dto.ChatProMensagemDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/chatpro")
public class ChatProController {

    @Autowired
    private ChatProService service;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{host}")
    public void enviarMensagem(@PathVariable String host, @Valid @RequestBody ChatProMensagemDTO mensagem) {
        service.enviarMensagem(host, mensagem);
    }
}
