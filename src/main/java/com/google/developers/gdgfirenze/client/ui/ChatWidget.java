package com.google.developers.gdgfirenze.client.ui;

import java.util.Date;

import com.google.developers.gdgfirenze.shared.Message;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ChatWidget extends Composite implements ChatView {

	private static ChatWidgetUiBinder uiBinder = GWT
			.create(ChatWidgetUiBinder.class);

	interface ChatWidgetUiBinder extends UiBinder<Widget, ChatWidget> {
	}

	public ChatWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	private DateTimeFormat timeFormat = DateTimeFormat.getFormat(PredefinedFormat.TIME_MEDIUM);

	@UiField
	TextBox inputText;

	@UiField
	Button sendButton;

	@UiField
	FlexTable table;

	private Presenter presenter;

	@UiHandler("sendButton")
	void handleClick(ClickEvent e) {
		sendMessageToThePresenter();
	}

	@UiHandler("inputText")
	void onPasswordTextBoxKeyPress(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			sendMessageToThePresenter();
		}
	}

	private void sendMessageToThePresenter() {
		Message message = new Message();
		message.setData(inputText.getText());
		message.setTime(new Date());
		message.setUsername("username");
		presenter.sendMessage(message);
	}

	@Override
	public void addMessage(Message msg) {
		int numRows = table.getRowCount();
		table.setWidget(numRows, 0, new Label(timeFormat.format(msg.getTime())));
		table.setWidget(numRows, 1, new Label(msg.getUsername()));
		table.setWidget(numRows, 2, new Label(msg.getData()));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
