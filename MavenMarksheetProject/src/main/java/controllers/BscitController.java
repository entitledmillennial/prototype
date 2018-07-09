package controllers;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.springframework.context.ApplicationContext;

import animations.FadeInUpTransition;
import animations.FadeOutUpTransition;
import configurations.ConfigApplicationContext;
import configurations.Template;
import implementations.ImplementBscit;
import interfaces.InterfaceBscit;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.beans.property.SimpleFloatProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Bscit;

/**
 * FXML Controller class
 *
 * @author zhee
 */
public class BscitController implements Initializable {

    @FXML
    private AnchorPane crudPane;
    @FXML
    private Button btnPhoto;
    @FXML
    private Button pDecade;
    @FXML
    private Button nDecade;
    @FXML
    private ImageView imgPhoto;
    @FXML
    private TextField txtRollNo;
    @FXML
    private TextField txtSName;
    @FXML
    private DatePicker txtDOB;
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtMName;
    @FXML
    private TextField txtPhoneNo;
    @FXML
    private TextArea txtAddress;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton radioF;
    @FXML
    private RadioButton radioM;
    @FXML
    private RadioButton radioO;
    @FXML
    private ChoiceBox<String> chooseSem;
    @FXML
    private CheckBox chkNCC;
    @FXML
    private CheckBox chkSports;
    @FXML
    private CheckBox chkNSS;
    @FXML
    private CheckBox chkCultural;
    @FXML
    private TextField txtS1T;
    @FXML
    private TextField txtS1I;
    @FXML
    private TextField txtS1P;
    @FXML
    private TextField txtS2T;
    @FXML
    private TextField txtS2I;
    @FXML
    private TextField txtS2P;
    @FXML
    private TextField txtS3T;
    @FXML
    private TextField txtS3I;
    @FXML
    private TextField txtS3P;
    @FXML
    private TextField txtS4T;
    @FXML
    private TextField txtS4I;
    @FXML
    private TextField txtS4P;
    @FXML
    private TextField txtS5T;
    @FXML
    private TextField txtS5I;
    @FXML
    private TextField txtS5P;
    @FXML
    private Label lblS1Total;
    @FXML
    private Label lblS2Total;
    @FXML
    private Label lblS3Total;
    @FXML
    private Label lblS4Total;
    @FXML
    private Label lblS5Total;
    @FXML
    private Label lblS12345Total;
    @FXML
    private Label ordinance;
    @FXML
    private Button btnBack1;
    @FXML
    private Button btnSave1;
    @FXML
    private AnchorPane tablePane;
    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView photoForView;
    @FXML
    private Button photoCloseBtn;
    @FXML
    private TreeTableView<Bscit> tableBscit;
    @FXML
    private TreeItem<Bscit> rootMain;
    @FXML
    private TreeItem<Bscit> root1;
    @FXML
    private TreeItem<Bscit> root2;
    @FXML
    private TreeItem<Bscit> root3;
    @FXML
    private TreeItem<Bscit> root4;
    @FXML
    private TreeItem<Bscit> root5;
    @FXML
    private TreeItem<Bscit> root6;
    @FXML
    private TreeTableColumn<Bscit, String> colSemester;
    @FXML
    private TreeTableColumn<Bscit, Boolean> colAction;
    @FXML
    private TreeTableColumn<Bscit, Boolean> colPhoto;
    @FXML
    private TreeTableColumn<Bscit, String> colName;
    @FXML
    private TreeTableColumn<Bscit, Number> colTotal;
    @FXML
    private TreeTableColumn<Bscit, Number> colPercentage;
    @FXML
    private TreeTableColumn<Bscit, String> colGrade;
    @FXML
    private TreeTableColumn<Bscit, Number> colCGPA;
    @FXML
    private ImageView imgLoad;
    @FXML
    private ProgressBar progBar;
    @FXML
    private Button btnNewRecord;
    @FXML
    private Button btnPrint;
    @FXML
    private TextField txtSearch;
    
    
    @FXML
    private AnchorPane printPane;
    @FXML
    private AnchorPane marksheetPane;
    @FXML
    private AnchorPane snapshotPane;
    @FXML
	private ImageView iv;
    
    final int IMG_WIDTH = 920;
	final int IMG_HEIGHT = 550;
    
    private ObservableList<Bscit> listSem1,listSem2,listSem3,listSem4,listSem5,listSem6;
    private ObservableList<String> listTemp;
    
    InterfaceBscit crud;
    
    private File file; //For choosing the image file.
    
    String selectedGender;
    
    private Integer status;
    
