/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.InventoryUpdate;

import Modal.Chart_of_Accounts.Level_3;
import Modal.Enums.UOM;
import Modal.ProductInventory.Product;
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
public class ProductInventoryUpdateController implements Initializable {

    @FXML private JFXTextField P_Price;
    @FXML private JFXComboBox<String> Level_2Combo;
    @FXML private JFXComboBox<String> Level_3Combo;
    @FXML private JFXTextField supplier;
    @FXML private JFXTextField available_Stock;
    @FXML private JFXTextField critical_Stock;
    @FXML private DatePicker manufactureDate;
    @FXML private DatePicker ExpiryDate;
    @FXML private Label Trans_ID;
    @FXML private JFXComboBox<String> statusCombo;
    @FXML private JFXTextField brand;
    @FXML private JFXTextField S_Price;
    @FXML private JFXComboBox<String> UOMCombo;
    @FXML private Label curr_Date;
    @FXML private TableView<Product> ProductTable;
    @FXML private TableColumn<Product, String> SerialNo;
    @FXML private TableColumn<Product, String> TransID;
    @FXML private TableColumn<Product, String> Pcode;
    @FXML private TableColumn<Product, String> Pname;
    @FXML private TableColumn<Product, String> Brand;
    @FXML private TableColumn<Product, String> Supplier;
    @FXML private TableColumn<Product, String> SPrice;
    @FXML private TableColumn<Product, String> PPrice;
    @FXML private TableColumn<Product, String> AvalStock;
    @FXML private TableColumn<Product, String> CriticalStock;
    @FXML private TableColumn<Product, String> ManDate;
    @FXML private TableColumn<Product, String> ExDate;
    @FXML private TableColumn<Product, String> uom;
    @FXML private TableColumn<Product, String> Status;
    @FXML private TableColumn<Product, String> date;
ArrayList<Product> plist= Modal.ProductInventory.Product.getAllProducts();
ObservableList<Product> TabelProductList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // sets Current dateOfTranscation & the TransscationID
        curr_Date.setText(Modal.Date_Time.Date.getCurrentDate());
Trans_ID.setText(Integer.toString(Modal.ProductInventory.Product.getProductTranscationID()+1));
       // initialize combo boxes
        ObservableList<String> l =  FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_2.getL2ComboList());
        Level_2Combo.setItems(l);
        ObservableList<String> l1 =  FXCollections.observableArrayList(Modal.Chart_of_Accounts.Level_3.getL3ComboList());
        Level_3Combo.setItems(l1);
        String[] status = new String[2];status[0]="Active";status[1]="InActive";
        ObservableList<String> l3 =  FXCollections.observableArrayList(status);
        statusCombo.setItems(l3);
        UOM[] uom = UOM.values();String[] strUOM = new String[uom.length];
        for (int i = 0; i < uom.length; i++) {
            strUOM[i] = uom[i].toString();
            
        }
        ObservableList<String> l4 =  FXCollections.observableArrayList(strUOM);
        UOMCombo.setItems(l4);
        // set Default values
        available_Stock.setText(Integer.toString(0));P_Price.setText(Integer.toString(0));
        critical_Stock.setText(Integer.toString(0));S_Price.setText(Integer.toString(0));
        // populate Enteries on table
        addProductsToTable();
        // year month date
     //   manufactureDate.setValue(LocalDate.of(0, 0, 0));
        
    }  
    
    
    
    
    
    
    
    @FXML
     public void Save(ActionEvent e){
         
                 String[] id = Level_3Combo.getValue().split(" ... ");
        Product p = new Product();
        LocalDate ManufactureDate = manufactureDate.getValue();
        LocalDate expiryDate = ExpiryDate.getValue();
  
                 
                 
        p.setProduct_ID(id[0]);
        p.setProduct_Name(id[1]);
        p.setAvailable_Stock(Integer.parseInt(available_Stock.getText()));
        p.setCritical_Stock_Point(Integer.parseInt(critical_Stock.getText()));
        p.setUOM(UOMCombo.getValue());
        p.setStatus(statusCombo.getValue());
         p.setBrand(brand.getText());
         p.setSupplier(supplier.getText());
         p.setPurchasePrice(Double.parseDouble(P_Price.getText()));
         p.setSalePrice(Double.parseDouble(S_Price.getText()));
//         p.setManufacture_Date(new Date(ManufactureDate.getDayOfMonth(),
//        ManufactureDate.getMonthValue(),
//        ManufactureDate.getYear()));
//        p.setExpiry_Date(new Date(expiryDate.getDayOfMonth(),expiryDate.getMonthValue(),expiryDate.getYear()));
//        String[] date = curr_Date.getText().split("/");
//        p.setDate(new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2])));
//     

p.setManufacture_Date(ManufactureDate.getDayOfMonth()+"/"+ManufactureDate.getMonthValue()+"/"+ManufactureDate.getYear());
p.setExpiry_Date(expiryDate.getDayOfMonth()+"/"+expiryDate.getMonthValue()+"/"+expiryDate.getYear());
p.setDate(curr_Date.getText());

         
 if(!Modal.ProductInventory.Product.checkDuplicates(Integer.parseInt(Trans_ID.getText()))){
    
Product.save(p);
 clear();       
Trans_ID.setText(Integer.toString(Modal.ProductInventory.Product.getProductTranscationID()+1));       
        TabelProductList.add(p);
 }else{
//     TabelProductList.remove(p);
//     Modal.ProductInventory.Product.Update(p);
//     clear();       
//Trans_ID.setText(Integer.toString(Modal.ProductInventory.Product.getProductTranscationID()));  
//
//        TabelProductList.add(p);
 }
         
         
         
         
     }
     
    @FXML
     public void Delete(ActionEvent e){
Product p = ProductTable.getSelectionModel().getSelectedItem();
Hibernate.DB_Connection.getSession().delete(p);
     Hibernate.DB_Connection.endSession();
     TabelProductList.remove(p);
     //return result > 0;


     }
    
    @FXML
    public void Edit(ActionEvent e){
        Product p = ProductTable.getSelectionModel().getSelectedItem();
        String[] t = p.getProduct_ID().split("-");
       Level_3 l3 = Modal.Chart_of_Accounts.Level_3.getL3Record(Integer.parseInt(t[2]));
       
             P_Price.setText(Double.toString(p.getPurchasePrice()));
    Level_2Combo.setValue(l3.getL1l2() + " ... " + l3.getName2());
    Level_3Combo.setValue(p.getProduct_ID() + " ... " + p.getProduct_Name());
     supplier.setText(p.getSupplier());
    available_Stock.setText(Integer.toString(p.getAvailable_Stock()));
   critical_Stock.setText(Integer.toString(p.getCritical_Stock_Point()));
   String[] temp1 = p.getManufacture_Date().split("/");
   LocalDate mandate = LocalDate.of(Integer.parseInt(temp1[2]),Integer.parseInt(temp1[1]),Integer.parseInt(temp1[0]));
    manufactureDate.setValue(mandate);
     String[] temp2 = p.getExpiry_Date().split("/");
   LocalDate exdate = LocalDate.of(Integer.parseInt(temp2[2]),Integer.parseInt(temp2[1]),Integer.parseInt(temp2[0]));
    ExpiryDate.setValue(exdate);
     statusCombo.setValue(p.getStatus());
     brand.setText(p.getBrand());
    S_Price.setText(Double.toString(p.getSalePrice()));
     UOMCombo.setValue(p.getUOM());
Trans_ID.setText(Integer.toString(p.getTranscationId()));     
        
    }
    
    public void clear(){
            P_Price.setText(Integer.toString(0));
    Level_2Combo.setValue("Select Category");
    Level_3Combo.setValue("Select Product");
     supplier.setText("");
    available_Stock.setText(Integer.toString(0));
   critical_Stock.setText(Integer.toString(0));
     manufactureDate.setPromptText("Manufacture Date");
    ExpiryDate.setPromptText("Expiry Date");;
     statusCombo.setValue("Status");
     brand.setText("");
    S_Price.setText(Integer.toString(0));
     UOMCombo.setValue("UOM");
    }
    
    public void addProductsToTable(){
        
        
        for (int i = 0; i < plist.size(); i++) {
            Product p = plist.get(i);
           // System.out.println(p.getTranscationId());
            TabelProductList.add(p);
        }
    //    System.out.println("List size:" + TabelProductList.size());
        
        TransID.setCellValueFactory(new PropertyValueFactory<>("TranscationId"));
            SerialNo.setCellValueFactory(new PropertyValueFactory<>("TranscationId"));
        Pcode.setCellValueFactory(new PropertyValueFactory<>("Product_ID"));
 Pname.setCellValueFactory(new PropertyValueFactory<>("Product_Name"));
     Brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
     Supplier.setCellValueFactory(new PropertyValueFactory<>("Supplier"));
   SPrice.setCellValueFactory(new PropertyValueFactory<>("SalePrice"));
    PPrice.setCellValueFactory(new PropertyValueFactory<>("PurchasePrice"));
     AvalStock.setCellValueFactory(new PropertyValueFactory<>("Available_Stock"));
     CriticalStock.setCellValueFactory(new PropertyValueFactory<>("Critical_Stock_Point"));
     ManDate.setCellValueFactory(new PropertyValueFactory<>("Manufacture_Date"));
     ExDate.setCellValueFactory(new PropertyValueFactory<>("Expiry_Date"));
     uom.setCellValueFactory(new PropertyValueFactory<>("UOM"));
   Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
    date.setCellValueFactory(new PropertyValueFactory<>("Date"));

    
        ProductTable.setItems(TabelProductList);
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
    
}
