package controllers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BsccsController implements Initializable {

	@FXML
	private AnchorPane marksheet;
	@FXML
	private AnchorPane parentap;
	@FXML
	private Button printButton;
	@FXML
	private Button printButton2;
	@FXML
	private GridPane gp;
	@FXML
	private AnchorPane ap1;
	@FXML
	private ImageView iv;
	@FXML
	private ImageView icllogo;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		//print(null);
	}
    
    final int IMG_WIDTH = 920;
	final int IMG_HEIGHT = 550;
	@FXML
	private void print(ActionEvent ae) throws IOException
	{
		System.out.println("inside print method");
		
		File file = new File("src\\main\\resources\\images\\marksheet.png");
		ap1.toFront();
		ap1.setOpacity(1.0);
		marksheet.setOpacity(1.0);
		    WritableImage image1 = marksheet.snapshot(new SnapshotParameters(), null);
		    marksheet.setOpacity(0.0);
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
		    ap1.setOpacity(0.0);
		    ap1.toBack();
		    
	}
	
	@FXML
	private void print2(ActionEvent ae)
	{
PrinterJob job = PrinterJob.createPrinterJob();
		
		if (job != null) 
		{
			job.showPrintDialog(null);
			//job.showPageSetupDialog(null);

			boolean printed = job.printPage(marksheet);

			if (printed) 
			{

				job.endJob();
			} 
		}
	}
}
