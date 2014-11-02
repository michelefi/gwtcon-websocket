package com.google.developers.gdgfirenze.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.developers.gdgfirenze.server.serialization.SimpleSerializationPolicy;
import com.google.developers.gdgfirenze.server.serialization.CustomSerializationPolicyProvider;
import com.google.developers.gdgfirenze.shared.Message;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamReader;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamWriter;

@ServerEndpoint(value = "/chat")
public class ChatWebSocketServer {

  private static Logger logger = Logger.getLogger(ChatWebSocketServer.class.getName());

  
  @OnMessage
  public void onMessage(String message, Session session) {
    try {
      final ServerSerializationStreamReader streamReader =
          new ServerSerializationStreamReader(Thread.currentThread().getContextClassLoader(),
              new CustomSerializationPolicyProvider());
      // Filling stream reader with data
      streamReader.prepareToRead(message);
      // Reading deserialized object from the stream
      final Message messageDto = (Message) streamReader.readObject();

      final ServerSerializationStreamWriter serverSerializationStreamWriter =
          new ServerSerializationStreamWriter(new SimpleSerializationPolicy());

      serverSerializationStreamWriter.writeObject(messageDto);
      String result = serverSerializationStreamWriter.toString();
      for (Session s : session.getOpenSessions()) {
          if (s.isOpen()) {
            s.getBasicRemote().sendText(result);
          }
      }
    } catch (final SerializationException | IOException e) {
      logger.log(Level.WARNING, "Error on web socket server", e);
    }
  }

  @OnOpen
  public void onOpen(Session session, EndpointConfig conf) {
    logger.log(Level.INFO, "Commection open for:" + session.getId());
  }

}
