package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class adminDispalyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adDisAchPn;

    @FXML
    private Label adDisUserDetLabel;

    @FXML
    private TableView<ModelTable> adminTable;

    @FXML
    private TableColumn<ModelTable,String> adminTableName;

    @FXML
    private TableColumn<ModelTable,Date> adminTableDOB;

    @FXML
    private TableColumn<ModelTable,String> adminTableGender;

    @FXML
    private Button DisplayRebootbtn;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    @FXML
    void initialize() {
    	DisplayRebootbtn.fire();
    	adminTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	adminTableDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
    	adminTableGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    	DisplayRebootbtn.setOnAction(new EventHandler<ActionEvent>(){
          	public void handle(ActionEvent event) {
          		try {
        			Class.forName("com.mysql.jdbc.Driver");
        			try {
        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","chowk1999");
        				Statement stm=con.createStatement();
        				ResultSet rs=stm.executeQuery("SELECT * FROM userdata");
        				
        				while(rs.next()) {
        					oblist.add(new ModelTable(rs.getString("uname"),rs.getString("gender"),rs.getDate("bdate")));	
        			    	adminTable.setItems(oblist);
        				}
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		} catch (ClassNotFoundException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
          	}
    	});


    }
}
	