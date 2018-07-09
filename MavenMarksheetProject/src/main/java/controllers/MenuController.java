/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import configurations.Template;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author zhee
 */
public class MenuController implements Initializable {

    @FXML
    private Label getWindow;
    @FXML
    private ListView<String> menuList;
    @FXML
    private Button btnLogout;
    @FXML
    private AnchorPane paneCourse;
    
    Template t = new Template();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuList.getItems().addAll("BSc. IT");
        Platform.runLater(() -> {
            menuList.getSelectionModel().select(0);  
            t.loadContent(paneCourse, "bscit.fxml");
            menuList.requestFocus();
        });
    }
    
    @FXML
    private void onListMenu(MouseEvent event) {
        switch(menuList.getSelectionModel().getSelectedIndex()){
            case 0:{
                t.loadContent(paneCourse, "bscit.fxml");
            }break;
            /*case 1:{
                t.loadContent(paneCourse, "bsccs.fxml");
            }break;*/
        }
    }
   
    
    @FXML
    private void onLogout(ActionEvent event)
    {
        Platform.exit();
        System.exit(0);
    }
        
}
