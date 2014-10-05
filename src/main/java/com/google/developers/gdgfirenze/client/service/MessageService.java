package com.google.developers.gdgfirenze.client.service;

import com.google.developers.gdgfirenze.shared.Message;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The Interface CommandService.
 */
@RemoteServiceRelativePath("MessageService")
public interface MessageService extends RemoteService {
  
  Message getMessage(Message message);

}
