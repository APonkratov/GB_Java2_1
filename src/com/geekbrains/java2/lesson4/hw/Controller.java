//package com.geekbrains.java2.lesson4.hw;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.input.KeyEvent;
//
//public class Controller {
//    @FXML
//    TextArea messageArea;
//
//    @FXML
//    TextField newMessage;
//
//    @FXML
//    Button sendButton;
//
//    public void sendMessageTypeAction(ActionEvent actionEvent) {
//        String messageText = newMessage.getText().trim();
//        if(!messageText.isEmpty()) {
//            messageArea.appendText(messageText+System.lineSeparator());
//            newMessage.clear();
//        }
//    }
//}
