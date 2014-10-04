package com.google.developers.gdgfirenze.client;

import java.util.ArrayList;

import com.google.developers.gdgfirenze.client.exception.WebSocketNotSupportedException;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;

public class WebSocket {

	private String url;
	private JavaScriptObject ws;
	private ArrayList<WebSocketHandler> handlers = new ArrayList<>();

	public WebSocket(String url) {
		super();
		this.url = url;
	}

	public void open() throws WebSocketNotSupportedException {
		if (ws != null) {
			try {
				ws = init();
			} catch (JavaScriptException e) {
				throw new WebSocketNotSupportedException();
			}
		}

	}

	void addWebSocketHandler(WebSocketHandler handler) {
		handlers.add(handler);
	}

	void removeWebSocketHandler(WebSocketHandler handler) {
		handlers.add(handler);
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
			handler.onError();
		}

	}

	private void onMessage(String msg) {

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

	private native boolean destroy() /*-{
		this.@com.google.developers.gdgfirenze.client.WebSocket::ws.close();
	}-*/;

}
