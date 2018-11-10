/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;



//////////////////////////////////////////////////////////

//                 Login and LogOut   imports       //
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




/////////////////////////////////////////////////////////////////////
//       Pharmacists    imports   //
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import javax.swing.table.DefaultTableModel;
import static pharmacy.DatabaseAPI.connection;






/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {

    public FXMLDocumentController() {
    }
    
    


    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Label errorLabel;

                     @FXML
    private Button sailesGOTOButton;  

     @FXML
   
    private Button logoutM;
    @FXML
     private Button logoutP;
    
        
        
  /////////////////////////////////////////////////////////////////////////
//          Pharmacisits             \\

            @FXML
    private Button refreshButton;


    @FXML
    private TableView<PharmacistsDetails> tableVeiwPH;
    @FXML
    private Button PharmacistsButton;

        @FXML
    private Button addPharmacistButton;
                @FXML
    private Button UpdatePHButton;
            @FXML
    private Button deleteGOTOButton;
     

        @FXML
    private TableColumn<PharmacistsDetails,String> idColumn;

    @FXML
    private TableColumn<PharmacistsDetails,String> nameColumn;

    @FXML
    private TableColumn<PharmacistsDetails,String> genderColumn;

    @FXML
    private TableColumn<PharmacistsDetails,String> addressColumn;

    @FXML
    private TableColumn<PharmacistsDetails,String> emailColumn;

    @FXML
    private TableColumn<PharmacistsDetails,String> phoneNumberColumn;

    @FXML
    private TableColumn<PharmacistsDetails,String> shiftTimeColumn;

    @FXML
    private TableColumn<PharmacistsDetails,String> salaryColumn;
    
    private ObservableList<PharmacistsDetails>data;
    /////////////////////////////////////////////////////////////////////
        
    Stage stage;
    Parent root;
    Scene scene;
  
     Stage stage2;
    Parent root2;
    Scene scene2;
    
         Stage stage3;
    Parent root3;
    Scene scene3;
    
private Connection connect; 
private PreparedStatement preparedStatement ;




//////////////////////////////////////////////////////////

//                 Login and LogOut        //

 
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        connect=DatabaseAPI.createConnection();
       
					String str = name.getText();
					String pass = password.getText();
                       String sql_query="SELECT * FROM pharmacist WHERE username='"+str+"' AND password ='"+pass +"';" ;                
					
                     
                          ResultSet res= connect.prepareStatement(sql_query).executeQuery();
                          
                           if(res.next())
                           {
                               stage = (Stage)password.getScene().getWindow();
                               root=FXMLLoader.load(getClass().getResource("mainFacePharmacist.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene =new Scene(root);
                               stage.setScene(scene);
                               stage.setResizable(false);
                               stage.show();
                           }
                            else {
                                       errorLabel.setText("wrong username or password");
                                 }
                               String sql_q="SELECT * FROM manegers WHERE username='"+str+"' AND password ='"+pass +"';" ;
                               ResultSet resu= connect.prepareStatement(sql_q).executeQuery();
                              
                               
                              if(resu.next())
                              {
                              stage = (Stage)name.getScene().getWindow();
                               root=FXMLLoader.load(getClass().getResource("mainFaceForManeger.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene =new Scene(root);
                               stage.setScene(scene);
                               stage.setResizable(false);
                               stage.show();  
                               
                           }
                              else {
                                       errorLabel.setText("wrong username or password");
                                 }
                              
                              
  /* boolean state =  preparedStatement.execute();
    if(state)
        System.out.println("Done");
    else
        System.out.println("Error");
    }*/
  
  
    }    
     @FXML  
   public void signout1(ActionEvent event) throws IOException, SQLException{
       
     //  stage = (Stage).getScene().getWindow();
    
                         stage2 = (Stage)logoutM.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
   }
   
@FXML
   public void signout2(ActionEvent event) throws IOException, SQLException{
       
     //  stage = (Stage).getScene().getWindow();
    
                         stage2 = (Stage)logoutP.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
   }
@FXML
    public void goToPharmacists(ActionEvent event) throws IOException, SQLException{
   
                                 stage2 = (Stage)PharmacistsButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("Pharmacists.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
        
    }
    static int log=0;
    @FXML
    public void goToSailes(ActionEvent event) throws IOException, SQLException{
                          log=0;
                               stage2 = (Stage)sailesGOTOButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("Sales.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
                              
                              
        
    }
        @FXML
    private Button goToSalesButton;
        @FXML
    public void goToSailes2(ActionEvent event) throws IOException, SQLException{
                      log=1;
                               stage2 = (Stage)goToSalesButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("Sales.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
                              
                              
        
    }
        
    
     /////////////////////////////////////////////////////////////////////////
//          Pharmacisits            \\
    
    @FXML
    private Button backToMainMangerButton;
        
        
    @FXML
   public void fill_data(ActionEvent event) throws IOException, SQLException{
    /*    try {
            connect=DatabaseAPI.createConnection();
            Statement stat = connection.createStatement();
            ResultSet res = stat.executeQuery("select count(*) from pharmacist");
            int size = 0;
            if (res.next()) {
                size = res.getInt("count(*)");
            }
            res.close();
            stat.close();

            stat = connection.createStatement();
            res = stat.executeQuery("SELECT P.personid,P.pname,"
                    + " P.gender,P.address,p.email,P.phone,S.shift ,"
                    + "S.Salary FROM pharmacist AS S, person AS P\n"
                    +
                                "WHERE S.pharmacistid = P.personid ;");
            String[] cols = {"ID","Name","Gender", "Address", "Email","Phone Number","Shift Time","Salary"};
            String[][] matrix = new String[size][8];
            int count = 0;

            while (res.next() ) {
                String pharmacist_id = res.getInt("personid") + "";      
                String Name = res.getString("pname") ;
                String Gender = res.getString("gender");

                
                String Address = res.getString("address");
                String Email = res.getString("email");

                String phoneNumber = res.getString("phone");
                String shiftTime = res.getString("shift");
                String salary = res.getDouble("salary")+"";
                
                matrix[count][0] = pharmacist_id;
                matrix[count][1] = Name;
                matrix[count][2] = Gender;
                matrix[count][3] = Address;
                matrix[count][4] = Email;
                matrix[count][5] = phoneNumber;
                matrix[count][6] = shiftTime;
                matrix[count][7] = salary;
                
                System.out.println(matrix[count][0]+" "+matrix[count][1]+" "+matrix[count][2]+" "+matrix[count][3]+" "+matrix[count][4]+" "+matrix[count][5]+" "+matrix[count][6]+" "+matrix[count][7]);
                count ++;
            }
            res.close();
            stat.close();
         DefaultTableModel model = new DefaultTableModel(matrix, cols);
        //    tableVeiwPH.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
*/
       connect=DatabaseAPI.createConnection();
              try {
                  Statement stat = connection.createStatement();
            Connection conn = DatabaseAPI.createConnection();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
          ResultSet  res = stat.executeQuery("SELECT P.personid,P.pname,"
                    + " P.gender,P.address,p.email,P.phone,S.shift ,"
                    + "S.Salary FROM pharmacist AS S, person AS P\n"
                    +
                                "WHERE S.pharmacistid = P.personid ;");
            
            while (res.next()) 
               data.add(new PharmacistsDetails(String.valueOf(res.getString(1)), String.valueOf(res.getString(2)), String.valueOf(res.getString(3)),String.valueOf(res.getString(4)),String.valueOf(res.getString(5)),String.valueOf(res.getString(6)),String.valueOf(res.getString(7)),String.valueOf(res.getString(8))));
            

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
           
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        shiftTimeColumn.setCellValueFactory(new PropertyValueFactory<>("ShiftTime"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        tableVeiwPH.setItems(null);
        tableVeiwPH.setItems(data);
       
       
    }
            @FXML   
 	void backToMainOfManeger(ActionEvent e) throws SQLException, IOException {     
                           stage2 = (Stage)backToMainMangerButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("mainFaceForManeger.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
                
            }
   
    @FXML
       public void goToaddPharmacists(ActionEvent event) throws IOException, SQLException{
   
                                 stage2 = (Stage)UpdatePHButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("ADDPharmacists.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();


       }

    

   @FXML
          public void goToRemovePharmacists(ActionEvent event) throws IOException, SQLException{
              
               stage3 = (Stage)deleteGOTOButton.getScene().getWindow();
                               root3=FXMLLoader.load(getClass().getResource("deletePharmacist.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene3 =new Scene(root3);
                               stage3.setScene(scene3);
                               stage3.setResizable(false);
                               stage3.show();
        
          
          }
          
  
   @FXML
          public void goToUpdatePharmacists(ActionEvent event) throws IOException, SQLException{
              
               stage2 = (Stage)deleteGOTOButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("updatePharmacist.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
        
          
          }        

//////////////////////////////////////////////////////////////////////
       //              Add Pharmacist      //
       
  /*      @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;*/
@FXML
    private TextField nPH;
@FXML    
private TextField phID;
@FXML    
    private TextField AddrPH;
@FXML
    private TextField emPH;
@FXML
    private TextField PhonePH;
@FXML
    private TextField UserPH;
@FXML
    private PasswordField passPH;

@FXML
    private TextField SihftPH;
@FXML
    private TextField SalaryPH;

@FXML
    private RadioButton maleBtn;  
    
   
       
     @FXML 
       public void addParmacistToDataBase(ActionEvent event) throws IOException, SQLException{
           
           connect=DatabaseAPI.createConnection();
           
		try {
			if (  nPH.getText().equals("") || phID.getText().equals("") ||AddrPH.getText().equals("")
					|| emPH.getText().equals("") || PhonePH.getText().equals("")
					|| UserPH.getText().equals("") || passPH.getText().equals("")
					||  SihftPH.getText().equals("")
					|| SalaryPH.getText().equals("") ) {
				throw new Exception();
			} 
                        String gender;
                        if(maleBtn.isSelected())
                            gender="Male";
                        else
                            gender="Female";
				DatabaseAPI.insert1( phID.getText(),nPH.getText() ,gender, AddrPH.getText(),
					 emPH.getText(),PhonePH.getText());
                                DatabaseAPI.insert2( phID.getText(), UserPH.getText(), passPH.getText(),
					  SihftPH.getText(),SalaryPH.getText());
					 
					 

				showSuccess("Adding a new employee was done successfully");
			//}
		} catch (Exception e3) {
			showError();
		}
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
        
        
////////////////////////////////////////////////////////////////////
        //       update pharmacist    //

@FXML
    private TextField nPH2;
@FXML
    private TextField AddrPH2;
@FXML
    private TextField emPH2;
@FXML
    private TextField PhonePH2;
@FXML
    private TextField UserPH2;
@FXML
    private PasswordField passPH2;
@FXML
    private TextField SihftPH2;
@FXML
    private TextField SalaryPH2;
@FXML

    private RadioButton maleBtn2;
@FXML
    private RadioButton femaleBtn2;
@FXML
private TextField phID2;

    @FXML    
    void findPharmacist(ActionEvent e) throws SQLException {
     
          
            
        //    DatabaseAPI.find(phID2.getText());
         try {
            connect=DatabaseAPI.createConnection();
            Statement stat = connection.createStatement();
            ResultSet res = stat.executeQuery("select * from pharmacist");
            int size = 0;

            res.close();
            stat.close();
            int id =Integer.parseInt(phID2.getText());
            
            stat = connection.createStatement();
            res = stat.executeQuery("SELECT P.personid,P.pname,"
                    + " P.gender,P.address,p.email,P.phone,S.shift ,"
                    + "S.Salary,S.username,S.password FROM pharmacist AS S, person AS P\n"
                    +"WHERE P.personid="+id+" and S.pharmacistid = P.personid ;");
                                
           // String[] cols = {"ID","Name","Gender", "Address", "Email","Phone Number","Shift Time","Salary"};
           // String[][] matrix = new String[size][8];
            

            if(res.next() ) {
                String pharmacist_id = res.getInt("personid") + "";      
                String Name = res.getString("pname") ;
                String Gender = res.getString("gender");

                
                String Address = res.getString("address");
                String Email = res.getString("email");

                String phoneNumber = res.getString("phone");
                String shiftTime = res.getString("shift");
                String salary = res.getDouble("salary")+"";
                String password = res.getString("password");
                String userName = res.getString("username");
                
                phID2.setText(pharmacist_id) ;
                nPH2.setText(Name);
                if(Gender.equals("Male")||Gender.equals("m")||Gender.equals("M"))
                    maleBtn2.setSelected(true);
                else
                    femaleBtn2.setSelected(true);
                
                AddrPH2.setText(Address);
                emPH2.setText(Email);
                PhonePH2.setText(phoneNumber);
                SihftPH2.setText(shiftTime);
                SalaryPH2.setText(salary);
                passPH2.setText(password);
                UserPH2.setText(userName);
                
            }

            res.close();
            stat.close();

       //  DefaultTableModel model = new DefaultTableModel(matrix, cols);
        //    tableVeiwPH.setModel(model);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        
        }
    @FXML
	void updateEmployee(ActionEvent e) throws SQLException {
            connect=DatabaseAPI.createConnection();
		try {
                    String gender="";
                        if(maleBtn2.isSelected())
                           gender ="Male";
                        else
                           gender="Female";
                        System.out.println("1");
			DatabaseAPI.update1(phID2.getText(),nPH2.getText() ,gender, AddrPH2.getText(),
					 emPH2.getText(),PhonePH2.getText());
                        System.out.println("2");
                        DatabaseAPI.update2( phID2.getText(), UserPH2.getText(), passPH2.getText(),
					  SihftPH2.getText(),SalaryPH2.getText());
                        System.out.println("3");
			showSuccess("Updating the information of employee " + phID2.getText() + " was done successfully");
		} catch (Exception e3) {
			System.out.println(e3.getMessage());
		}
	}
        
        /////////////////////////////////////////////////////////////////////////
        
        ////////// delete pharmacist          //////////
    private TextField deletePHText;
        
        
     @FXML   
 	void removeEmployee(ActionEvent e) throws SQLException {  
       try{     
        connect=DatabaseAPI.createConnection();
        DatabaseAPI.delete(deletePHText.getText());
        showSuccess("Delete a Pharmacist with id"+deletePHText.getText()+"was done successfully");
      } catch (Exception e3) {
	showErrorDelete();
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

/*	@FXML
	void deleteEmployee(ActionEvent e) throws SQLException {
		try {
			DatabaseAPI.delete(eidT.getText());
			showSuccess("Employee " + eidT.getText() + " was deleted successfully");
		} catch (Exception e3) {
			showError();
		}
	}
*/
                @FXML
	void clearFields(ActionEvent e) {
	 phID.setText("");
         nPH.setText("");
        maleBtn.setSelected(true);
        AddrPH.setText("");
	emPH.setText("");
        PhonePH.setText("");
        phID.setText("");
        UserPH.setText("");
        passPH.setText("");
        SihftPH.setText("");
        SalaryPH.setText("");
	
        }
@FXML
private Button backButtoninUpdate ;
        @FXML
            void goBackToPharmacistMenuFromUpdate(ActionEvent event) throws IOException {

                         stage2 = (Stage)backButtoninUpdate.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("Pharmacists.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
  
    }
            
@FXML
private Button backButtoninAdd ;
        @FXML
            void goBackToPharmacistMenuFromAdd(ActionEvent event) throws IOException {

                         stage2 = (Stage)backButtoninAdd.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("Pharmacists.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
  
    }
            @FXML
private Button backButtoninRemove ;
        @FXML
            void goBackToPharmacistMenuFromRemove(ActionEvent event) throws IOException {

                         stage2 = (Stage)backButtoninRemove.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("Pharmacists.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
  
    }
            @FXML
            private Button backButtoninPharmacists ;
        @FXML
            void goBackToPharmacistMenuFromPharmacists(ActionEvent event) throws IOException {

                         stage2 = (Stage)backButtoninPharmacists.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("mainFaceForManeger.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
  
    }
  ////////////////////////////////////////////////////////////////////////////
        //////               Sales         \\\\\\
 
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

}