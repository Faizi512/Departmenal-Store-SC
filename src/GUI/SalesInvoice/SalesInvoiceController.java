/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SalesInvoice;

import Modal.Invoice.SalesInvoice;
import Modal.ProductInventory.Product;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import javafx.scene.control.*;
import org.controlsfx.control.action.Action;

/**
 * FXML Controller class
 *
 * @author Abdul Muazzam
 */
public class SalesInvoiceController implements Initializable {


    public static double grossAmount = 0;
    public static double DiscountedAmount = 0;
    public static double NetAmount = 0,paid = 0,changeDue=0;
    public static int SalesProductTableIndexListing = 1;
    public static int SalesInvoiceTransactionID;
    @FXML
    private Label invoice;
    @FXML
    private Label casherCode;
    @FXML
    private Label casherName;
    @FXML
    private Label PosID;
    @FXML
    private Label uan;
    @FXML
    private Label gst;
    @FXML
    private Label gross;
    @FXML
    private Label discount;
    @FXML
    private JFXTextField Percentdiscount;
    @FXML
    private Label netBill;
    @FXML
    private JFXTextField searchByItems;
    @FXML
    private JFXTextField searchByID;

    /**
     * Initializes the controller class.
     */
    String selected_item = "";
      ArrayList<Product> products;
    @FXML
    private TableView<Product> ProductTable;
    @FXML
    private TableColumn<Product, String> SerialNo;
    @FXML
    private TableColumn<Product, String> TransID;
    @FXML
    private TableColumn<Product, String> Pcode;
    @FXML
    private TableColumn<Product, String> Pname;
    @FXML
    private TableColumn<Product, String> Brand;
    @FXML
    private TableColumn<Product, String> Supplier;
    @FXML
    private TableColumn<Product, String> SPrice;
    @FXML
    private TableColumn<Product, String> PPrice;
    @FXML
    private TableColumn<Product, String> AvalStock;
    @FXML
    private TableColumn<Product, String> CriticalStock;
    @FXML
    private TableColumn<Product, String> ManDate;
    @FXML
    private TableColumn<Product, String> ExDate;
    @FXML
    private TableColumn<Product, String> uom;
    @FXML
    private TableColumn<Product, String> Status;
    @FXML
    private TableColumn<Product, String> date;
    
    
    @FXML
    private TableView<salesEntery> productSalesEntryTableView;
    @FXML
    private TableColumn<salesEntery, String> pSerial;
    @FXML
    private TableColumn<salesEntery, String> pName;
    @FXML
    private TableColumn<salesEntery, String> pQty;
    @FXML
    private TableColumn<salesEntery, String> pRate;
    @FXML
    private TableColumn<salesEntery, String> pAmount;
   
  //  ArrayList<Product> plist= Modal.ProductInventory.Product.getAllProducts();
ObservableList<Product> TabelProductList = FXCollections.observableArrayList();
ObservableList<salesEntery> productSalesEntryList = FXCollections.observableArrayList();

    @FXML
    private Label currenttime;
    @FXML
    private Label currentdate;
    @FXML
    private JFXTextField ItemQuantity;
    @FXML
    private Label paidamount;
    @FXML
    private Label due;
    @FXML
    private JFXTextField paid_amount;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        products =  Modal.ProductInventory.Product.getAllProducts();
        
//        User u = LoginController.user;
//        casherCode.setText(Integer.toString(u.getID()));
//        casherName.setText(u.getName());
//        PosID.setText(u.getPOS_ID());
    SalesInvoiceTransactionID = SalesInvoice.getSalesTranscationID()+1;
invoice.setText(Integer.toString(SalesInvoiceTransactionID));
uan.setText(SalesInvoice.getUAN());
gst.setText(SalesInvoice.getGst_Number());

currentdate.setText(LocalDate.now().toString());
currenttime.setText(LocalTime.now().toString());

InitilaizeProductToTable();

//Alert alert = new Alert(AlertType.INFORMATION);
//alert.setTitle("Information Dialog");
//alert.setHeaderText("Look, an Information Dialog");
//alert.setContentText("I have a great message for you!");
//
//alert.showAndWait();
   
      //   String[] opt = {"moazzam","maheem","maryam","bano","nasir","bilal","imran"}; 
         
