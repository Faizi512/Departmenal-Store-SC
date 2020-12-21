/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.AddUsers;

import Modal.Enums.Designation;
import Modal.RegisterUser.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdul Muazzam
 */
public class UserController implements Initializable {

    @FXML
    private Label id;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXComboBox<String> designation;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXListView<String> List;

 
    public int NextUserID; 
    
    
    /**
     * Initializes the controller class.
     */
    ArrayList<String> Users = Modal.RegisterUser.User.getAllUsers();
        ObservableList<String> LIST = FXCollections.observableArrayList(Users);
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField posID;
    
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        Designation[] d = Designation.values();String[] strLevel = new String[d.length];
        for (int i = 0; i < d.length; i++) {
            strLevel[i] = d[i].toString();
            
        }
        ObservableList<String> l =  FXCollections.observableArrayList(strLevel);
        designation.setItems(l);
        NextUserID = Modal.RegisterUser.User.getUserRegisteredID()+1;
        id.setText(Integer.toString(NextUserID));
        
        List.setItems(LIST);
        
        
         List.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                String[] t = List.getSelectionModel().getSelectedItem().split(" ... ");
                User u = Modal.RegisterUser.User.getSingleUserRecord(Integer.parseInt(t[0]));
                name.setText(u.getName());email.setText(u.getEmail());phone.setText(u.getPhone_No());address.setText(u.getAddress());city.setText(u.getCity());
                designation.setValue(u.getDesignation());
               id.setText(Integer.toString(u.getID()));
               username.setText(u.getUser_Name());
               password.setText(u.getPassword());
               posID.setText(u.getPOS_ID());
            }
        });
        
        
        
    }  
    
    
    
    
    
    
       public void clear(){
        name.setText("");email.setText("");phone.setText("");address.setText("");city.setText("");
        designation.setValue("Select Designation");posID.setText("");username.setText("");
        password.setText("");
    }
    
    @FXML
     public void Save(ActionEvent e){
         
          User u = new User();
         u.setName(name.getText());
         u.setPhone_No(phone.getText());
         u.setEmail(email.getText());
         u.setAddress(address.getText());
         u.setCity(city.getText());
         u.setDesignation(designation.getValue());
         u.setPOS_ID(posID.getText());
         u.setUser_Name(username.getText());
         u.setPassword(password.getText());
         
        if(Modal.RegisterUser.User.duplicationCheck(Integer.parseInt(id.getText()))){
           
           
            u.setID(Integer.parseInt(id.getText()));
             RemoveItemFromList(u.getID());
         Modal.RegisterUser.User.Update(u);
         LIST.add(id.getText() +" ... "+ name.getText() +" ... "+ designation.getValue());
         clear();
         id.setText(Integer.toString(NextUserID));
       
        }else{
        
         Modal.RegisterUser.User.Save(u);
         LIST.add(id.getText() +" ... "+ name.getText() +" ... "+ designation.getValue());
         clear();
         ++NextUserID;
         id.setText(Integer.toString(NextUserID));
        }
       
         
         
         
      
         
     }
     
    @FXML
     public void Delete(ActionEvent e){
         System.out.println(List.getSelectionModel().getSelectedItem());
        String[] ListSeletecItem = List.getSelectionModel().getSelectedItem().split(" ... ");
        int id = Integer.parseInt(ListSeletecItem[0]);
        User u = Modal.RegisterUser.User.getSingleUserRecord(id);
Hibernate.DB_Connection.getSession().delete(u);
     Hibernate.DB_Connection.endSession();
  List.getItems().remove(List.getSelectionModel().getSelectedIndex());
     }
    
    
        @FXML
     public void Back(ActionEvent e) throws IOException{
          Parent  mainscene = FXMLLoader.load(getClass().getResource("/GUI/MainScene/MainScene.fxml"));
            Scene MainScene = new Scene(mainscene);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Main Scene");
        window.setScene(MainScene);
        window.show();
    }
     
     
     public void RemoveItemFromList(Integer u){
         for (int i = 0; i < LIST.size(); i++) {
             String[] t = LIST.get(i).split(" ... ");
              Integer in = Integer.parseInt(t[0]);
             if(u == in){
                 LIST.remove(i);
             }
             
         }
     }
    
}
