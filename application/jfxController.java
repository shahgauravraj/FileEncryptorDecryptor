package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.ui.Window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class jfxController {
	   @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button newuserbtn;

	    @FXML
	    private Button olduserbtn;
	    
	    @FXML
	    private Button jfxadminbtn;

	    @FXML
	    void initialize() {
	        newuserbtn.setOnAction(new EventHandler<ActionEvent>(){
	        	public void handle(ActionEvent event) {
	        		Stage stg=new Stage();
	        		stg.getIcons().add(new Image("file:///C:/CodeRespiratory/jfx/src/images/icons8-search-pain-64.png"));
	    			stg.setTitle("Secure");
	    			AnchorPane myroot=new AnchorPane();
					try {
						myroot = (AnchorPane)FXMLLoader.load(getClass().getResource("details.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		Scene myscene = new Scene(myroot,416,305);
	    			myscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    			stg.setScene(myscene);
	    			stg.show();
	        	}
	        });
	        olduserbtn.setOnAction(new EventHandler<ActionEvent>(){
	        	public void handle(ActionEvent event) {
	        		Stage stg=new Stage();
	        		stg.getIcons().add(new Image("file:///C:/CodeRespiratory/jfx/src/images/icons8-search-pain-64.png"));
	    			stg.setTitle("Secure");
	    			AnchorPane myroot=new AnchorPane();
					try {
						myroot = (AnchorPane)FXMLLoader.load(getClass().getResource("oldDetails.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		Scene myscene = new Scene(myroot,400,180);
	    			myscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    			stg.setScene(myscene);
	    			stg.show();
	        	}
	        });
	        jfxadminbtn.setOnAction(new EventHandler<ActionEvent>(){
	        	public void handle(ActionEvent event) {
	        		Stage stg=new Stage();
	        		stg.getIcons().add(new Image("file:///C:/CodeRespiratory/jfx/src/images/icons8-search-pain-64.png"));
	    			stg.setTitle("Secure");
	    			AnchorPane myroot=new AnchorPane();
					try {
						myroot = (AnchorPane)FXMLLoader.load(getClass().getResource("admin.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		Scene myscene = new Scene(myroot,400,130);
	    			myscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    			stg.setScene(myscene);
	    			stg.show();
	        	}
	        });

	    }
}
