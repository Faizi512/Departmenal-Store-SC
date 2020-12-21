/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.PurchaseInvoice;

import Modal.Invoice.PurchaseInvoice;
import Modal.ProductInventory.Product;
import Modal.RegisterUser.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdul Muazzam
 */
public class PurchaseInvoiceController implements Initializable {
public static int invoiceId;PurchaseInvoice EditablePI;
    @FXML
    private JFXComboBox<String> product;
    @FXML
    private JFXComboBox<String> category;
    @FXML
    private JFXTextField description;
    @FXML
    private DatePicker date;
    @FXML
    private JFXComboBox<String> timeh;
    @FXML
    private JFXComboBox<String> timem;
    @FXML
    private JFXComboBox<String> time;
    @FXML
    private JFXTextField supplier;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField approvedby;
    @FXML
    private Label invoice;

    
   ArrayList<PurchaseInvoice> pilist= Modal.Invoice.PurchaseInvoice.getAllPurchaseInvoices();
ObservableList<PurchaseInvoice> TabelPurchaseInvoiceList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    String[] h = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    String[] m = new String[60];  
    String[] ti = {"AM","PM"};
     
ObservableList<String> hours = FXCollections.observableArrayList(h);
ObservableList<String> t = FXCollections.observableArrayList(ti);
    @FXML
    private TableView<PurchaseInvoice> PITable;
    @FXML
    private TableColumn<PurchaseInvoice, String> SerialNo;
    @FXML
    private TableColumn<PurchaseInvoice, String> Category;
    @FXML
    private TableColumn<PurchaseInvoice, String> Product;
    @FXML
    private TableColumn<PurchaseInvoice, String> Date;
    @FXML
    private TableColumn<PurchaseInvoice, String> Time;
    @FXML
    private TableColumn<PurchaseInvoice, String> Desc;
    @FXML
    private TableColumn<PurchaseInvoice, String> Supplier;
    @FXML
    private TableColumn<PurchaseInvoice, String> Address;
    @FXML
    private TableColumn<PurchaseInvoice, String> ApprovedBy;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        invoiceId = Modal.Invoice.PurchaseInvoice.getPurchaseInvoiceTranscationID() + 1;
        invoice.setText(Integer.toString(invoiceId));
         ObservableList<String> l =  FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_2.getL2ComboList());
        category.setItems(l);
        ObservableList<String> l1 =  FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_3.getL3ComboList());
        product.setItems(l1);
        timeh.setItems(hours);setTimeMinuteComboBox();
        ObservableList<String> minute = FXCollections.observableArrayList(m);
        timem.setItems(minute);
        time.setItems(t);
        time.setValue("AM");
        addPurchaseInvoicesToTable();
        
        //  edit working
        
             PITable.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
        EditablePI = PITable.getSelectionModel().getSelectedItem();
                
       invoice.setText(Integer.toString(EditablePI.getInvoice_ID()));
        category.setValue(EditablePI.getCategory());
         product.setValue(EditablePI.getProduct());
         String[] dateparse = EditablePI.getDate().split("/");
         String[] timeparse = EditablePI.getTime().split(":");
         String[] temp = timeparse[1].split(" ");
        date.setValue(LocalDate.of(Integer.parseInt(dateparse[2]), Integer.parseInt(dateparse[1]), Integer.parseInt(dateparse[0])));
         timeh.setValue(timeparse[0]);timem.setValue(temp[0]);
         time.setValue(temp[1]);
         description.setText(EditablePI.getDescription());
         supplier.setText(EditablePI.getSupplier());address.setText(EditablePI.getAddress());
         approvedby.setText(EditablePI.getApprovedBy());
            }
        });
        
        
        
        
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
    
     public void Save(ActionEvent e){
         
         LocalDate Date = date.getValue();
         
         PurchaseInvoice pi = new PurchaseInvoice();
         pi.setCategory(category.getSelectionModel().getSelectedItem());
         pi.setProduct(product.getSelectionModel().getSelectedItem());
         pi.setDate(Integer.toString(Date.getDayOfMonth())+"/"+ Integer.toString(Date.getMonthValue())+"/"+
                 Integer.toString(Date.getYear()));
         pi.setTime(timeh.getSelectionModel().getSelectedItem()+":"+timem.getSelectionModel().getSelectedItem()
         +" "+ time.getSelectionModel().getSelectedItem());
         pi.setDescription(description.getText());
         pi.setSupplier(supplier.getText());
         pi.setAddress(address.getText());
         pi.setApprovedBy(approvedby.getText());
         
         // save
         PurchaseInvoice.save(pi);
         TabelPurchaseInvoiceList.add(pi);
         PITable.setItems(TabelPurchaseInvoiceList);
         invoice.setText(Integer.toString(invoiceId +1));
         ++invoiceId;
         clear();
     }
     
     public void Delete(ActionEvent e){
         
         PurchaseInvoice pi = PITable.getSelectionModel().getSelectedItem();
         TabelPurchaseInvoiceList.remove(pi);
         Modal.Invoice.PurchaseInvoice.Delete(pi);
         
         
     }
     
     public void Update(ActionEvent e){
       LocalDate Date = date.getValue();
       
        
        
         EditablePI.setCategory(category.getSelectionModel().getSelectedItem());
         EditablePI.setProduct(product.getSelectionModel().getSelectedItem());
         EditablePI.setDate(Integer.toString(Date.getDayOfMonth())+"/"+ Integer.toString(Date.getMonthValue())+"/"+
                 Integer.toString(Date.getYear()));
         EditablePI.setTime(timeh.getSelectionModel().getSelectedItem()+":"+timem.getSelectionModel().getSelectedItem()
         +" "+ time.getSelectionModel().getSelectedItem());
         EditablePI.setDescription(description.getText());
         EditablePI.setSupplier(supplier.getText());
         EditablePI.setAddress(address.getText());
         EditablePI.setApprovedBy(approvedby.getText());
             Modal.Invoice.PurchaseInvoice.update(EditablePI);
           //  System.out.println(EditablePI.getInvoice_ID());
         
         clear();
         invoice.setText(Integer.toString(invoiceId));
         
     }
     
     public void clear(){
         category.getSelectionModel().select(-1);
         product.getSelectionModel().select(-1);
        date.setValue(LocalDate.now());
         timeh.getSelectionModel().select(-1);timem.getSelectionModel().select(-1);
         time.getSelectionModel().select(-1);
         description.setText("");supplier.setText("");address.setText("");approvedby.setText("");
     }
     
     public void setTimeMinuteComboBox(){
         for (int i = 0; i < 60; i++) {
             if(i<=9){
                 m[i] = ("0" + Integer.toString(i+1));
             }else{
                 m[i] = Integer.toString(i+1);
             }
             
         }
     }
     
     public void addPurchaseInvoicesToTable(){
        
        
        for (int i = 0; i < pilist.size(); i++) {
            PurchaseInvoice p = pilist.get(i);
           // System.out.println(p.getTranscationId());
            TabelPurchaseInvoiceList.add(p);
        }
    //    System.out.println("List size:" + TabelProductList.size());
        

            SerialNo.setCellValueFactory(new PropertyValueFactory<>("Invoice_ID"));
        Category.setCellValueFactory(new PropertyValueFactory<>("category"));
 Product.setCellValueFactory(new PropertyValueFactory<>("product"));
     Date.setCellValueFactory(new PropertyValueFactory<>("date"));
     Time.setCellValueFactory(new PropertyValueFactory<>("time"));
   Desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
    Supplier.setCellValueFactory(new PropertyValueFactory<>("Supplier"));
     Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
     ApprovedBy.setCellValueFactory(new PropertyValueFactory<>("ApprovedBy"));
    

        PITable.setItems(TabelPurchaseInvoiceList);
    }
    
}
