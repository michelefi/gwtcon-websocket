package com.google.developers.gdgfirenze.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.developers.gdgfirenze.server.serialization.CustomSerializationPolicy;
import com.google.developers.gdgfirenze.server.serialization.CustomSerializationPolicyProvider;
import com.google.developers.gdgfirenze.shared.Message;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamReader;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamWriter;

@ServerEndpoint(value = "/chat")
public class ChatWebSocketServer {

  private static Logger logger = Logger.getLogger(ChatWebSocketServer.class.getName());

  
  @OnMessage
  public String onMessage(String message, Session session) {
    String result = message;
    try {
      final ServerSerializationStreamReader streamReader =
          new ServerSerializationStreamReader(Thread.currentThread().getContextClassLoader(),
              new CustomSerializationPolicyProvider());
      // Filling stream reader with data
      streamReader.prepareToRead(message);
      // Reading deserialized object from the stream
      final Message messageDto = (Message) streamReader.readObject();

      final ServerSerializationStreamWriter serverSerializationStreamWriter =
          new ServerSerializationStreamWriter(new CustomSerializationPolicy());

      serverSerializationStreamWriter.writeObject(messageDto);
      result = serverSerializationStreamWriter.toString();
//      logger.log(Level.INFO, messageDto.getId() + messageDto.getType());
    } catch (final SerializationException e) {
      logger.log(Level.WARNING, "SerializationException", e);
    }
    return result;
  }

  @OnOpen
  public void onOpen(Session session, EndpointConfig conf) {
    logger.log(Level.INFO, "Commection open for:" + session.getId());
  }

}
