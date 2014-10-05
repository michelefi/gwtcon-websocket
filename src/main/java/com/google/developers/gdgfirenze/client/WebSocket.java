package com.google.developers.gdgfirenze.client;

import java.util.ArrayList;

import com.google.developers.gdgfirenze.client.exception.WebSocketNotSupportedException;
import com.google.developers.gdgfirenze.client.service.MessageService;
import com.google.developers.gdgfirenze.shared.Message;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class WebSocket {

	private String url;
	private JavaScriptObject ws;
	private ArrayList<WebSocketHandler> handlers = new ArrayList<>();
	private SerializationStreamFactory factory;

	public WebSocket(String url) {
		super();
		this.url = url;
		factory = (SerializationStreamFactory) GWT.create(MessageService.class);
	}

	public void open() throws WebSocketNotSupportedException {
		try {
			ws = init();
		} catch (JavaScriptException e) {
			throw new WebSocketNotSupportedException();
		}
	}

	public void addWebSocketHandler(WebSocketHandler handler) {
		handlers.add(handler);
	}

	public void removeWebSocketHandler(WebSocketHandler handler) {
		handlers.add(handler);
	}

	public void sendMessae(Message message) {
		try {
			final SerializationStreamWriter writer = factory
					.createStreamWriter();
			writer.writeObject(message);
			// Sending serialized object content
			final String data = writer.toString();
			send(data);
		} catch (final SerializationException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (ws != null) {
			destroy();
			ws = null;
		}
	}

	private void onClose() {
		for (WebSocketHandler handler : handlers) {
			handler.onClose();
		}

	}

	private void onError() {
		for (WebSocketHandler handler : handlers) {
			handler.onError("Websocket error");
		}

	}

	private void onMessage(String msg) {
		try {
			final SerializationStreamReader streamReader = factory
					.createStreamReader(msg);
			final Message message = (Message) streamReader.readObject();
			for (WebSocketHandler handler : handlers) {
				handler.onMessage(message);
			}
		} catch (SerializationException e) {
			for (WebSocketHandler handler : handlers) {
				handler.onError("Serialization error");
			}
		}

	}

	private void onOpen() {
		for (WebSocketHandler handler : handlers) {
			handler.onOpen();
		}

	}

	private native JavaScriptObject init() /*-{
		if (!$wnd.WebSocket) {
			throw "WebSocket not supported";
		}
		var websocket = new WebSocket(
				this.@com.google.developers.gdgfirenze.client.WebSocket::url);
		var wrapper = this;
		websocket.onopen = function(evt) {
			wrapper.@com.google.developers.gdgfirenze.client.WebSocket::onOpen()();
		};
		websocket.onclose = function(evt) {
			wrapper.@com.google.developers.gdgfirenze.client.WebSocket::onClose()();
		};
		websocket.onmessage = function(evt) {
			wrapper.@com.google.developers.gdgfirenze.client.WebSocket::onMessage(Ljava/lang/String;)(evt.data);

		};
		websocket.onerror = function(evt) {
			wrapper.@com.google.developers.gdgfirenze.client.WebSocket::onError()();

		};
		return websocket;
	}-*/;

	private native void send(String message) /*-{
		this.@com.google.developers.gdgfirenze.client.WebSocket::ws
				.send(message);
	}-*/;

	private native boolean destroy() /*-{
		this.@com.google.developers.gdgfirenze.client.WebSocket::ws.close();
	}-*/;

}
