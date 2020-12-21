/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Capcha;

import java.io.IOException;
import java.net.URL;
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
public class ECapchaController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML TextField eCapcha;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void newPassword(ActionEvent e) throws IOException{
        if(eCapcha.getText().matches("AB6DE2")){
            Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/NewPassword/NewPassword.fxml"));
            Scene MainScene = new Scene(mainscene);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setTitle("Reset Password");
            window.setScene(MainScene);
            window.show();
        }
    }
}
