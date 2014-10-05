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

	interface ChatWidgetUiBinder extends UiBinder<Widget, ChatWidget> {
	}

	private static ChatWidgetUiBinder uiBinder = GWT
			.create(ChatWidgetUiBinder.class);

	private DateTimeFormat timeFormat = DateTimeFormat.getFormat(PredefinedFormat.TIME_MEDIUM);
	
	@UiField
	TextBox inputText;

	@UiField
	Button sendButton;

	@UiField
	FlexTable table;

	@UiField
	Label infolabel;
	
	private Presenter presenter;

	public ChatWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void addMessage(Message msg) {
		table.insertRow(0);
		Label info = new Label(timeFormat.format(msg.getTime()) + " " + msg.getUsername() + " - ");
		info.setStyleName("info");
		Label data = new Label(msg.getData());
		data.setStyleName("data");
		table.setWidget(0, 0, info);
		table.setWidget(0, 1, data);
	}

	@Override
	public void setInfoMessage(String message, boolean error) {
		if(error) {
			infolabel.setStyleName("error");
		} else {
			infolabel.setStyleName("info");
		}
		infolabel.setText(message);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

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
		inputText.setText("");
	}
}
