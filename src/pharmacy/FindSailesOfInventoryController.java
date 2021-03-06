/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static pharmacy.DatabaseAPI.connection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FindSailesOfInventoryController implements Initializable {
private Connection connect; 
private PreparedStatement preparedStatement ;
     Stage stage2;
    Parent root2;
    Scene scene2;

      @FXML
    private TableView<SalesDetailsSearch> tableVeiwSaile;

    @FXML
    private TableColumn<SalesDetailsSearch, String> saleIDColumn;

    @FXML
    private TableColumn<SalesDetailsSearch, String> inventoryIDColumn;

    @FXML
    private TableColumn<SalesDetailsSearch, String> inventoryNameColumn;

    @FXML
    private TableColumn<SalesDetailsSearch, String> priceColumn;

    @FXML
    private TableColumn<SalesDetailsSearch, String> quantityCloumn;

    @FXML
    private TableColumn<SalesDetailsSearch, String> dateColumn;

    @FXML
    private Button findSakeInDayButton;
        @FXML
    private Button backButton;
        
    @FXML
    private TextField IDInventory;
    
  private ObservableList<SalesDetailsSearch>data2;

  
    @FXML
  void loadSalesofInventoryID(ActionEvent e) throws SQLException {    
    
           connect=DatabaseAPI.createConnection();
              try {
                  Statement stat = connection.createStatement();
            Connection conn = DatabaseAPI.createConnection();
            data2 = FXCollections.observableArrayList();
            
     
    int i=0;
            // Execute query and store result in a resultset
          ResultSet  res1 = stat.executeQuery("SELECT S.sid,S.iid,"
                    + " I.name,S.sprice,S.squanity,S.sdate FROM  sailes AS S,inventory as I\n"
          +"where S.iid="+" '"+Integer.parseInt(IDInventory.getText())+"' "+"and I.iid=S.iid ");
    
            while (res1.next())  
            {
             data2.add(new SalesDetailsSearch(String.valueOf(res1.getString(1)), String.valueOf(res1.getString(2)), String.valueOf(res1.getString(3)),String.valueOf(res1.getString(4)),String.valueOf(res1.getString(5)),String.valueOf(res1.getString(6))));
               i++;
            }
            if(i==0)
              showErrorNotFound();
          



        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
           
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        saleIDColumn.setCellValueFactory(new PropertyValueFactory<>("saileID"));
        inventoryIDColumn.setCellValueFactory(new PropertyValueFactory<>("invntoryID"));
        inventoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCloumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        
       // tableVeiwSaile.setItems(null);
        tableVeiwSaile.setItems(data2);
       
  }
  
          	private void showErrorNotFound() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setTitle("Error !!");
		alert.setHeaderText("Not Found");
		alert.setContentText("Date is not found ");

		alert.showAndWait();
	}
                          	private void showError() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setTitle("Error !!");
		alert.setHeaderText("Input Error");
		alert.setContentText("Date is not found ");

		alert.showAndWait();
	}
    
                                @FXML
    void goBackToSaleMenu(ActionEvent event) throws IOException {

                         stage2 = (Stage)backButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("Sales.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
  
    }        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    
}
}