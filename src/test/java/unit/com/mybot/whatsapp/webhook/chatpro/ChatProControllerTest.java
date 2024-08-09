package com.mybot.whatsapp.webhook.chatpro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatProController.class)
@AutoConfigureMockMvc
class ChatProControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ChatProService service;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void retorna204AoEnviarMensagem() throws Exception {

        doNothing()
                .when(service)
                .enviarMensagem(any(), any());

        var dto = ChatProUtil.getChatProMensagemTexto();
        var json = objectMapper.writeValueAsString(dto);


        mockMvc.perform(post("/v1/chatpro/+5551999887766")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void retorna400AoEnviarMensagemInvalida() throws Exception {

        var dto = ChatProUtil.getChatProMensagemTextoInvalida();
        var json = objectMapper.writeValueAsString(dto);


        mockMvc.perform(post("/v1/chatpro/+5551999887766")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void retorna404AoEnviarMensagemSemHost() throws Exception {

        var dto = ChatProUtil.getChatProMensagemTexto();
        var json = objectMapper.writeValueAsString(dto);


        mockMvc.perform(post("/v1/chatpro/")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}