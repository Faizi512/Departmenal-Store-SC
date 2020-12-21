/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Modal.RegisterUser.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.*;

/**
 * FXML Controller class
 *
 * @author Abdul Muazzam
 */
public class LoginController implements Initializable {
public static User user = null;
    /**
     * Initializes the controller class.
     */
    
       @FXML TextField username;
       @FXML TextField password;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          String[] opt = {"Faizan","moazzam","maheem","maryam","bano","nasir","bilal","imran"}; 
          TextFields.bindAutoCompletion(username, opt);
    //    password =  TextFields.createClearableTextField();
    }    
    
   
    

    
    
       @FXML
    public void Reset(ActionEvent e) throws IOException{
        Parent fPassord = FXMLLoader.load(getClass().getResource("/GUI/ForgetPassword/forgetPassword.fxml"));
        Scene FPassword = new Scene(fPassord);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Forget Password");
        window.setScene(FPassword);
        window.show();
    }
    
       @FXML
       public void Login(ActionEvent e) throws IOException{
           if (authentication()) {
               Parent mainscene = FXMLLoader.load(getClass().getResource("/GUI/MainScene/MainScene.fxml"));
               Scene MainScene = new Scene(mainscene);
               Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
               window.resizableProperty().setValue(Boolean.FALSE);
               window.setTitle("Main Scene");
               window.setScene(MainScene);
               window.show();
           }else{
               System.out.println("Incorrect User name or Password !!!");
           }
  }
       
    public boolean authentication(){
        return true;
//        ArrayList<User> u = Modal.RegisterUser.User.getAllUsersRecord();
//        for(User element: u){
//            System.out.println("-------------"+element.getName()+element.getPassword()+"-------------");
//        }
//        for (int i = 0; i < u.size(); i++) {
//            if (username.getText().equals(u.get(i).getUser_Name()) && password.getText().equals(u.get(i).getPassword())) {
//                user = u.get(i);
//                break;
//            }
//        }
//        if(user != null){
//            return true;
//        }
//        return false;
    }      
}