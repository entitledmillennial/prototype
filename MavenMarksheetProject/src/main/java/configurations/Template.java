/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configurations;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author zhee
 */
public class Template {
    
    public static void dialog(Alert.AlertType alertType,String s)
    {
        Alert alert = new Alert(alertType,s);
        alert.setTitle("An Alert Dialog");
        alert.initStyle(StageStyle.UNIFIED);
        alert.showAndWait();
    }
    
    public void newStage(Label lb, String load, String title, boolean resize, StageStyle style, boolean maximized){
       try {
            Stage newStage = new Stage();
            Stage stage = (Stage) lb.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(load));
            Scene scene = new Scene(root);
            newStage.initStyle(style);
            newStage.setMinWidth(800);
            newStage.setMinHeight(600);
            newStage.setResizable(resize);
            newStage.setMaximized(maximized);
            newStage.setTitle(title);
            newStage.setScene(scene);
            newStage.show();
            stage.close();
        } catch (Exception e) {
        } 
    }
   
    
    public void loadContent(AnchorPane ap, String nameofview){
        try {
            AnchorPane targetpane = FXMLLoader.load(getClass().getResource("/views/"+nameofview));
            ap.getChildren().setAll(targetpane.getChildren());
        } catch (IOException e) {
        }   
    }
}
