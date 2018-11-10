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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author user*/


public class SalesController implements Initializable {
    Stage stage2;
    Parent root2;
    Scene scene2;
    
private Connection connect; 
private PreparedStatement preparedStatement ;

               @FXML
    private TableView<SailesDetails> tableVeiwSaile;

    @FXML
    private TableColumn<SailesDetails, String> saleIDColumn;

    @FXML
    private TableColumn<SailesDetails, String> inventoryIDColumn;

    @FXML
    private TableColumn<SailesDetails, String> inventoryNameColumn;

    @FXML
    private TableColumn<SailesDetails, String> priceColumn;

    @FXML
    private TableColumn<SailesDetails, String> quantityCloumn;

    @FXML
    private TableColumn<SailesDetails, String> dateColumn;
    
    @FXML
    private TableColumn<SailesDetails, String> typeOfSaleColumn;

    @FXML
    private TableColumn<SailesDetails, String> discountColumn;
    
     @FXML
    private Button sailesOfInventoryButton;

    @FXML
    private TextField discountTextFeild;

    @FXML
    private TextField QuantityTextFeild;
   
      @FXML
    private TextField saleID;

    @FXML
    private TextField totalSaleTextFeild;

    @FXML
    private ComboBox<String> inventoryComboBox;

    @FXML
    private RadioButton cashRadioButton;

    @FXML
    private ToggleGroup typeOfSale;

    @FXML
    private RadioButton insuranceRadioButton;

    @FXML
    private Button addSaleButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button maxSailesButton;

    @FXML
    private Button sailesInDayButton;

    @FXML
    private Button sailesInMonthButton;

    @FXML
    private Button showWithButton;
    
    @FXML
    private Button backToMainManger;
   
    private ObservableList<SailesDetails>data3;
   private ObservableList<String>inventoryData=FXCollections.observableArrayList();


