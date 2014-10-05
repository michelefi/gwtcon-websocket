package com.google.developers.gdgfirenze.client.ui;

import com.google.developers.gdgfirenze.shared.Message;

public interface ChatView {
	
	interface Presenter {
		void sendMessage(Message message);
	}
	
	void addMessage(Message message);
	
	void setPresenter(Presenter presenter);

}
