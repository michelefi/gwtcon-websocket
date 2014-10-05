package com.google.developers.gdgfirenze.client;

import com.google.developers.gdgfirenze.client.exception.WebSocketNotSupportedException;
import com.google.developers.gdgfirenze.client.ui.ChatWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtConEntryPoint implements EntryPoint {
	/**
	 * /** This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// ws://127.0.0.1:8025/chat
		String url = GWT.getHostPageBaseURL().replace("http", "ws").replaceFirst(":\\d+",":8025") + "chat";
		WebSocket webSocket = new WebSocket(url);
		ChatWidget view = new ChatWidget();
		ChatPresenter presenter = new ChatPresenter(view,webSocket);
		RootPanel.get("application").add(view);	
		try {
			webSocket.open();
		} catch (WebSocketNotSupportedException e) {
			Window.alert("Sorry your browser dosen't support Web Socket");
		}
	}
}
