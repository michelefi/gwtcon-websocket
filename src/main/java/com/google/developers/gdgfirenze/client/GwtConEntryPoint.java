package com.google.developers.gdgfirenze.client;

import com.google.developers.gdgfirenze.client.exception.WebSocketNotSupportedException;
import com.google.developers.gdgfirenze.client.ui.ChatWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtConEntryPoint implements EntryPoint {
	/**
	 * /** This is the entry point method.
	 */
	public void onModuleLoad() {
		
//		Window.alert(GWT.getHostPageBaseURL());
		
		WebSocket webSocket = new WebSocket("ws://localhost:8025/chat");
		ChatWidget view = new ChatWidget();
		ChatPresenter presenter = new ChatPresenter(view,webSocket);
		RootLayoutPanel.get().add(view);
		try {
			webSocket.open();
		} catch (WebSocketNotSupportedException e) {
			Window.alert("Sorry your browser dosen't support Web Socket");
		}
	}
}
