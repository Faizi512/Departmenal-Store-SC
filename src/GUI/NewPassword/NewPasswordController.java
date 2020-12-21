/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.NewPassword;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Faizan Haider
 */
public class NewPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TextField p1;
    @FXML TextField p2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void Login(ActionEvent e) throws IOException, Exception{
        Parent  root = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
        Scene MainScene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(MainScene);
        window.show();
    }
    public void Save(ActionEvent e) throws IOException, Exception{
        if(p1.getText().matches(p2.getText())){
            Parent  root = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
            Scene MainScene = new Scene(root);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(MainScene);
            window.show();
            //password db mai save krana ha yha/////
        }
    }
}