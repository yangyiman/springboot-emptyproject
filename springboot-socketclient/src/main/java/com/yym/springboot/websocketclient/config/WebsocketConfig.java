package com.yym.springboot.websocketclient.config;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Slf4j
public class WebsocketConfig {

    @Bean
    public WebSocketClient webSocketClient() throws URISyntaxException {
        WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://localhost:8080//websocket/1")) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                log.info("连接成功");
            }

            @Override
            public void onMessage(String s) {
                log.info("收到消息{}",s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                log.info("连接关闭");
            }

            @Override
            public void onError(Exception e) {
                log.info("连接出错{}",e.getMessage());
            }
        };
        webSocketClient.connect();
        return webSocketClient;
    }
}
