package com.sky.webSocket;

import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket服务
 */
@Component
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {

    //存放会话对象
    private static Map<String, Session> sessionMap = new HashMap();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        System.out.println("客户端：" + sid + "建立连接");
        sessionMap.put(sid, session);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        System.out.println("收到来自客户端：" + sid + "的信息:" + message);
    }

    /**
     * 连接关闭调用的方法
     *
     * @param sid
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        System.out.println("连接断开:" + sid);
        sessionMap.remove(sid);
    }

    /**
     * 群发
     *
     * @param message
     */
    public void sendToAllClient(String message) {
        System.out.println("WebSocket消息群发: " + message);
        Collection<Session> sessions = sessionMap.values();
        System.out.println("当前连接的客户端数量: " + sessions.size());
         
        // 使用JacksonObjectMapper处理消息转换
        String jsonMessage;
        if (message.startsWith("{") && message.endsWith("}")) {
            // 如果已经是JSON格式，直接使用
            jsonMessage = message;
        } else {
            // 如果不是JSON格式，使用Map包装并转换为JSON
            try {
                com.sky.json.JacksonObjectMapper objectMapper = new com.sky.json.JacksonObjectMapper();
                java.util.Map<String, String> messageMap = new java.util.HashMap<>();
                messageMap.put("type", "message");
                messageMap.put("content", message);
                jsonMessage = objectMapper.writeValueAsString(messageMap);
            } catch (Exception e) {
                System.err.println("消息转换为JSON失败: " + e.getMessage());
                // 如果转换失败，使用简单包装
                jsonMessage = "{\"type\":\"message\",\"content\":\"" + message.replace("\"", "\\\"") + "\"}";
            }
        }
        
        for (Session session : sessions) {
            try {
                //服务器向客户端发送消息
                System.out.println("正在向客户端发送消息: " + jsonMessage);
                session.getBasicRemote().sendText(jsonMessage);
                System.out.println("消息发送成功");
            } catch (Exception e) {
                System.err.println("消息发送失败: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
