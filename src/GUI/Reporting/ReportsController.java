/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reporting;

import Modal.Reports.SalesReport;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdul Muazzam
 */
public class ReportsController implements Initializable {

    @FXML
    private TableView<SalesReport> Report_Table;
    @FXML
    private TableColumn<SalesReport, Integer> invoice;
    @FXML
    private TableColumn<SalesReport, LocalDate> date;
    private TableColumn<SalesReport, String> product;
    private TableColumn<SalesReport, Double> quantity;
    @FXML
    private TableColumn<SalesReport, Double> gross;
    @FXML
    private TableColumn<SalesReport, Double> discount;
    @FXML
    private TableColumn<SalesReport, Double> amount;
    @FXML
    private Label TGross;
    @FXML
    private Label TDiscount;
    @FXML
    private Label TSales;
    
    @FXML
    private JFXComboBox<String> MonthSelection;
    @FXML
    private JFXComboBox<String> YearSelection;
    
    ObservableList MonthSelectionList = FXCollections.observableArrayList();
    ObservableList YearSelectionList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initializeComboxes();
        TableInitailzation(); 
  
                
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

    @FXML
    private void GenerateReport(ActionEvent event) {
        
        if(!MonthSelection.getSelectionModel().isEmpty() && !YearSelection.getSelectionModel().isEmpty()){
   ObservableList<SalesReport> srl = SalesReport.getReport(MonthSelection.getSelectionModel().getSelectedItem(), YearSelection.getSelectionModel().getSelectedItem());      
    Report_Table.setItems(srl);
    
    if(srl.isEmpty()){
        System.out.println("No Record Found !!!");
        TGross.setText("0");
        TDiscount.setText("0");
        TSales.setText("0");
    }else{
        
        double g=0.0,d=0.0,a=0.0;
        for (int i = 0; i < srl.size(); i++) {
            g += srl.get(i).getGross();
            d += srl.get(i).getDiscount();
            a += srl.get(i).getAmount();
        }
        TGross.setText(Double.toString(g));
        TDiscount.setText(Double.toString(d));
        TSales.setText(Double.toString(a));
        
    }
        }else{
            System.out.println("Input Missing !!!");
        }
        
  
    
    }
    
    
    

    @FXML
    private void Reset(ActionEvent event) {
        
        TGross.setText("0");
        TDiscount.setText("0");
        TSales.setText("0");
        Report_Table.getItems().clear();
        MonthSelection.getSelectionModel().select(-1);
        YearSelection.getSelectionModel().select(-1);
    }
    
    
    public void initializeComboxes(){
        
        for (int i = 1; i <= 12; i++) {
            MonthSelectionList.add("" + i);
        }
        
        for (int i = 2010; i <= 2018; i++) {
            YearSelectionList.add("" + i);
        }
        
       MonthSelection.setItems(MonthSelectionList);
       YearSelection.setItems(YearSelectionList);
        
        
        
    }
    
   public void TableInitailzation(){
       invoice.setCellValueFactory(new PropertyValueFactory<>("Invoice_no"));
     date.setCellValueFactory(new PropertyValueFactory<>("Date"));
    gross.setCellValueFactory(new PropertyValueFactory<>("Gross"));
     discount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
     amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
     
     
   }
     
}