      TextFields.bindAutoCompletion(searchByID, getProductIDs());
      TextFields.bindAutoCompletion(searchByItems, getProductItems());
        
        ProductTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

    @Override
    public void handle(MouseEvent click) {

        if (click.getClickCount() == 2) {
           //Use ListView's getSelected Item
           
           Product prod = ProductTable.getSelectionModel().getSelectedItem();
           
          //  System.out.println(prod);
            
            salesEntery se = new salesEntery();
            
            
            
            if(!ItemQuantity.getText().isEmpty()){
                
               Product StoredProduct = Product.getSingleProduct(prod.getProduct_ID());
                if(StoredProduct.getAvailable_Stock() >= Integer.parseInt(ItemQuantity.getText())){
                      se.setProduct(prod.getProduct_ID() +" ... "+ prod.getProduct_Name());
            se.setPrice((int) prod.getSalePrice());
                se.setProductQty(ItemQuantity.getText());
                
                se.setAmount(Integer.parseInt(se.getProductQty()) * se.getPrice());
               // IndexListing();
               se.setIndex(SalesProductTableIndexListing);
               ++SalesProductTableIndexListing;
            productSalesEntryTableView.getItems().add(se);
            grossAmount += se.getAmount();
            gross.setText(Double.toString(grossAmount));
            NetAmount = (Double.parseDouble(gross.getText()));
            netBill.setText(Double.toString(NetAmount));
            ItemQuantity.setText("");
                }else{
                    System.out.println("Stock is low or unavailable !!!");
                }
                
                
          
            }else{
                ItemQuantity.setFocusTraversable(true);
                System.out.println("Quantity needed !!!");
            }
            
            
            
        }
       
    }
        });
        
        paid_amount.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent ke) {
            KeyCode kc = ke.getCode();
            if ((kc.equals(KeyCode.ENTER) )) {
                paid = (Double.parseDouble(paid_amount.getText()));
                due.setText(Double.toString((Double.parseDouble(paid_amount.getText())) - NetAmount));
                paid_amount.setText("");
            }
        }
    });
        
                Percentdiscount.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent ke) {
            KeyCode kc = ke.getCode();
            if ((kc.equals(KeyCode.ENTER) )) {
                 DiscountedAmount = Double.parseDouble(Percentdiscount.getText());
            DiscountedAmount /= 100;
            Double truncatedDouble;
                truncatedDouble = BigDecimal.valueOf(grossAmount * DiscountedAmount)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();
            discount.setText(Double.toString(truncatedDouble));
            NetAmount = (Double.parseDouble(gross.getText()) - Double.parseDouble(discount.getText()));
            netBill.setText(Double.toString(NetAmount));
            }
        }
    });
        
         
               paidamount.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent ke) {
            KeyCode kc = ke.getCode();
            if ((kc.equals(KeyCode.ENTER) )) {
                
                if(Double.parseDouble(paidamount.getText()) > NetAmount){
                    due.setText(Double.toString(NetAmount - Double.parseDouble(paidamount.getText())));
                }
                
                 
            }
        }
    });    
            
            
        
          pQty.setCellFactory(TextFieldTableCell.forTableColumn());
