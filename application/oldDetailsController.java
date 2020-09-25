package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class oldDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane oldDetailsanchpn;

    @FXML
    private Label oldDetailsNameLabel;

    @FXML
    private TextField oldDetailsNameTxtFld;

    @FXML
    private Label oldDetailsPssLabel;

    @FXML
    private PasswordField oldDetailsPssFld;

    @FXML
    private Button oldDetailsFinishBtn;
    
    static String name;
    static String password;

    @FXML
    void initialize() {
        oldDetailsFinishBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				name=oldDetailsNameTxtFld.getText();
				password=oldDetailsPssFld.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					try {
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","chowk1999");
						PreparedStatement pmt=con.prepareStatement("Select password from pssmanager where uname=(?)");
						pmt.setString(1,name);
						ResultSet rs=pmt.executeQuery();
						if(rs.next()) {
							System.out.println("Succesfully Logged in");
							if(password.equals(rs.getString("password"))) {
								pmt.close();
								Stage stage=(Stage) oldDetailsFinishBtn.getScene().getWindow();
        		                stage.close();
        		                Stage stg=new Stage();
        		        		stg.getIcons().add(new Image("file:///C:/CodeRespiratory/jfx/src/images/icons8-search-pain-64.png"));
        		    			stg.setTitle("Secure");
        		    			AnchorPane myroot=new AnchorPane();
        						try {
        							myroot = (AnchorPane)FXMLLoader.load(getClass().getResource("jfx2.fxml"));
        						} catch (IOException e) {
        							// TODO Auto-generated catch block
        							e.printStackTrace();
        						}
        		        		Scene myscene = new Scene(myroot,600,340);
        		    			myscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        		    			stg.setScene(myscene);
        		    			stg.show();
        		    			
							}
							else {
								System.out.println("Wrong password");
							}
						}
						else System.out.println("No such ID");
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
        	
        });

    }
}