    @FXML
    void goToShowWithType(ActionEvent event) throws IOException {

                         stage2 = (Stage)showWithButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("ShowSalesWith.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
  
    }
    
       @FXML
  void goTofindSaleInADay (ActionEvent e) throws SQLException, IOException {  
      
                 stage2 = (Stage)sailesInDayButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("SalesInADay.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
  
  }
  @FXML
  void goTofindInventorySales (ActionEvent e) throws SQLException, IOException {  
      
                 stage2 = (Stage)sailesOfInventoryButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("findSailesOfInventory.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
  
  } 
     @FXML 
       public void addSale(ActionEvent event) throws IOException, SQLException{
           
           connect=DatabaseAPI.createConnection();
           
		try {

      
	
			if (  inventoryComboBox.getValue()==null ||QuantityTextFeild.getText()==null ) {
				throw new Exception();
			} 
                                               Statement stat2 = connection.createStatement();
                                Connection conn2 = DatabaseAPI.createConnection();
                                  String s="";
                                  int i=0;
                        int j=0;
                        while(inventoryComboBox.getValue().charAt(i)!=' ')
                                                   i++;
                        i--;
                        while(i>=0){
                           s=s+inventoryComboBox.getValue().charAt(j);
                           j++;
                           i--;
                        }
            // Execute query and store result in a resultset
          ResultSet  res12 = stat2.executeQuery("SELECT I.quantity"
                    + " FROM  inventory as I \n"
          +"where I.iid= "+"'"+Integer.parseInt(s)+"'");
          
          String qq=QuantityTextFeild.getText();
          if(res12.next())
          {
              if(Integer.parseInt(String.valueOf(res12.getString(1)))-(Integer.parseInt(qq))<0 )
              {
                  ShowMessage("Quantity of Inventory of iid "+s+"will be lower than 0 .. Not Accepted ");
                  throw new Exception();
              }
              
          }
                        double discount;
                        int inventoryID;
                        String typeOfSale;
                      //  int i=0;
                      

                         System.out.println("1");

                            
                        inventoryID=Integer.parseInt(inventoryComboBox.getValue().charAt(0)+"");
                        System.out.println("2");
           Statement stat = connection.createStatement();
            Connection conn = DatabaseAPI.createConnection();
            
            // Execute query and store result in a resultset
          ResultSet  res1 = stat.executeQuery("SELECT I.price"
                    + "  FROM inventory I\n"
          +"where I.iid ="+"'"+Integer.parseInt(s)+"'");
          System.out.println("3");
          double price =0;
          if(res1.next())
              price=Double.parseDouble(String.valueOf(res1.getString(1)));
          System.out.println("4");
        String discount0 =discountTextFeild.getText();
        String discount1="";
        for(i=0;discount0.charAt(i)!='%';i++)
            discount1=discount1+discount0.charAt(i);
        discount=Double.parseDouble(discount1)/100;
         price = (price-price*discount)*Double.parseDouble(QuantityTextFeild.getText());
         String quantity=QuantityTextFeild.getText();
         System.out.println("5");
        
                        if(cashRadioButton.isSelected())
                            typeOfSale="Cash";
                        else
                            typeOfSale="Insurance";
                        
                       System.out.println("6");
				DatabaseAPI.insertSale(s,price+"",quantity,typeOfSale,discount+"");
          
		 			 
					 

				showSuccess("Adding a new Sale was done successfully");
			//}
		} catch (Exception e3) {
			System.out.println(e3.getMessage()); 
		}
                Load();
                updateTotalPrice();
	}
                 private void ShowMessage(String s) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(s);

		alert.showAndWait();   
                
	}

	private void showSuccess(String s) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(s);

		alert.showAndWait();
	}

	private void showError() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setTitle("Error !!");
		alert.setHeaderText("Input Error");
		alert.setContentText("Make sure you fill all the inputs properly");

		alert.showAndWait();
	}
           @FXML 
       public void showSales(ActionEvent event) throws IOException, SQLException{
           
       }
  private void Load(){
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
          ResultSet  res1 = stat.executeQuery("SELECT S.sid,S.iid,"
                    + " I.name,S.sprice,S.squanity,S.sdate,S.typeOfSale,S.discount FROM  sailes AS S,inventory as I\n"
          +"where I.iid=S.iid");
            while (res1.next()) 
            {
                double discount0=Double.parseDouble(res1.getString(8))*100;
                String discount = (String.valueOf(discount0)+"%");
             data3.add(new SailesDetails(String.valueOf(res1.getString(1)), String.valueOf(res1.getString(2)), String.valueOf(res1.getString(3)),String.valueOf(res1.getString(4)),String.valueOf(res1.getString(5)),String.valueOf(res1.getString(6)),String.valueOf(res1.getString(7)),discount));

             
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

        
       // tableVeiwSaile.setItems(null);
        tableVeiwSaile.setItems(data3);
    }    
  
  
  private void addToComboBox() throws SQLException{
      connect=DatabaseAPI.createConnection();
             Statement stat = connection.createStatement();
            Connection conn = DatabaseAPI.createConnection();
            data3 = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
          ResultSet  res1 = stat.executeQuery("SELECT I.iid,I.name from inventory as I ");
          while(res1.next())
          {
              inventoryData.add(String.valueOf(res1.getString(1))+" "+String.valueOf(res1.getString(2)));
          }
          inventoryComboBox.setItems(inventoryData);
   
  }    
  private void updateTotalPrice() throws SQLException{
              try {
            
           
            connect=DatabaseAPI.createConnection();
            Statement stat = connection.createStatement();
            Connection conn = DatabaseAPI.createConnection();
         
         double totalPrice=0;   
            // Execute query and store result in a resultset
            ResultSet  res1 = stat.executeQuery("SELECT I.sprice from sailes as I ");
            while(res1.next())
            {
               totalPrice+= Double.parseDouble(String.valueOf(res1.getString(1)));
            }
            totalSaleTextFeild.setText(totalPrice+"");
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
     @FXML 
       public void MaxSales(ActionEvent event) throws IOException, SQLException{
                       connect=DatabaseAPI.createConnection();
            Statement stat = connection.createStatement();
            Connection conn = DatabaseAPI.createConnection();
            ResultSet  res1 = stat.executeQuery("SELECT I.iid ,I.name from  inventory as I \n "
                    + "WHERE I.totalSales IN"
                    + " ( SELECT MAX(totalSales) from inventory)");
            
             if(res1.next())
            {
               showResult("The inventory "+String.valueOf(res1.getString(2))+" With ID "+String.valueOf(res1.getString(1))+" Have The max total sails");
            }
       }
             	private void showResult(String s) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setHeaderText(s);

		alert.showAndWait();
	

       }

            @FXML   
 	void removeSale(ActionEvent e) throws SQLException {  
       try{     
        connect=DatabaseAPI.createConnection();
                      
                   Statement statement = connection.createStatement();
		statement.execute("delete from sailes where sid = " +Integer.parseInt(saleID.getText()) );
               
	 Statement statement2 = connection.createStatement();
               Statement statement3 = connection.createStatement();
                      ResultSet  res2 = statement3.executeQuery("SELECT I.iid ,S.squanity from  inventory as I ,sailes as S\n "
                    + "WHERE S.sid="+"'"+ Integer.parseInt(saleID.getText())+"'"+"and  S.iid = I.iid ");
                 
         if(res2.next())        
         statement2.executeUpdate("UPDATE inventory set quantity=quantity+"+"'"+Integer.parseInt(String.valueOf(res2.getString(2)))+"'" +"where iid="+" '"+Integer.parseInt(String.valueOf(res2.getString(1)))+"'");
             
        showSuccess("Delete a Record Sale with id "+saleID.getText()+" was done successfully");
        Load();
      } catch (Exception e3) {
	//showErrorDelete();
	System.out.println(e3.getMessage());
      }
        
        }
                	private void showErrorDelete() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setTitle("Error !!");
		alert.setHeaderText("Input Error");
		alert.setContentText("ID is not found ");

		alert.showAndWait();
	}
                        
         @FXML   
 	void backToMain(ActionEvent e) throws SQLException, IOException {  
           
                           stage2 = (Stage)backToMainManger.getScene().getWindow();
             if (FXMLDocumentController.log==0)
                               root2=FXMLLoader.load(getClass().getResource("mainFaceForManeger.fxml"));
             else
                 root2=FXMLLoader.load(getClass().getResource("mainFacePharmacist.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
                
            }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
       
            
            Load();
            try {
                addToComboBox();
            } catch (SQLException ex) {
                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
            updateTotalPrice();
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

}
}