package controllers;

import animations.FadeInLeftTransition;
import animations.FadeInRightTransition;
import animations.FadeInTransition;
import configurations.ConfigApplicationContext;
import configurations.Template;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class SplashController implements Initializable {
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblCollege;
    @FXML
    private VBox vboxBottom;
    @FXML
    private Label lblClose;
    Stage stage;
    @FXML
    private ImageView imgLoading;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        longStart();
        lblClose.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
        // TODO
    }   
    
    private void longStart() {
        Service<ApplicationContext> service = new Service<ApplicationContext>() {
            @Override
            protected Task<ApplicationContext> createTask() {
                return new Task<ApplicationContext>() {           
                    @Override
                    protected ApplicationContext call() throws Exception {
                        ApplicationContext appContex = ConfigApplicationContext.getInstance().getApplicationContext();
                        int max = appContex.getBeanDefinitionCount();
                        updateProgress(0, max);
                        for (int k = 0; k < max; k++) {
                            Thread.sleep(50);
                            updateProgress(k+1, max);
                        }
                        return appContex;
                    }
                };
            }
        };
        service.start();
        service.setOnRunning((WorkerStateEvent event) -> {
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInRightTransition(lblCollege).play();
            new FadeInTransition(vboxBottom).play();
        });
        service.setOnSucceeded((WorkerStateEvent event) -> {
        	Template template = new Template();
            template.newStage(lblClose, "/views/login.fxml", "Marksheet Generation System", true, StageStyle.UNDECORATED, false);
        });
    } 
}
