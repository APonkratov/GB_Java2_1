package com.geekbrains.java2.lesson4.homework4.fxchat;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatSceneController implements Initializable {

    @FXML
    TextArea messagesList;

    @FXML
    TextField messageField;

    @FXML
    Button sendButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize elements
        messagesList.setEditable(false);
        sendButton.setDefaultButton(true);

        // Initialize actions
        messageField.addEventHandler(KeyEvent.KEY_PRESSED, this::keyPressedAction);
        sendButton.addEventHandler(ActionEvent.ACTION, this::mouseClickButton);
    }

    private <T extends Event> void mouseClickButton(T event) {
        event.consume();
        sendMessage();
    }

    private void keyPressedAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !event.isControlDown()) {
            event.consume();
            sendMessage();
        }
    }

    private void sendMessage() {
        if (!messageField.getText().isEmpty()) {
            String messageText = messageField.getText();
            messagesList.appendText(messageText + "\n");
            messageField.clear();
        }
    }
}