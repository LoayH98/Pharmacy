/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MainFacePharmacistController implements Initializable {

     @FXML
    private Button logout;
    
    Stage stage;
    Parent root;
    Scene scene;
    
    
       @FXML
   private void logout(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
  
           root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                              
                               scene =new Scene(root);
                               stage.setScene(scene);
                               stage.setResizable(false);
                               stage.show();   
                               
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
