package com.google.developers.gdgfirenze.client;

import com.google.developers.gdgfirenze.shared.Message;

public interface WebSocketHandler {

	void onClose();

	void onError();

	void onMessage(Message msg);

	void onOpen();

}
