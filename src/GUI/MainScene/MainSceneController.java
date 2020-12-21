/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.MainScene;

import GUI.Login.LoginController;
import static GUI.Login.LoginController.user;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdul Muazzam
 */
public class MainSceneController implements Initializable {

    @FXML
    private Button chartofaccounts;
    @FXML
    private Button inventory;
    @FXML
    private Button salesinvoice;
    @FXML
    private Button purchaseinvoice;
    @FXML
    private Button reports;
    @FXML
    private Button user;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        if(LoginController.user.getDesignation().equals("SALESPERSON")){
//            chartofaccounts.setDisable(true);
//            inventory.setDisable(true);
//            purchaseinvoice.setDisable(true);
//            reports.setDisable(true);
//            user.setDisable(true);
//        }
        
        
        
    }    
    
    
    
    @FXML
    public void getChartofAccounts(ActionEvent e) throws IOException{
        
         Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/ChartOfAccounts/ChartOfAccounts.fxml"));
            Scene MainScene = new Scene(mainscene);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Chart of Accounts");
        window.setScene(MainScene);
        window.show();
        
    }
    
       @FXML
    public void getInventoryPage(ActionEvent e) throws IOException{
        
         Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/InventoryUpdate/ProductInventoryUpdate.fxml"));
            Scene MainScene = new Scene(mainscene);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Inventory");
        window.setScene(MainScene);
        window.show();
        
    }
    
          @FXML
    public void getSalesInvoicePage(ActionEvent e) throws IOException{
        
        Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/SalesInvoice/SalesInvoice.fxml"));
            Scene MainScene = new Scene(mainscene);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Repots");
        window.setScene(MainScene);
        window.show();
        
    }
    
          @FXML
    public void getPurchaseInvoicePage(ActionEvent e) throws IOException{
        
         Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/PurchaseInvoice/PurchaseInvoice.fxml"));
            Scene MainScene = new Scene(mainscene);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Purchase Invoice");
        window.setScene(MainScene);
        window.show();
        
    }
    
          @FXML
    public void getRepotsPage(ActionEvent e) throws IOException{
        
         Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/Reporting/Reports.fxml"));
            Scene MainScene = new Scene(mainscene);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Repots");
        window.setScene(MainScene);
        window.show();
        
    }
    
    
          @FXML
    public void getUserAccessPage(ActionEvent e) throws IOException{
        
         Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/AddUsers/User.fxml"));
            Scene MainScene = new Scene(mainscene);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Login Portal");
        window.setScene(MainScene);
        window.show();
        
    }
    
         @FXML
    public void getLoginPage(ActionEvent e) throws IOException{
        
         Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
            Scene MainScene = new Scene(mainscene);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Login Portal");
        window.setScene(MainScene);
        window.show();
        
    }
    
    
}