    //Marksheet fields.....
    @FXML private Label s1i;@FXML private Label s2i;@FXML private Label s3i;@FXML private Label s4i;@FXML private Label s5i;
    @FXML private Label s1t;@FXML private Label s2t;@FXML private Label s3t;@FXML private Label s4t;@FXML private Label s5t;
    @FXML private Label s1p;@FXML private Label s2p;@FXML private Label s3p;@FXML private Label s4p;@FXML private Label s5p;
    @FXML private Label s1ittotal;@FXML private Label s2ittotal;@FXML private Label s3ittotal;@FXML private Label s4ittotal;@FXML private Label s5ittotal;
    @FXML private Label s1pstar;@FXML private Label s2pstar;@FXML private Label s3pstar;@FXML private Label s4pstar;@FXML private Label s5pstar;
    @FXML private Label s1git;@FXML private Label s2git;@FXML private Label s3git;@FXML private Label s4git;@FXML private Label s5git;
    @FXML private Label s1gp;@FXML private Label s2gp;@FXML private Label s3gp;@FXML private Label s4gp;@FXML private Label s5gp;
    @FXML private Label s1gpit;@FXML private Label s2gpit;@FXML private Label s3gpit;@FXML private Label s4gpit;@FXML private Label s5gpit;
    @FXML private Label s1gpp;@FXML private Label s2gpp;@FXML private Label s3gpp;@FXML private Label s4gpp;@FXML private Label s5gpp;
    @FXML private Label s1cpit;@FXML private Label s2cpit;@FXML private Label s3cpit;@FXML private Label s4cpit;@FXML private Label s5cpit;
    @FXML private Label s1cpp;@FXML private Label s2cpp;@FXML private Label s3cpp;@FXML private Label s4cpp;@FXML private Label s5cpp;
    @FXML private Label s1cxgit;@FXML private Label s2cxgit;@FXML private Label s3cxgit;@FXML private Label s4cxgit;@FXML private Label s5cxgit;
    @FXML private Label s1cxgp;@FXML private Label s2cxgp;@FXML private Label s3cxgp;@FXML private Label s4cxgp;@FXML private Label s5cxgp;
    @FXML private Label cgpa;
    @FXML private Label mDate;@FXML private Label mPlace;
    @FXML private ImageView mPhoto;
    @FXML private Label mRollNo;@FXML private Label mSName;@FXML private Label mYear;@FXML private Label mSem;@FXML private Label mProg;
    @FXML private Text s1name;@FXML private Text s2name;@FXML private Text s3name;@FXML private Text s4name;@FXML private Text s5name;
    @FXML private Label mCC1;@FXML private Label mCC2;@FXML private Label mCC3;@FXML private Label mCC4;@FXML private Label mCC5;
    @FXML private Label mCT1;@FXML private Label mCT2;@FXML private Label mCT3;@FXML private Label mCT4;@FXML private Label mCT5;
    @FXML private Label cptotal;@FXML private Label cptotalstar;@FXML private Label cxgtotal;
    @FXML private Label mFinalGrade;@FXML private Label mRemark;
    @FXML private Label mSem1;@FXML private Label mSem2;@FXML private Label mSem3;@FXML private Label mSem4;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    	Platform.runLater(() -> {

    		ApplicationContext context = ConfigApplicationContext.getInstance().getApplicationContext();
            crud = context.getBean(ImplementBscit.class);
            
    		chooseSem.setItems(FXCollections.observableArrayList("Semester 1","Semester 2","Semester 3",
					"Semester 4","Semester 5","Semester 6"));
            
    		listSem1=listSem2=listSem3=listSem4=listSem5=listSem6 = FXCollections.observableArrayList();
    		
    		rootMain = new TreeItem<>(new Bscit("Root"));
    		root1 = new TreeItem<>(new Bscit("Sem One"));	root2 = new TreeItem<>(new Bscit("Sem Two"));	root3 = new TreeItem<>(new Bscit("Sem Three"));
    		root4 = new TreeItem<>(new Bscit("Sem Four"));	root5 = new TreeItem<>(new Bscit("Sem Five"));	root6 = new TreeItem<>(new Bscit("Sem Six"));
    		
    		
    		colSemester.setCellValueFactory(param -> {
				if(param.getValue().getValue().getSem()!=null)
					return new SimpleStringProperty(param.getValue().getValue().getSem());
				else
					return null;
			});
    			
    		colAction.setCellValueFactory((TreeTableColumn.CellDataFeatures<Bscit, Boolean> param) -> new SimpleBooleanProperty(param.getValue()!=null));
    		colAction.setCellFactory((TreeTableColumn<Bscit, Boolean> param) -> new ButtonCell(tableBscit));

    		colPhoto.setCellValueFactory((TreeTableColumn.CellDataFeatures<Bscit, Boolean> param) -> new SimpleBooleanProperty(param.getValue()!=null));
    		colPhoto.setCellFactory((TreeTableColumn<Bscit, Boolean> param) -> new PhotoButtonCell(tableBscit));
    		
    		colName.setCellValueFactory(param -> {
				if(param.getValue().getValue().getStudname()!=null)
					return new SimpleStringProperty(param.getValue().getValue().getStudname());
				else
					return null;
			});
    		colTotal.setCellValueFactory(param -> {
				if(param.getValue().getValue().getGrandtotal()!=null)
					return new SimpleIntegerProperty(param.getValue().getValue().getGrandtotal());
				else
					return null;
			});
    		colPercentage.setCellValueFactory(param -> {
				if(param.getValue().getValue().getPercentage()!=null)
					return new SimpleFloatProperty(param.getValue().getValue().getPercentage());
				else
					return null;
			});
    		colGrade.setCellValueFactory(param -> {
				if(param.getValue().getValue().getFinalgrade()!=null)
					return new SimpleStringProperty(param.getValue().getValue().getFinalgrade());
				else
					return null;
			});
    		colCGPA.setCellValueFactory(param -> {
				if(param.getValue().getValue().getCgpa()!=null)
					return new SimpleFloatProperty(param.getValue().getValue().getCgpa());
				else
					return null;
			});
    		
    		selectWithService();
    		
    		//txtSearch.textProperty().addListener((observable, oldValue, newValue) -> filterChanged(newValue));
    		
    		btnSave1.setDisable(false);	
		});
       
    }
    
    @FXML
    private void filterChanged() {
    	String filter = txtSearch.getText();
        if (filter.isEmpty()) {
            tableBscit.setRoot(rootMain);         
        }
        else {
            TreeItem<Bscit> filteredRoot = new TreeItem<Bscit>();
            filter(rootMain, filter, filteredRoot);
            tableBscit.setRoot(filteredRoot);
        }
    }


    private void filter(TreeItem<Bscit> root, String filter, TreeItem<Bscit> filteredRoot) {
        for (TreeItem<Bscit> child : root.getChildren()) {            
            TreeItem<Bscit> filteredChild = new TreeItem<Bscit>();
            filteredChild.setValue(child.getValue());
            filteredChild.setExpanded(true);
            filter(child, filter, filteredChild );
            if (!filteredChild.getChildren().isEmpty() || isMatch(filteredChild.getValue(), filter)) {
                filteredRoot.getChildren().add(filteredChild);
            }
        }
    }

    private boolean isMatch(Bscit value, String filter) {
    	ObservableList<String> tempList = FXCollections.observableArrayList();
    	tempList.add(value.getStudname());
    	tempList.removeAll(Collections.singleton(null));

        return tempList.stream().anyMatch((Object o) -> o.toString().contains(filter));
    }
    
    
    private void selectWithService(){
        Service<Integer> service = new Service<Integer>() {
            @Override
            protected Task<Integer> createTask() {
                selectData();
                return new Task<Integer>() {           
                    @Override
                    protected Integer call() throws Exception {
                        Integer max = crud.select().size();
                        if (max > 35) {
                            max = 35;
                        }
                        updateProgress(0, max);
                        for (int k = 0; k < max; k++) {
                            Thread.sleep(30);
                            updateProgress(k+1, max);
                        }
                        return max;
                    }
                };
            }
        };
        service.start();
        progBar.progressProperty().bind(service.progressProperty());
        service.setOnRunning((WorkerStateEvent event) -> {
            imgLoad.setVisible(true);
        });
        service.setOnSucceeded((WorkerStateEvent event) -> {
            imgLoad.setVisible(false);
            //new FadeInUpTransition(tablePane).play();
            
            if(new FadeInUpTransition(tablePane).getStatus() == Animation.Status.STOPPED)
            {
                tablePane.setDisable(false);
            }
        });
    }
    
    private void selectData(){
        
    	if(listSem1 == null){
        	listSem1 = FXCollections.observableArrayList(crud.select("Semester 1"));
        }else {
            listSem1.clear();
            listSem1.addAll(crud.select("Semester 1"));
        }
    		listSem1.stream().forEach((listSem1) -> {
    			root1.getChildren().add(new TreeItem<Bscit>(listSem1));
    		});
        
        if(listSem2 == null){
        	listSem2 = FXCollections.observableArrayList(crud.select("Semester 2"));
        }else {
            listSem2.clear();
            listSem2.addAll(crud.select("Semester 2"));
        }
	        listSem2.stream().forEach((listSem2) -> {
	        	root2.getChildren().add(new TreeItem<>(listSem2));
	        });
        
        if(listSem3 == null){
            listSem3 = FXCollections.observableArrayList(crud.select("Semester 3"));
        }else {
            listSem3.clear();
            listSem3.addAll(crud.select("Semester 3"));
        }
	        listSem3.stream().forEach((listSem3) -> {
	        	root3.getChildren().add(new TreeItem<>(listSem3));
	        });
        
        if(listSem4 == null){
            listSem4 = FXCollections.observableArrayList(crud.select("Semester 4"));
        }else {
            listSem4.clear();
            listSem4.addAll(crud.select("Semester 4"));
        }
	        listSem4.stream().forEach((listSem4) -> {
	        	root4.getChildren().add(new TreeItem<>(listSem4));
	        });
        
        if(listSem5 == null){
            listSem5 = FXCollections.observableArrayList(crud.select("Semester 5"));
        }else {
            listSem5.clear();
            listSem5.addAll(crud.select("Semester 5"));
        }
	        listSem5.stream().forEach((listSem5) -> {
	        	root5.getChildren().add(new TreeItem<>(listSem5));
	        });
        
        if(listSem6 == null){
            listSem6 = FXCollections.observableArrayList(crud.select("Semester 6"));
        }else {
            listSem6.clear();
            listSem6.addAll(crud.select("Semester 6"));
        } 
	        listSem6.stream().forEach((listSem6) -> {
	        	root6.getChildren().add(new TreeItem<>(listSem6));
	        });
	        
        	rootMain.getChildren().setAll(root1,root2,root3,root4,root5,root6);
        	root1.setExpanded(true);root2.setExpanded(true);root3.setExpanded(true);
        	root4.setExpanded(true);root5.setExpanded(true);root6.setExpanded(true);
        	tableBscit.setRoot(rootMain);
        	tableBscit.setShowRoot(false);
    }
  
    
    private void selectedRowData(MouseEvent me)
    {
        if(status==1)
        {
            try{
            Bscit b = tableBscit.getSelectionModel().getSelectedItem().getValue();
            imgPhoto.setImage(toImage(b.getPhoto()));
            txtRollNo.setText(String.valueOf(b.getRollno()));
            txtSName.setText(b.getStudname());
            txtDOB.setValue(LocalDate.parse(b.getDob()));
            txtFName.setText(b.getFname());
            txtMName.setText(b.getMname());
            txtPhoneNo.setText(b.getPhone());
            txtAddress.setText(b.getAddress());
            
            if(b.getGender().equals("Male"))gender.selectToggle(radioF);
            else if(b.getGender().equals("Female"))gender.selectToggle(radioF);
            else gender.selectToggle(radioO);
            
            chooseSem.setValue(b.getSem());
            
            if(b.getSem().equals("Semester 1")){
            	s1name.setText("Imperative \n Programming");s2name.setText("Digital \n Electronics");s3name.setText("Operating \n Systems");s4name.setText("Discrete \n Mathematics");s5name.setText("Communication \n Skills");
            }
            else if(b.getSem().equals("Semester 2")){
            	s1name.setText("Object \n oriented \n Programming");s2name.setText("Microprocessor \n Architecture");s3name.setText("Web \n Programming");s4name.setText("Numerical and \n Statistical \n Methods");s5name.setText("Green \n Computing");
            }
            else if(b.getSem().equals("Semester 3")){
            	s1name.setText("Logic and \n Discrete \n Mathematics");s2name.setText("Computer \n Graphics");s3name.setText("Advanced \n SQL");s4name.setText("Object Oriented \n Programming \n with C++");s5name.setText("Modern \n Operating \n Systems");
            }
            else if(b.getSem().equals("Semester 4")){
            	s1name.setText("Software \n Engineering");s2name.setText("Multimedia");s3name.setText("Java and \n Data \n Structures");s4name.setText("Quantitative \n Techniques");s5name.setText("Embedded \n Systems");
            }
            else if(b.getSem().equals("Semester 5")){
            	s1name.setText("Network \n Security");s2name.setText("Asp.Net \n with C#");s3name.setText("Software \n Testing");s4name.setText("Advanced \n Java");s5name.setText("Linux \n Administration");
            }
            else if(b.getSem().equals("Semester 6")){
            	s1name.setText("Internet =\n Technology");s2name.setText("Project \n Management");s3name.setText("Data \n Warehouse");s4name.setText("Geographic \n Information \n Systems");s5name.setText("Project");
            }
            else{
            	s1name.setText("Subject 1");s2name.setText("Subject 2");s3name.setText("Subject 3");s4name.setText("Subject 4");s5name.setText("Subject 5");
            }
            
            //checkboxes
            
            txtS1T.setText(String.valueOf(b.getS1t())); txtS1I.setText(String.valueOf(b.getS1i())); txtS1P.setText(String.valueOf(b.getS1p()));
            txtS2T.setText(String.valueOf(b.getS2t())); txtS2I.setText(String.valueOf(b.getS2i())); txtS3P.setText(String.valueOf(b.getS3p()));
            txtS3T.setText(String.valueOf(b.getS3t())); txtS3I.setText(String.valueOf(b.getS3i())); txtS3P.setText(String.valueOf(b.getS3p()));
            txtS4T.setText(String.valueOf(b.getS4t())); txtS4I.setText(String.valueOf(b.getS4i())); txtS4P.setText(String.valueOf(b.getS4p()));
            txtS5T.setText(String.valueOf(b.getS5t())); txtS5I.setText(String.valueOf(b.getS5i())); txtS5P.setText(String.valueOf(b.getS5p()));
            
            lblS1Total.setText(String.valueOf(b.getS1itptotal())); lblS2Total.setText(String.valueOf(b.getS2itptotal()));
            lblS3Total.setText(String.valueOf(b.getS3itptotal())); lblS4Total.setText(String.valueOf(b.getS4itptotal()));
            lblS5Total.setText(String.valueOf(b.getS5itptotal()));
            lblS12345Total.setText(String.valueOf(b.getGrandtotal()));
            }
            catch(Exception e)
            {}
        }
    }
    
    private void fillMarksheet()
    {
    	try{
    		 Bscit b = tableBscit.getSelectionModel().getSelectedItem().getValue();
    		 s1t.setText(String.valueOf(b.getS1t())); s1i.setText(String.valueOf(b.getS1i())); s1p.setText(String.valueOf(b.getS1p()));
             s2t.setText(String.valueOf(b.getS2t())); s2i.setText(String.valueOf(b.getS2i())); s2p.setText(String.valueOf(b.getS3p()));
             s3t.setText(String.valueOf(b.getS3t())); s3i.setText(String.valueOf(b.getS3i())); s3p.setText(String.valueOf(b.getS3p()));
             s4t.setText(String.valueOf(b.getS4t())); s4i.setText(String.valueOf(b.getS4i())); s4p.setText(String.valueOf(b.getS4p()));
             s5t.setText(String.valueOf(b.getS5t())); s5i.setText(String.valueOf(b.getS5i())); s5p.setText(String.valueOf(b.getS5p()));
             s1git.setText(b.getS1git());s2git.setText(b.getS2git());s3git.setText(b.getS3git());s4git.setText(b.getS4git());s5git.setText(b.getS5git());
             s1gp.setText(b.getS1gp());s2gp.setText(b.getS2gp());s3gp.setText(b.getS3gp());s4gp.setText(b.getS4gp());s5gp.setText(b.getS5gp());
             s1gpit.setText(String.valueOf(b.getS1gpit()));s2gpit.setText(String.valueOf(b.getS2gpit()));s3gpit.setText(String.valueOf(b.getS3gpit()));s4gpit.setText(String.valueOf(b.getS4gpit()));s5gpit.setText(String.valueOf(b.getS5gpit()));
             s1gpp.setText(String.valueOf(b.getS1gpp()));s2gpp.setText(String.valueOf(b.getS2gpp()));s3gpp.setText(String.valueOf(b.getS3gpp()));s4gpp.setText(String.valueOf(b.getS4gpp()));s5gpp.setText(String.valueOf(b.getS5gpp()));
             s1cpit.setText(String.valueOf(b.getS1cpit()));s2cpit.setText(String.valueOf(b.getS2cpit()));s3cpit.setText(String.valueOf(b.getS3cpit()));s4cpit.setText(String.valueOf(b.getS4cpit()));s5cpit.setText(String.valueOf(b.getS5cpit()));
             s1cpp.setText(String.valueOf(b.getS1cpp()));s2cpp.setText(String.valueOf(b.getS2cpp()));s3cpp.setText(String.valueOf(b.getS3cpp()));s4cpp.setText(String.valueOf(b.getS4cpp()));s5cpp.setText(String.valueOf(b.getS5cpp()));
             s1cxgit.setText(String.valueOf(b.getS1cxgit()));s2cxgit.setText(String.valueOf(b.getS2cxgit()));s3cxgit.setText(String.valueOf(b.getS3cxgit()));s4cxgit.setText(String.valueOf(b.getS4cxgit()));s5cxgit.setText(String.valueOf(b.getS5cxgit()));
             s1ittotal.setText(String.valueOf(b.getS1itptotal()));s2ittotal.setText(String.valueOf(b.getS2itptotal()));s3ittotal.setText(String.valueOf(b.getS3itptotal()));s4ittotal.setText(String.valueOf(b.getS4itptotal()));s5ittotal.setText(String.valueOf(b.getS5itptotal()));
             s1pstar.setText(String.valueOf(b.getS1p()));s2pstar.setText(String.valueOf(b.getS2p()));s3pstar.setText(String.valueOf(b.getS3p()));s4pstar.setText(String.valueOf(b.getS4p()));s5pstar.setText(String.valueOf(b.getS5p()));
             
             cgpa.setText(String.valueOf(b.getCgpa()));
             
             mDate.setText("Date : " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
             mPlace.setText("Place: Vashi, Navi Mumbai.");
             
             s1cxgp.setText(String.valueOf(b.getS1cxgp()));s2cxgp.setText(String.valueOf(b.getS2cxgp()));s3cxgp.setText(String.valueOf(b.getS3cxgp()));s4cxgp.setText(String.valueOf(b.getS4cxgp()));s5cxgp.setText(String.valueOf(b.getS5cxgp()));
              
             mPhoto.setImage(toImage(b.getPhoto()));
             mRollNo.setText(String.valueOf(b.getRollno()));mSName.setText(b.getStudname());
             
             if(b.getSem().equals("Semester 1")){
            	mCC1.setText("USIT101"); mCC2.setText("USIT102");mCC3.setText("USIT103");mCC4.setText("USIT104");mCC5.setText("USIT105");
            	mCT1.setText("Imperative \n Programming");mCT2.setText("Digital \n Electronics");mCT3.setText("Operating \n Systems");mCT4.setText("Discrete \n Mathematics");mCT5.setText("Communication \n Skills");
             	mSem.setText("SEMESTER I");
             	mSem1.setText("");mSem2.setText("");mSem3.setText("");mSem4.setText("");
             }else if(b.getSem().equals("Semester 2")){
            	 mCC1.setText("USIT201"); mCC2.setText("USIT202");mCC3.setText("USIT203");mCC4.setText("USIT204");mCC5.setText("USIT205");
            	 mCT1.setText("Object \n oriented \n Programming");mCT2.setText("Microprocessor \n Architecture");mCT3.setText("Web \n Programming");mCT4.setText("Numerical and \n Statistical \n Methods");mCT5.setText("Green \n Computing");
            	 mSem.setText("SEMESTER II");
            	 mSem1.setText("");mSem2.setText("");mSem3.setText("");mSem4.setText("");
             }else if(b.getSem().equals("Semester 3")){
            	 mCC1.setText("USIT301"); mCC2.setText("USIT302");mCC3.setText("USIT303");mCC4.setText("USIT304");mCC5.setText("USIT305");
            	 mCT1.setText("Logic and \n Discrete \n Mathematics");mCT2.setText("Computer \n Graphics");mCT3.setText("Advanced \n SQL");mCT4.setText("Object Oriented \n Programming \n with C++");mCT5.setText("Modern \n Operating \n Systems");
            	 mSem.setText("SEMESTER III");
            	 mSem1.setText("");mSem2.setText("");mSem3.setText("");mSem4.setText("");
             }else if(b.getSem().equals("Semester 4")){
            	 mCC1.setText("USIT401"); mCC2.setText("USIT402");mCC3.setText("USIT403");mCC4.setText("USIT404");mCC5.setText("USIT405");
            	 mCT1.setText("Software \n Engineering");mCT2.setText("Multimedia");mCT3.setText("Java and \n Data \n Structures");mCT4.setText("Quantitative \n Techniques");mCT5.setText("Embedded \n Systems");
            	 mSem.setText("SEMESTER IV");
            	 mSem1.setText("");mSem2.setText("");mSem3.setText("");mSem4.setText("");
             }else if(b.getSem().equals("Semester 5")){
            	 mCC1.setText("USIT501"); mCC2.setText("USIT502");mCC3.setText("USIT503");mCC4.setText("USIT504");mCC5.setText("USIT505");
            	 mCT1.setText("Network \n Security");mCT2.setText("Asp.Net \n with C#");mCT3.setText("Software \n Testing");mCT4.setText("Advanced \n Java");mCT5.setText("Linux \n Administration");
            	 mSem.setText("SEMESTER V");
            	 mSem1.setText("");mSem2.setText("");mSem3.setText("");mSem4.setText("");
             }else if(b.getSem().equals("Semester 6")){
            	 mCC1.setText("USIT601"); mCC2.setText("USIT602");mCC3.setText("USIT603");mCC4.setText("USIT604");mCC5.setText("USIT605");
            	 mCT1.setText("Internet =\n Technology");mCT2.setText("Project \n Management");mCT3.setText("Data \n Warehouse");mCT4.setText("Geographic \n Information \n Systems");mCT5.setText("Project");
            	 mSem.setText("SEMESTER VI");
            	 mSem1.setText("");mSem2.setText("");mSem3.setText("");mSem4.setText("");
             }
             
             if(b.getSem().equals("Semester 1") || b.getSem().equals("Semester 3") || b.getSem().equals("Semester 5")){
            	 mYear.setText("NOVEMBER - " + new SimpleDateFormat("yyyy").format(new Date()));
             }else if(b.getSem().equals("Semester 2") || b.getSem().equals("Semester 4") || b.getSem().equals("Semester 6")){
            	 mYear.setText("MARCH - " + new SimpleDateFormat("yyyy").format(new Date()));
             }
    		
             cxgtotal.setText("ΣCG=" + b.getCxgtotal());
             cptotal.setText("Credit Earned = 20");
             cptotalstar.setText("ΣC=20");
             mFinalGrade.setText(b.getFinalgrade());
             
             if(Integer.valueOf(s1i.getText()) < 10 || Integer.valueOf(s2i.getText()) < 10 || Integer.valueOf(s3i.getText()) < 10 || 
            	Integer.valueOf(s4i.getText()) < 10 || Integer.valueOf(s5i.getText()) < 10 || 
            	Integer.valueOf(s1p.getText()) < 20 || Integer.valueOf(s2p.getText()) < 20 || Integer.valueOf(s3p.getText()) < 20 || 	
            	Integer.valueOf(s4p.getText()) < 20 || Integer.valueOf(s5p.getText()) < 20 ||
            	Integer.valueOf(s1t.getText()) < 30 || Integer.valueOf(s2t.getText()) < 30 || Integer.valueOf(s3t.getText()) < 30 || 
            	Integer.valueOf(s4t.getText()) < 30 || Integer.valueOf(s5t.getText()) < 30)
             {
            	 mRemark.setText("Remark : Unsuccessful");
             }
             else
            	 mRemark.setText("Remark : Successful/Eligible");
            	 
             mProg.setText("PROGRAMME : BACHELOR OF SCIENCE (I.T)");
    	}
    	catch(Exception e)
    	{}
    }

    private class ButtonCell extends TreeTableCell<Bscit, Boolean> {
    	final Hyperlink cellButtonView = new Hyperlink("View");
        final Hyperlink cellButtonDelete = new Hyperlink("Delete");
        final Hyperlink cellButtonEdit = new Hyperlink("Edit");
        final Hyperlink cellButtonPrint = new Hyperlink("Print");
        final HBox hb = new HBox(cellButtonView,cellButtonDelete,cellButtonEdit,cellButtonPrint);
        ButtonCell(final TreeTableView<Bscit> tblView){
            hb.setSpacing(4);
            
            cellButtonDelete.setOnAction((ActionEvent t) -> {
                status = 1;
                int row = getTreeTableRow().getIndex();
                tableBscit.getSelectionModel().select(row);
                selectedRowData(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Delete Data "+txtSName.getText()+" ?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Bscit b = new Bscit();
                    b.setRollno(Integer.parseInt(txtRollNo.getText()));
                    crud.delete(b);
                    clear();
                    initialize(null, null);
                }else{
                    clear();
                    initialize(null, null);
                    //selectWithService();
                    //auto();
                }
                status = 0;
            });
            
            cellButtonEdit.setOnAction((ActionEvent event) -> {
                status = 1;
                btnSave1.setDisable(false);
                int row = getTreeTableRow().getIndex();
                tableBscit.getSelectionModel().select(row);
                selectedRowData(null);
                
                new FadeOutUpTransition(tablePane).play();
                new FadeInUpTransition(crudPane).play();
                /*
                tablePane.setOpacity(0);
                crudPane.toFront();
                //crudPane.setVisible(true);
                crudPane.setOpacity(1);
                */
                status = 0;
            });
            
            cellButtonView.setOnAction((ActionEvent event) -> {
                status = 1;
                btnSave1.setDisable(true);
                int row = getTreeTableRow().getIndex();
                tableBscit.getSelectionModel().select(row);
                selectedRowData(null);
                
                new FadeOutUpTransition(tablePane).play();
                new FadeInUpTransition(crudPane).play();
                /*
                tablePane.setOpacity(0);
                crudPane.toFront();
                //crudPane.setVisible(true);
                crudPane.setOpacity(1);
                */
                status = 0;
            });
            
            cellButtonPrint.setOnAction((ActionEvent event) -> {
                //status = 1;
                
                int row = getTreeTableRow().getIndex();
                tableBscit.getSelectionModel().select(row);
                fillMarksheet();
                
                System.out.println("inside print method");
        		
        		File file = new File("src\\main\\resources\\images\\marksheet.png");
        		printPane.toFront();
        		printPane.setOpacity(1.0);
        		marksheetPane.setOpacity(1.0);
        		    WritableImage image1 = marksheetPane.snapshot(new SnapshotParameters(), null);
        		    
        		    BufferedImage image = SwingFXUtils.fromFXImage(image1, null);

        			int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
        			
        			BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        			Graphics2D g = resizedImage.createGraphics();
        			g.drawImage(image, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        			g.dispose();
        		 
        		    
        		    try {
        		        ImageIO.write(resizedImage, "png", file);
        		    } catch (IOException e) {
        		        // TODO: handle exception here
        		    }
        		    
        		    Image img = new Image(file.toURI().toString());
        		    
        		    iv.setImage(img);
        		    printPane.setOpacity(0.0);
        		    printPane.toBack();
        		    
        		    new FadeOutUpTransition(tablePane).play();
                    new FadeInUpTransition(printPane).play();
                    
                    


                //status = 0;
            });
        
        }
        
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            TreeItem<Bscit> c = getTreeTableRow().getTreeItem();
            if(!empty && !(c==root1) && !(c==root2) && !(c==root3) && !(c==root4) && !(c==root5) && !(c==root6))
            {
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        }  
    }
    
    
    
  //Used in PhotoButtonCell inner class.
    private Image selectRowPhoto(MouseEvent me) {
        Bscit b = tableBscit.getSelectionModel().getSelectedItem().getValue();
        Image tempPhoto = toImage(b.getPhoto());
        
        //For the uniformity of all photos.
        BufferedImage originalImage = SwingFXUtils.fromFXImage(tempPhoto, null);
        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizeImagePng = resizeImage(originalImage, type);
        File tempFile = new File("C:\\Users\\zhee\\Documents\\NetBeansProjects\\MavenMarksheetProject\\src\\main\\resources\\images\\tempFile.png");
        try {
            ImageIO.write(resizeImagePng, "png", tempFile);
        } catch (IOException ex) {
            //Logger.getLogger(BscitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image rowPhoto = new Image(tempFile.toURI().toString());
        return rowPhoto;
    }
    
    //Used by selectRowPhoto() method.
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
        final int IMG_WIDTH = 1024;
	final int IMG_HEIGHT = 1024;
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();

	return resizedImage;
    }
    
    
    
    private class PhotoButtonCell extends TreeTableCell<Bscit, Boolean>{
        final Hyperlink cellButtonPhoto = new Hyperlink("View");
        final HBox hb =new HBox(cellButtonPhoto);

        public PhotoButtonCell(TreeTableView<Bscit> tv) {
            cellButtonPhoto.setOnAction((ActionEvent ae) -> {
                int row = getTreeTableRow().getIndex();
                tableBscit.getSelectionModel().select(row);

                tablePane.setDisable(true);
                tablePane.setOpacity(0.5);   
                stackPane.setDisable(false);
                stackPane.setOpacity(1);
                stackPane.toFront();

                photoForView.setImage(selectRowPhoto(null));
                
                ScaleTransition photoTransition = new ScaleTransition(Duration.seconds(1), photoForView);
                photoTransition.setFromX(1);
                photoTransition.setFromY(1);
                photoTransition.setToX(20.0);
                photoTransition.setToY(20.0);
                photoTransition.setCycleCount(1);
                photoTransition.play();
                
                photoTransition.setOnFinished((event) -> {
                    photoCloseBtn.setOpacity(1.0);
                });
                         
            });
        }
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            TreeItem<Bscit> c = getTreeTableRow().getTreeItem();
            if(!empty && !(c==root1) && !(c==root2) && !(c==root3) && !(c==root4) && !(c==root5) && !(c==root6)){
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        } 
        }
    
    
    @FXML
    private void photoCloseClicked(){
        stackPane.setOpacity(0);
        stackPane.setDisable(true);
        //tablePane.toFront();
        //tablePane.setDisable(false);
        tablePane.setOpacity(1.0);
        
        initialize(null, null);
    }
    
    
    	@FXML
    	private void clickNew(ActionEvent event) {
        //tablePane.setOpacity(0);
        //---tablePane.setVisible(false);
        //---crudPane.setVisible(true);
        //txtRollno.setDisable(false);
    	btnSave1.setDisable(false);
        new FadeOutUpTransition(tablePane).play();
        new FadeInUpTransition(crudPane).play();
        Platform.runLater(() -> {
            clear();
            //auto();
        });
        };
        
        public void clear()
        {
        	imgPhoto.setImage(null);
        	txtRollNo.setText(null);
        	txtSName.setText(null);
        	txtDOB.setValue(null);
        	txtFName.setText(null);
        	txtMName.setText(null);
        	txtPhoneNo.setText(null);
        	txtAddress.setText(null);
        	gender.selectToggle(null);
        	//chooseSem.setSelectionModel(null);
        	chkNCC.setSelected(false);
        	chkSports.setSelected(false);
        	chkNSS.setSelected(false);
        	chkCultural.setSelected(false);
        	txtS1T.setText(null);txtS2T.setText(null);txtS3T.setText(null);txtS4T.setText(null);txtS5T.setText(null);
        	txtS1I.setText(null);txtS2I.setText(null);txtS3I.setText(null);txtS4I.setText(null);txtS5I.setText(null);
        	txtS1P.setText(null);txtS2P.setText(null);txtS3P.setText(null);txtS4P.setText(null);txtS5P.setText(null);
        	lblS1Total.setText(null);lblS2Total.setText(null);lblS3Total.setText(null);lblS4Total.setText(null);
        	lblS5Total.setText(null);lblS12345Total.setText(null);
        }
    
    
	    @FXML
	    private void clickBack(ActionEvent event) {
	        new FadeOutUpTransition(crudPane).play();
	        new FadeInUpTransition(tablePane).play();
	        initialize(null, null);
	    };
    
	    
	  //When user clicks to choose a photo.
	    @FXML
	    private void choosePhoto() throws NullPointerException
	    {
	        FileChooser chooser = new FileChooser();
	        
	        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	        FileChooser.ExtensionFilter extFilterBMP = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.BMP");
	        chooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterBMP);
	        
	        file = chooser.showOpenDialog(null);
	        try
	        {
	        Image img = new Image(file.toURI().toString(),200,200,false,true);
	        imgPhoto.setImage(img);
	        }
	        catch(Exception e){
	        }
	    } 
	    
	  //Convert an image into byte[] array for storing in database.
	    private byte[] tobyteArray(Image img) throws IOException
	    {
	    	
	    	//Bscit b = tableBscit.getSelectionModel().getSelectedItem().getValue();
	            	BufferedImage temp = SwingFXUtils.fromFXImage(img, null);

	                ImageIO.write(temp, "png", new File("src\\main\\resources\\images\\tempPhoto.png"));
	                file=new File("src\\main\\resources\\images\\tempPhoto.png");
	    	
	    	byte[] buffer = new byte[(int) file.length()];

	        try {
		     FileInputStream fileInputStream = new FileInputStream(file);
		     fileInputStream.read(buffer);
		     fileInputStream.close();
	        } catch (Exception e) {
		     e.printStackTrace();
	        } 
	    	
	        return buffer;
	    }
	    
	  
	  //Convert a byte[] array into an Image for GUI display purposes.
	    private Image toImage(byte[] byteArray)
	    {   
	        Image tempImage = null;
	        try{
	        tempImage = new Image(new ByteArrayInputStream(byteArray));
	        }
	        catch(Exception e){
	            e.printStackTrace();
	            }
	        
	        return tempImage;
	    }
	    
	    
	    @FXML
	    private void onGenderSelect(ActionEvent ae)
	    {
	    	if(radioF.isSelected())
	    		selectedGender = radioF.getText();
	    	if(radioM.isSelected())
	    		selectedGender = radioM.getText();
	    	if(radioO.isSelected())
	    		selectedGender = radioO.getText();
	    	
	    }
	    
	    //When user clicks Save.
        @FXML
        private void clickSave(ActionEvent ae) throws IOException
        {
            if (	imgPhoto.getImage()==null || txtRollNo.getText().isEmpty() || txtSName.getText().isEmpty() || txtDOB.getValue()==null
            		|| txtFName.getText().isEmpty() || txtMName.getText().isEmpty() || txtPhoneNo.getText().isEmpty()
            		|| txtAddress.getText().isEmpty() || gender.getSelectedToggle()==null || chooseSem.getValue()==null || txtS1T.getText().isEmpty()
            		|| txtS2T.getText().isEmpty() || txtS3T.getText().isEmpty() || txtS4T.getText().isEmpty()
            		|| txtS5T.getText().isEmpty() || txtS1I.getText().isEmpty() || txtS2I.getText().isEmpty()
            		|| txtS3I.getText().isEmpty() || txtS4I.getText().isEmpty() || txtS5I.getText().isEmpty()
            		|| txtS1P.getText().isEmpty() || txtS2P.getText().isEmpty() || txtS3P.getText().isEmpty() 
            		|| txtS4P.getText().isEmpty() || txtS5P.getText().isEmpty()
            		) {
                Template.dialog(Alert.AlertType.ERROR, "All fields are necessary. Please check again");
                txtRollNo.requestFocus();
            }else
            {
                Bscit b = new Bscit();
                
                b.setStudname(txtSName.getText());
                b.setAddress(txtAddress.getText());
                b.setDob(txtDOB.getValue().toString());
                b.setFname(txtFName.getText());
                b.setGender(selectedGender);
                b.setMname(txtMName.getText());
                b.setPhone(txtPhoneNo.getText());
                b.setRollno(Integer.valueOf(txtRollNo.getText()));
                b.setSem(chooseSem.getSelectionModel().getSelectedItem().toString());
                b.setPhoto(tobyteArray(imgPhoto.getImage()));
                
                Integer tempIT1 = Integer.valueOf(txtS1T.getText()) + Integer.valueOf(txtS1I.getText());
                Integer tempP1 = Integer.valueOf(txtS1P.getText());
                Integer tempITP1 = tempIT1 + tempP1;
                b.setS1t(Integer.valueOf(txtS1T.getText()));
                b.setS1i(Integer.valueOf(txtS1I.getText()));
                b.setS1p(Integer.valueOf(txtS1P.getText()));
                b.setS1ittotal(tempIT1);
                b.setS1itptotal(tempITP1);
                b.setS1cpit(3);
                b.setS1cpp(1);
                Integer cxg1 = Integer.valueOf(new Calc().CalcForP(tempP1, "cxg"));
                b.setS1git(new Calc().CalcForIAndT(tempIT1, "g"));
        		b.setS1gpit(Integer.valueOf(new Calc().CalcForIAndT(tempIT1, "gp")));
        		Integer cxg11 = Integer.valueOf(new Calc().CalcForIAndT(tempIT1, "cxg"));
        		b.setS1cxgit(Integer.valueOf(new Calc().CalcForIAndT(tempIT1, "cxg")));
        		b.setS1gp(new Calc().CalcForP(tempP1, "g"));
                b.setS1gpp(Integer.valueOf(new Calc().CalcForP(tempP1, "gp")));
                b.setS1cxgp(cxg1);
                
                Integer tempIT2 = Integer.valueOf(txtS2T.getText()) + Integer.valueOf(txtS2I.getText());
                Integer tempP2 = Integer.valueOf(txtS2P.getText());
                Integer tempITP2 = tempIT2 + tempP2;
                b.setS2t(Integer.valueOf(txtS2T.getText()));
                b.setS2i(Integer.valueOf(txtS2I.getText()));
                b.setS2p(Integer.valueOf(txtS2P.getText()));
                b.setS2ittotal(tempIT2);
                b.setS2itptotal(tempITP2);
                b.setS2cpit(3);
                b.setS2cpp(1);
                Integer cxg2 = Integer.valueOf(new Calc().CalcForP(tempP2, "cxg"));
                b.setS2git(new Calc().CalcForIAndT(tempIT2, "g"));
        		b.setS2gpit(Integer.valueOf(new Calc().CalcForIAndT(tempIT2, "gp")));
        		Integer cxg22=Integer.valueOf(new Calc().CalcForIAndT(tempIT2, "cxg"));
        		b.setS2cxgit(Integer.valueOf(new Calc().CalcForIAndT(tempIT2, "cxg")));
        		b.setS2gp(new Calc().CalcForP(tempP2, "g"));
                b.setS2gpp(Integer.valueOf(new Calc().CalcForP(tempP2, "gp")));
                b.setS2cxgp(cxg2);
 
                Integer tempIT3 = Integer.valueOf(txtS3T.getText()) + Integer.valueOf(txtS3I.getText());
                Integer tempP3 = Integer.valueOf(txtS3P.getText());
                Integer tempITP3 = tempIT3 + tempP3;
                b.setS3t(Integer.valueOf(txtS3T.getText()));
                b.setS3i(Integer.valueOf(txtS3I.getText()));
                b.setS3p(Integer.valueOf(txtS3P.getText()));
                b.setS3ittotal(tempIT3);
                b.setS3itptotal(tempITP3);
                b.setS3cpit(3);
                b.setS3cpp(1);
                Integer cxg3 = Integer.valueOf(new Calc().CalcForP(tempP3, "cxg"));
                b.setS3git(new Calc().CalcForIAndT(tempIT3, "g"));
        		b.setS3gpit(Integer.valueOf(new Calc().CalcForIAndT(tempIT3, "gp")));
        		Integer cxg33=Integer.valueOf(new Calc().CalcForIAndT(tempIT3, "cxg"));
        		b.setS3cxgit(Integer.valueOf(new Calc().CalcForIAndT(tempIT3, "cxg")));
        		b.setS3gp(new Calc().CalcForP(tempP3, "g"));
                b.setS3gpp(Integer.valueOf(new Calc().CalcForP(tempP3, "gp")));
                b.setS3cxgp(cxg3);
                
                Integer tempIT4 = Integer.valueOf(txtS4T.getText()) + Integer.valueOf(txtS4I.getText());
                Integer tempP4 = Integer.valueOf(txtS4P.getText());
                Integer tempITP4 = tempIT4 + tempP4;
                b.setS4t(Integer.valueOf(txtS4T.getText()));
                b.setS4i(Integer.valueOf(txtS4I.getText()));
                b.setS4p(Integer.valueOf(txtS4P.getText()));
                b.setS4ittotal(tempIT4);
                b.setS4itptotal(tempITP4);
                b.setS4cpit(3);
                b.setS4cpp(1);
                Integer cxg4 =Integer.valueOf(new Calc().CalcForP(tempP4, "cxg"));
                b.setS4git(new Calc().CalcForIAndT(tempIT4, "g"));
        		b.setS4gpit(Integer.valueOf(new Calc().CalcForIAndT(tempIT4, "gp")));
        		Integer cxg44=Integer.valueOf(new Calc().CalcForIAndT(tempIT4, "cxg"));
        		b.setS4cxgit(Integer.valueOf(new Calc().CalcForIAndT(tempIT4, "cxg")));
        		b.setS4gp(new Calc().CalcForP(tempP4, "g"));
                b.setS4gpp(Integer.valueOf(new Calc().CalcForP(tempP4, "gp")));
                b.setS4cxgp(cxg4);
                
                Integer tempIT5 = Integer.valueOf(txtS5T.getText()) + Integer.valueOf(txtS5I.getText());
                Integer tempP5 = Integer.valueOf(txtS5P.getText());
                Integer tempITP5 = tempIT5 + tempP5;
                b.setS5t(Integer.valueOf(txtS5T.getText()));
                b.setS5i(Integer.valueOf(txtS5I.getText()));
                b.setS5p(Integer.valueOf(txtS5P.getText()));
                b.setS5ittotal(tempIT5);
                b.setS5itptotal(tempITP5);
                b.setS5cpit(3);
                b.setS5cpp(1);
                Integer cxg5 = Integer.valueOf(new Calc().CalcForP(tempP5, "cxg"));
                b.setS5git(new Calc().CalcForIAndT(tempIT5, "g"));
        		b.setS5gpit(Integer.valueOf(new Calc().CalcForIAndT(tempIT5, "gp")));
        		Integer cxg55=Integer.valueOf(new Calc().CalcForIAndT(tempIT5, "cxg"));
        		b.setS5cxgit(Integer.valueOf(new Calc().CalcForIAndT(tempIT5, "cxg")));
        		b.setS5gp(new Calc().CalcForP(tempP5, "g"));
                b.setS5gpp(Integer.valueOf(new Calc().CalcForP(tempP5, "gp")));
                b.setS5cxgp(cxg5);
                
                Integer tempGT = tempIT1 + tempP1 + tempIT2 + tempP2 + tempIT3 + tempP3 + tempIT4 + tempP4 +tempIT5 + tempP5;
                Integer cxgTotal = cxg1+cxg11+cxg2+cxg22+cxg3+cxg33+cxg4+cxg44+cxg5+cxg55;
                
                b.setFinalgrade(new Calc().CalcFinalGrade(tempGT));
                b.setGrandtotal(tempGT);
                b.setPercentage((Float.valueOf(tempGT)/750)*100); 
                b.setCgpa(Float.valueOf(cxgTotal)/Float.valueOf(20));
                b.setCptotal(20);
                
                
                if(!(chkCultural.isSelected() || chkNCC.isSelected() || chkNSS.isSelected() || chkSports.isSelected()))
                {
                	b.setCxgtotal(cxgTotal);
                }
                else
                {
                	ordinance.setText("Plus 10 for \n Extra curricular \n according to \n ordinance O.229");
                	cxgTotal = cxgTotal + 10;
                	b.setCxgtotal(cxgTotal);
                	
                }
                
                crud.saveOrUpdate(b);
                //clear();
                selectData();
                Template.dialog(Alert.AlertType.INFORMATION, "Data successfully saved. . .");
            }
        }
        
        private class Calc
        {
        private String grade, gradePoint,CXG;
        private String returnVal;
        
        private String CalcForIAndT(Integer tempIT, String returnVal)
        {
        	this.returnVal = returnVal;
        	if(tempIT>=70)
        	{ 	
        		grade = "O";
        		gradePoint = "7";
        	}
        	else if(tempIT>=60 && tempIT<70)
        	{
        		grade = "A";
        		gradePoint = "6";
        	}
        	else if(tempIT>=55 && tempIT<60)
        	{
        		grade = "B";
        		gradePoint = "5";
        	}
        	else if(tempIT>=50 && tempIT<55)
        	{
        		grade = "C";
        		gradePoint = "4";
        	}
        	else if(tempIT>=45 && tempIT<50)
        	{
        		grade = "D";
        		gradePoint = "3";
        	}
        	else if(tempIT>=40 && tempIT<45)
        	{
        		grade = "E";
        		gradePoint = "2";
        	}
        	else
        	{
        		grade = "F";
        		gradePoint = "1";
        	}
        		CXG = String.valueOf(Integer.valueOf(gradePoint) * 3);

	        if(returnVal == "g")
	        	return this.grade;
	        else if(returnVal == "gp")
	        	return this.gradePoint;
	        else if(returnVal.equals("cxg"))
	        	return this.CXG;
	        else
	        	return null;
        	}
        
        private String CalcForP(Integer tempIT, String returnVal)
        {
        	this.returnVal = returnVal;
        	if(tempIT>=35)
        	{ 	
        		grade = "O";
        		gradePoint = "7";
        	}
        	else if(tempIT>=30 && tempIT<35)
        	{
        		grade = "A";
        		gradePoint = "6";
        	}
        	else if(tempIT>=28 && tempIT<30)
        	{
        		grade = "B";
        		gradePoint = "5";
        	}
        	else if(tempIT>=25 && tempIT<28)
        	{
        		grade = "C";
        		gradePoint = "4";
        	}
        	else if(tempIT>=23 && tempIT<25)
        	{
        		grade = "D";
        		gradePoint = "3";
        	}
        	else if(tempIT>=20 && tempIT<23)
        	{
        		grade = "E";
        		gradePoint = "2";
        	}
        	else
        	{
        		grade = "F";
        		gradePoint = "1";
        	}
        		CXG = String.valueOf(Integer.valueOf(gradePoint) * 1);

	        if(returnVal == "g")
	        	return this.grade;
	        else if(returnVal == "gp")
	        	return this.gradePoint;
	        else if(returnVal.equals("cxg"))
	        	return this.CXG;
	        else
	        	return null;
        	}
        
        private String CalcFinalGrade(Integer tempGT)
        {
        	if(tempGT>=525)
        	{ 	
        		grade = "O";
        	}
        	else if(tempGT>=450 && tempGT<525)
        	{
        		grade = "A";
        	}
        	else if(tempGT>=413 && tempGT<450)
        	{
        		grade = "B";
        	}
        	else if(tempGT>=375 && tempGT<413)
        	{
        		grade = "C";
        	}
        	else if(tempGT>=338 && tempGT<375)
        	{
        		grade = "D";
        	}
        	else if(tempGT>=300 && tempGT<338)
        	{
        		grade = "E";
        	}
        	else
        	{
        		grade = "F";
        	}
        	return grade;
        	}
        }   
        
        
        @FXML
    	private void print(ActionEvent ae) throws IOException
    	{
        	PrinterJob job = PrinterJob.createPrinterJob();
    		
    		if (job != null) 
    		{
    			//job.showPrintDialog(null);
    			job.showPageSetupDialog(null);

    			boolean printed = job.printPage(iv);

    			if (printed) 
    			{

    				job.endJob();
    			} 
    		}
    	}
        
        @FXML
        private void clickPDecade()
        {
        	LocalDate value = txtDOB.getValue();
            txtDOB.setValue(LocalDate.of(value.getYear()-10, value.getMonth(), value.getDayOfMonth()));
            txtDOB.show();
        }

        @FXML
        private void clickNDecade()
        {
        	txtDOB.show();
            LocalDate value = txtDOB.getValue();
            txtDOB.setValue(LocalDate.of(value.getYear()+10, value.getMonth(), value.getDayOfMonth()));
        }
}

