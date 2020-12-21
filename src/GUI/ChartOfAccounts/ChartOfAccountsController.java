/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ChartOfAccounts;

import Modal.Chart_of_Accounts.Level_1;
import Modal.Chart_of_Accounts.Level_2;
import Modal.Chart_of_Accounts.Level_3;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdul Muazzam
 */
public class ChartOfAccountsController implements Initializable {

    @FXML  private TextField l1c1;
    @FXML  private TextField l1n1;
    @FXML  private TextField l2c2;
    @FXML  private TextField l2n2;
    @FXML  private ComboBox<String> l2n1;
    @FXML  private ComboBox<String> l3n1;
    @FXML  private ComboBox<String> l3n2;
    @FXML  private TextField l3c3;
    @FXML  private TextField l3n3;
    @FXML
    private ListView<String> list1;
    @FXML
    private ListView<String> list2;
    @FXML
    private ListView<String> list3;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        l2n1.setItems(FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_1.getL1List()));
        l3n1.setItems(FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_1.getL1List()));
        l3n2.setItems(FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_2.getL2ComboList()));        
        
        list1.setItems(FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_1.getL1List()));
        list2.setItems(FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_2.getL2ComboList()));
        list3.setItems(FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_3.getL3ComboList()));
 

        list1.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                String[] t = list1.getSelectionModel().getSelectedItem().split(" ... ");
                l1c1.setText(t[0]);
                l1n1.setText(t[1]);
             //   System.out.println(list1.getSelectionModel().getSelectedItem());
                                                    
            }
        });
        list2.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                String[] t = list2.getSelectionModel().getSelectedItem().split(" ... ");
                String[] t1 = t[0].split("-");
               Level_2 level2 = new Level_2().getL2Record(Integer.parseInt(t1[1]));
                l2c2.setText(t1[1]);
                l2n2.setText(t[1]);
                l2n1.setValue(Integer.toString(level2.getCode1())+" ... "+level2.getName1());
                      
                         
            }
        });
        list3.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                 String[] t = list3.getSelectionModel().getSelectedItem().split(" ... ");
                String[] t1 = t[0].split("-");
               Level_3 level3 = new Level_3().getL3Record(Integer.parseInt(t1[2]));
                l3c3.setText(Integer.toString(level3.getCode3()));
                l3n3.setText(t[1]);
                l3n1.setValue(Integer.toString(level3.getCode1())+" ... "+level3.getName1());
                  l3n2.setValue(Integer.toString(level3.getCode2())+" ... "+level3.getName2());
              //  System.out.println(list3.getSelectionModel().getSelectedItem());
                                                    
            }
        });
        
        
    }    
    

       
          
    
    
    @FXML
    public void Save1(ActionEvent e){
        if(!(l1c1.getText().isEmpty() && l1n1.getText().isEmpty())){
                    try {
                        if(!l1c1.getText().isEmpty() && Integer.parseInt(l1c1.getText()) > 0){
                            if(  new Level_1().checkDuplicates(Integer.parseInt(l1c1.getText()))){
                                new Level_1().Update(new Level_1(Integer.parseInt(l1c1.getText()),l1n1.getText()));
                                l2n1.getItems().add(l1c1.getText() + " ... " + l1n1.getText());
        list1.getItems().add(l1c1.getText() + " ... " + l1n1.getText());
        clearl1();
                             //   System.out.println("Duplicate entries are not allowed !!!");
                            }else{
                                
                                 Hibernate.DB_Connection.getSession().save(new Level_1(Integer.parseInt(l1c1.getText()),l1n1.getText()));
        Hibernate.DB_Connection.endSession();
        l2n1.getItems().add(l1c1.getText() + " ... " + l1n1.getText());
        l3n1.getItems().add(l1c1.getText() + " ... " + l1n1.getText());
        list1.getItems().add(l1c1.getText() + " ... " + l1n1.getText());
      clearl1();
                            }
                           
                        }else{
                             System.out.println("Invalid Input !!!");
                        }
              
        } catch (Exception ex) {
            System.out.println(ex);
        }
        }else{
        System.out.println("Invalid Input !!!");
           
        }
    }
    @FXML
    public void Save2(ActionEvent e){
        try {
            if(  new Level_2().checkDuplicates(Integer.parseInt(l2c2.getText()))){
                                System.out.println("Duplicate entries are not allowed !!!");
                            }else{
                 String[] temp = l2n1.getValue().split(" ... ");
                            Hibernate.DB_Connection.getSession().save(new Level_2(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(l2c2.getText()),l2n2.getText()));
        Hibernate.DB_Connection.endSession();
        l3n2.getItems().add(temp[0] +"-"+ l2c2.getText() + " ... " + l2n2.getText());
       list2.getItems().add(temp[0] +"-"+l2c2.getText() + " ... " + l2n2.getText());
         clearl2();
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
       
    
       
    }
    @FXML
    public void Save3(ActionEvent e){
         try {
//               if(  new Level_3().checkDuplicates(Integer.parseInt(l3c3.getText()))){
//                                System.out.println("Duplicate entries are not allowed !!!");
//                            }else{
//                   String[] temp1 = l3n1.getValue().split(" ... ");
//              String[] temp2 = l3n2.getValue().split(" ... ");
//              String[] temp3 = temp2[0].split("-");
//                      //     System.out.println(temp2[0] +" "+ temp2[1]);
//        Hibernate.DB_Connection.getSession().save(new Level_3(Integer.parseInt(temp1[0]),temp1[1],Integer.parseInt(temp3[1]),temp2[1],Integer.parseInt(l3c3.getText()),l3n3.getText()));
//        Hibernate.DB_Connection.endSession();
//        list3.getItems().add(l3c3.getText() + " ... " + l3n3.getText());
//        clearl3();
//               }

     String[] temp1 = l3n1.getValue().split(" ... ");
              String[] temp2 = l3n2.getValue().split(" ... ");
              String[] temp3 = temp2[0].split("-");
                      //     System.out.println(temp2[0] +" "+ temp2[1]);
        Hibernate.DB_Connection.getSession().save(new Level_3(Integer.parseInt(temp1[0]),temp1[1],Integer.parseInt(temp3[1]),temp2[1],Integer.parseInt(l3c3.getText()),l3n3.getText()));
        Hibernate.DB_Connection.endSession();
        list3.getItems().add(temp1[0]+"-"+temp3[1]  +"-"+l3c3.getText()+ " ... " + l3n3.getText());
        clearl3();
             
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    

  
    public void clearl1(){
        l1c1.setText("");l1n1.setText("");
    }
      public void clearl2(){
         l2n1.setValue("Select Level 1 Entry");
        l2c2.setText("");l2n2.setText("");
    }
        public void clearl3(){
        l3n1.setValue("Select Level 1 Entry");l3n2.setValue("Select Level 2 Entry");
        l3c3.setText("");l3n3.setText("");
    }
    
    @FXML
    public void Delete1(ActionEvent e){
         new Level_1().Delete(Integer.parseInt(l1c1.getText()));
         clearl1();
    }
    @FXML
    public void Delete2(ActionEvent e){
        new Level_2().Delete(Integer.parseInt(l2c2.getText()));
        clearl2();
    }
    @FXML
    public void Delete3(ActionEvent e){
         new Level_3().Delete(Integer.parseInt(l3c3.getText()));
         clearl3();
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
     
     
     //  Helper Methods
     

     
     
     
}