pQty.setOnEditCommit(event -> {
    String row = event.getOldValue();
    row = event.getNewValue();

   // System.out.println("test: " + event.getTableColumn().getTableView().getSelectionModel().getSelectedItem());


     ObservableList<salesEntery> s = pQty.getTableView().getItems();
     s.get(0).setAmount(s.get(0).getPrice() * Integer.parseInt(row));
     System.out.println(s.get(0));
//     for(Product sen: s){
//         System.out.println(sen);
//     }
});
      
    }    
    
    
      
    
         @FXML
     public void getSearchItem(ActionEvent e) throws IOException{
         searchByItems.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent ke) {
            KeyCode kc = ke.getCode();
            if ((kc.equals(KeyCode.ENTER) )) {
                
                if(!searchByItems.getText().isEmpty()){
                    selected_item =  searchByItems.getText();
              //   System.out.println(searchByItems.getText());
                 String[] t = selected_item.split(" ... ");
              Product  p =  getProduct(t[0]);
          //      System.out.println(p);
         ProductTable.getItems().add(p);
         searchByID.setText(t[0]);
         searchByItems.setText("");
                }else{
                    System.out.println("Select Item for Search !!!");
                }
                
                 
            }
        }
    });
        
         
         
     }
     
              @FXML
     public void getSearchItemByID(ActionEvent e) throws IOException{
         searchByItems.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent ke) {
            KeyCode kc = ke.getCode();
            if ((kc.equals(KeyCode.ENTER) )) {
               String selected_item_id =  searchByID.getText();
              //   System.out.println(searchByItems.getText());
                 
              Product  p =  getProduct(selected_item_id);
          //      System.out.println(p);
         ProductTable.getItems().add(p);
         
         searchByItems.setText(getProductItem(selected_item_id));
            }
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
     
     
     public String[] getProductIDs(){
         String[] ids = new String[products.size()];
         for (int i = 0; i < products.size(); i++) {
            ids[i]=products.get(i).getProduct_ID();
         }
         return ids;
     }
     
       public String[] getProductItems(){
         String[] items = new String[products.size()];
         for (int i = 0; i < products.size(); i++) {
            items[i]=products.get(i).getProduct_ID() +" ... "+ products.get(i).getProduct_Name();
         }
         return items;
     }
       
          public String getProductID(String s){
         
         for (int i = 0; i < products.size(); i++) {
             if(products.get(i).getProduct_Name().equals(s))
            return products.get(i).getProduct_ID();
         }
         return "";
     }
     
             public String getProductItem(String s){
         
         for (int i = 0; i < products.size(); i++) {
             if(products.get(i).getProduct_ID().equals(s))
            return products.get(i).getProduct_Name();
         }
         return "";
     }
             
                  public Product getProduct(String code){
         
         for (int i = 0; i < products.size(); i++) {
             if(products.get(i).getProduct_ID().equals(code))
            return products.get(i);
         }
         return null;
     }
                  
   
         
             
             
                public void InitilaizeProductToTable(){
        
        
 
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
        
        
        productSalesEntryTableView.setEditable(true);
       
         pSerial.setCellValueFactory(new PropertyValueFactory<>("index"));
         pName.setCellValueFactory(new PropertyValueFactory<>("Product"));
         pQty.setCellValueFactory(new PropertyValueFactory<>("ProductQty"));
         pRate.setCellValueFactory(new PropertyValueFactory<>("price"));
         pAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
      //  pQty.setCellValueFactory(TextFieldTableCell.forTableColumn());
        productSalesEntryTableView.setItems(productSalesEntryList);
        
          // update table to allow editable qty
         productSalesEntryTableView.setEditable(true);
         pQty.setCellFactory(TextFieldTableCell.forTableColumn());
         
    
   
         
    }
                
      @FXML
      public void Save(ActionEvent e){
          SalesInvoice si = new SalesInvoice();
          
          
          for (int i = 0; i < productSalesEntryList.size(); i++) {
              si.getSalesProducts().add(productSalesEntryList.get(i));              
          }
          
        
        si.setGross(Double.parseDouble(gross.getText()));
        si.setDiscountPercentAmount(Double.parseDouble(discount.getText()));
        si.setNetBill(Double.parseDouble(netBill.getText()));
    //    si.setPaidAmount(Double.parseDouble(paid_amount.getText()));
    si.setPaidAmount(paid);
        si.setDueChange(Double.parseDouble(due.getText()));
        si.setPOS_ID(PosID.getText());
        si.setDate(LocalDate.now());
        si.setTime(LocalTime.now());
        si.setUserCode(Integer.parseInt(casherCode.getText()));
        si.setUserName(casherName.getText());

        Session s = Hibernate.DB_Connection.getSession();
        s.save(si);
        s.getTransaction().commit();
        s.close();
        
        ++SalesInvoiceTransactionID;
        invoice.setText(Integer.toString(SalesInvoiceTransactionID));
        clearAll();
//System.out.println(LocalDate.now());
//System.out.println(LocalTime.now());
      }
      
      public void clearAll(){
          gross.setText("0");
          discount.setText("0");
          Percentdiscount.setText("0");
          netBill.setText("0");
          paid_amount.setText("0");
          due.setText("0");
          ProductTable.getItems().clear();
          productSalesEntryTableView.getItems().clear();
      }
      
      
}
