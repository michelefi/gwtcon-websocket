package com.google.developers.gdgfirenze.client.service;

import com.google.developers.gdgfirenze.shared.Message;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MessageServiceAsync {

  void getMessage(Message message, AsyncCallback<Message> callback);

}
