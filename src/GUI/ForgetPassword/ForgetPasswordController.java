/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ForgetPassword;

import static GUI.Login.LoginController.user;
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

/**
 * FXML Controller class
 *
 * @author Faizan Haider
 */
public class ForgetPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TextField username;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void verify(ActionEvent e) throws IOException{
        if(username.getText().matches("moazzam")){
            Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/Capcha/eCapcha.fxml"));
            Scene MainScene = new Scene(mainscene);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setTitle("Password Reset");
            window.setScene(MainScene);
            window.show();
        }
    }
    public boolean authentication(){
        ArrayList<User> u = Modal.RegisterUser.User.getAllUsersRecord();
        for (int i = 0; i < u.size(); i++) {
            if (username.getText().equals(u.get(i).getUser_Name())) {
                user = u.get(i);
                break;
            }
        }
        if(username != null ){
            return true;
        }

        return false;
    }
}
