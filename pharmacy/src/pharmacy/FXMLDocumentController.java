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
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import static pharmacy.DatabaseAPI.connection;






/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {

    public FXMLDocumentController() {
    }
    
    //////////////////////////////////////////////////////////

//                 Login and LogOut        //
    
     @FXML
    private Label label;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Label welcomeLabel;
     @FXML
    private Label errorLabel;
     
    @FXML
    private Button logoutM;
       @FXML
    private Button logoutP;
     /*@FXML
    private Button logout2;
       */     
        @FXML
    private Button insertNewInventoryButton;    
    
        
        
  /////////////////////////////////////////////////////////////////////////
//          Pharmacisits             \\


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Pharmacy> tableVeiwPH;
    
        @FXML
    private Button PharmacistsButton;

        @FXML
    private Button addPharmacistButton;

    
    /////////////////////////////////////////////////////////////////////
        
    Stage stage;
    Parent root;
    Scene scene;
  
     Stage stage2;
    Parent root2;
    Scene scene2;
    
private Connection connect; 
private PreparedStatement preparedStatement ;




//////////////////////////////////////////////////////////

//                 Login and LogOut        //


    @FXML
    private void insertNewInventor(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        connect=DatabaseAPI.createConnection();
        System.out.println("a7a");
    } 
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
                                           stage = (Stage)password.getScene().getWindow();
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

    public void goToPharmacists(ActionEvent event) throws IOException, SQLException{
   
                                 stage2 = (Stage)PharmacistsButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("Pharmacists.fxml"));
                              // welcomeLabel.setText("Welcome "+str);
                               scene2 =new Scene(root2);
                               stage2.setScene(scene2);
                               stage2.setResizable(false);
                               stage2.show();
        
    }
     /////////////////////////////////////////////////////////////////////////
//          Pharmacisits            \\
  
    
       public void goToADDPharmacists(ActionEvent event) throws IOException, SQLException{
   
                                 stage2 = (Stage)addPharmacistButton.getScene().getWindow();
                               root2=FXMLLoader.load(getClass().getResource("addPharmacist.fxml"));
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
