/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static pharmacy.DatabaseAPI.connection;

public class ShowSalesWithController {
    
      Stage stage2;
    Parent root2;
    Scene scene2;
    
private Connection connect; 
private PreparedStatement preparedStatement ;
private ObservableList<SailesDetails>data3;


    @FXML
    private TableView<SailesDetails> tableVeiwSaile;

    @FXML
    private TableColumn<SailesDetails,String> saleIDColumn;

    @FXML
    private TableColumn<SailesDetails,String> inventoryIDColumn;

    @FXML
    private TableColumn<SailesDetails,String> inventoryNameColumn;

    @FXML
    private TableColumn<SailesDetails,String> priceColumn;

    @FXML
    private TableColumn<SailesDetails,String> quantityCloumn;

    @FXML
    private TableColumn<SailesDetails,String> dateColumn;

    @FXML
    private TableColumn<SailesDetails,String> typeOfSaleColumn;

    @FXML
    private TableColumn<SailesDetails,String> discountColumn;

    @FXML
    private RadioButton salesWithCash;

       @FXML
    private Button loadButton;
       
       @FXML
    private Button backButton;
    @FXML
    private ToggleGroup toggle;
    @FXML
    private TextField totalSaleTextFeild;
    

    @FXML
    private RadioButton salesWithInsurance;

                 @FXML 
       public void ShowSailesWith(ActionEvent event) throws IOException, SQLException{
           
                  try {
            connect=DatabaseAPI.createConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
              try {
                  Statement stat = connection.createStatement();
            Connection conn = DatabaseAPI.createConnection();
            data3 = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            if(salesWithCash.isSelected())
            {
               ResultSet  res1 = stat.executeQuery("SELECT S.sid,S.iid,"
                    + " I.name,S.sprice,S.squanity,S.sdate,S.typeOfSale,S.discount FROM  sailes AS S,inventory as I\n"
          +"where I.iid=S.iid and S.typeOfSale='Cash'");
            while (res1.next()) 
            {
                double discount0=Double.parseDouble(res1.getString(8))*100;
                String discount = (String.valueOf(discount0)+"%");
             data3.add(new SailesDetails(String.valueOf(res1.getString(1)), String.valueOf(res1.getString(2)), String.valueOf(res1.getString(3)),String.valueOf(res1.getString(4)),String.valueOf(res1.getString(5)),String.valueOf(res1.getString(6)),String.valueOf(res1.getString(7)),discount));

             
            }   
            }
            else
            {
                               ResultSet  res1 = stat.executeQuery("SELECT S.sid,S.iid,"
                    + " I.name,S.sprice,S.squanity,S.sdate,S.typeOfSale,S.discount FROM  sailes AS S,inventory as I\n"
          +"where I.iid=S.iid and S.typeOfSale='Insurance'");
            while (res1.next()) 
            {
                double discount0=Double.parseDouble(res1.getString(8))*100;
                String discount = (String.valueOf(discount0)+"%");
             data3.add(new SailesDetails(String.valueOf(res1.getString(1)), String.valueOf(res1.getString(2)), String.valueOf(res1.getString(3)),String.valueOf(res1.getString(4)),String.valueOf(res1.getString(5)),String.valueOf(res1.getString(6)),String.valueOf(res1.getString(7)),discount));

             
            }
            }
      
         

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
        typeOfSaleColumn.setCellValueFactory(new PropertyValueFactory<>("TypeOfSale"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));

        updateTotalPrice();
       // tableVeiwSaile.setItems(null);
        tableVeiwSaile.setItems(data3);
           
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
      private void updateTotalPrice() throws SQLException{
              try {
            
           
            connect=DatabaseAPI.createConnection();
            Statement stat = connection.createStatement();
            Connection conn = DatabaseAPI.createConnection();
         
         double totalPrice=0;   
            // Execute query and store result in a resultset
            if(salesWithCash.isSelected())
            {
                           ResultSet  res1 = stat.executeQuery("SELECT I.sprice from sailes as I\n"
                    + "where I.typeOfSale='Cash'  ");
            while(res1.next())
            {
               totalPrice+= Double.parseDouble(String.valueOf(res1.getString(1)));
            }
            totalSaleTextFeild.setText(totalPrice+"");
            }
            else
            {
                                           ResultSet  res1 = stat.executeQuery("SELECT I.sprice from sailes as I\n"
                    + "where I.typeOfSale='Insurance'  ");
            while(res1.next())
            {
               totalPrice+= Double.parseDouble(String.valueOf(res1.getString(1)));
            }
            totalSaleTextFeild.setText(totalPrice+"");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            

              
  }
    
    
}

