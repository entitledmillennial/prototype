/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animations.FadeInLeftTransition1;
import animations.FadeInRightTransition;
import configurations.Template;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 *
 * @author zhee
 */
public class LoginController implements Initializable {
    
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Text message;
    @FXML
    private Label lblClose;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            new FadeInRightTransition(message).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtUsername).play();
            new FadeInLeftTransition1(txtPassword).play();
            new FadeInRightTransition(btnLogin).play();
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });
        });
    }   
       
    @FXML
    private void loginEvent(ActionEvent ae)
    {
        //txtUsername.setText("username");txtPassword.setText("password");
        if(txtUsername.getText().equals("admin")&&txtPassword.getText().equals("admin"))
        {
            Template t = new Template();
            t.newStage(lblClose, "/views/menu.fxml", "Menu", true, StageStyle.UNIFIED, true);
        }
        else
        {
            Template.dialog(AlertType.ERROR, "Login Error. Please check your username and password");
        }
    }
     
    
}
