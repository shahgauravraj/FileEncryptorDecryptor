package application;

import java.awt.Desktop;
import java.io.File;
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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class jfx2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addFilesBtn;

    @FXML
    private Button openFilesBtn;

    @FXML
    private ListView<File> selectFilesList;

    @FXML
    private Label selectedFilesLabel;
    
    @FXML
    private Button showFilesBtn;
    
    @FXML
    private Button encryptBtn;

    @FXML
    private Button decryptBtn;
    
    String name;
    StringBuilder path;
    static StringBuilder path1;
    static StringBuilder path2;
    StringBuilder refpath;
    StringBuilder filename;

    @FXML
    void initialize() {
    	name=oldDetailsController.name;
    	addFilesBtn.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event) {
        		 
        		 FileChooser fc=new FileChooser();
                 File selectedFile=fc.showOpenDialog(null);
               
        		    try {
							Class.forName("com.mysql.jdbc.Driver");
							try {
								Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","chowk1999");
								PreparedStatement pmt=con.prepareStatement("Select path from file where uname=(?)");
								pmt.setString(1,name);
								ResultSet rs=pmt.executeQuery();
								if(!rs.next()) {
									System.out.println();
								}
								path=new StringBuilder(rs.getString("path"));
								path.append("\\NEW_FILES\\");
								path.append(selectedFile.getName());
		                        if(selectedFile.renameTo 
		                                (new File(path.toString()))) 
		                             { 
		                                 selectedFile.delete(); 
		                             }
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
    	showFilesBtn.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event) {
        		File dir;
        		try {
					Class.forName("com.mysql.jdbc.Driver");
					try {
						
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","chowk1999");
						PreparedStatement pmt=con.prepareStatement("Select path from file where uname=(?)");
						pmt.setString(1,name);
						ResultSet rs=pmt.executeQuery();
						if(!rs.next()) {
							System.out.println();
						}
						path=new StringBuilder(rs.getString("path"));
						dir=new File(path.toString());
						File [] list=dir.listFiles();
						if(list!=null) {
							for(File child:list) {
								selectFilesList.getItems().add(child.getAbsoluteFile());
							}
							
						}
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
    	openFilesBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				File selectedFile=selectFilesList.getSelectionModel().getSelectedItem();
                try {
                	Desktop.getDesktop().open(selectedFile);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			    }

			}
    		
    	});
    	
    	encryptBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Class.forName("com.mysql.jdbc.Driver");
					try {
						
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","chowk1999");
						PreparedStatement pmt=con.prepareStatement("Select path from file where uname=(?)");
						pmt.setString(1,name);
						ResultSet rs=pmt.executeQuery();
						if(!rs.next()) {
							System.out.println();
						}
						path1=new StringBuilder(rs.getString("path"));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Encryptor en = Encryptor.getEncrypter(true);
				//Decryptor de = Decryptor.getDecrypter(true);
				path1.append("\\PROTECTED\\");
				path2=new StringBuilder(path1.toString());
				File ref = selectFilesList.getSelectionModel().getSelectedItem();
				refpath=new StringBuilder(ref.getAbsolutePath());
				File src = new File(refpath.toString());
				en.encrypt(src, src);
				File [] dir=src.listFiles();
				if(dir!=null)
				{
					for(File f:dir) {
						filename=new StringBuilder(f.getName());
						path1.append(filename.toString());
						 if(f.renameTo 
	                                (new File(path1.toString()))) 
	                             { 
	                                 f.delete(); 
	                             }
						path1=path2;
						
					}
				}
				selectFilesList.refresh();
				selectFilesList.getItems().clear();
				showFilesBtn.fire();
		        //de.decrypt(src, src);
			}});
    	decryptBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//
				//Encryptor en = Encryptor.getEncrypter(true);
				Decryptor de = Decryptor.getDecrypter(true);
				
				File ref = selectFilesList.getSelectionModel().getSelectedItem();
				refpath=new StringBuilder(ref.getAbsolutePath());
				File src = new File(refpath.toString());

				
				//en.encrypt(src, src);
		        de.decrypt(src, src);
				
			}});

    }
}

