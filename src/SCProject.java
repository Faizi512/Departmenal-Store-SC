/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import GUI.SalesInvoice.salesEntery;
import Modal.Invoice.SalesInvoice;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 *
 * @author Abdul Muazzam
 */
public class SCProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      
        
       
          try {
          Parent  root = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
   //       Parent  root = FXMLLoader.load(getClass().getResource("/GUI/ChartOfAccounts/ChartOfAccounts.fxml"));
 //     Parent  root = FXMLLoader.load(getClass().getResource("/GUI/InventoryUpdate/ProductInventoryUpdate.fxml"));
//         Parent  root = FXMLLoader.load(getClass().getResource("/GUI/MainScene/MainScene.fxml")); 
  //     Parent  root = FXMLLoader.load(getClass().getResource("/GUI/AddUsers/User.fxml"));        
//  Parent  root = FXMLLoader.load(getClass().getResource("/GUI/PurchaseInvoice/PurchaseInvoice.fxml"));  
//  Parent  root = FXMLLoader.load(getClass().getResource("/GUI/SalesInvoice/SalesInvoice.fxml"));  
//Parent  root = FXMLLoader.load(getClass().getResource("/GUI/Reporting/Reports.fxml"));  

         Scene scene = new Scene(root);
           
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
        
     
        } catch (IOException ex) {
            Logger.getLogger(SCProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
//        SalesInvoice si = new SalesInvoice();
//        salesEntery se = new salesEntery();
//        se.setProduct("apple");
//        se.setProductQty("5");
//        se.setPrice(20);
//        se.setAmount(100);
//        
//        salesEntery se1 = new salesEntery();
//        se1.setProduct("apple");
//        se1.setProductQty("5");
//        se1.setPrice(20);
//        se1.setAmount(100);
//        
//        salesEntery se2 = new salesEntery();
//        se2.setProduct("apple");
//        se2.setProductQty("5");
//        se2.setPrice(20);
//        se2.setAmount(100);
//        
//        
//        si.setGross(100);
//        si.setDiscountPercentAmount(0);
//        si.setNetBill(100.00);
//        si.setPaidAmount(1000);
//        si.setDueChange(900);
//        si.setPOS_ID("POS 001");
//        si.setDate(LocalDate.parse("2017-11-15"));
//        si.setTime(LocalTime.parse("15:30:18"));
//        si.setUserCode(01);
//        si.setUserName("moazzam");
//        si.getSalesProducts().add(se);
//        si.getSalesProducts().add(se1);
//        si.getSalesProducts().add(se2);
//        
//        Session s = Hibernate.DB_Connection.getSession();
//        s.save(se);
//        s.save(si);
//        s.getTransaction().commit();
//        s.close();
        
        
            launch(args);
          
          
          
                  
//       LocalDate localDate = LocalDate.now();//For reference
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
//String manufactDate = localDate.format(formatter);
//
//        localDate.getDayOfMonth();
//        localDate.getMonthValue();
//        localDate.getYear();
        
   //     SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//        try (Session s = Hibernate.DB_Connection.getSession()) {
//            s.beginTransaction();
//            s.save(new Level_1(1,"product"));
//            s.save(new Level_1(2,"supplier"));
//            s.save(new Level_2(1,"product",1,"groceries"));
//            s.save(new Level_2(2,"supplier",1,"Moazzam"));
//            s.save(new Level_3(1,"product",1,"groceries",1,"bread"));
//            s.save(new Level_3(1,"product",1,"groceries",2,"eggs"));
//         //   s.getTransaction().commit();
//          //s.close();
//
//        }

//      Modal.Chart_of_Accounts.Level_1 l1 = new Modal.Chart_of_Accounts.Level_1();
//      l1.getLevel_1Data();
        
    
     
//     ArrayList<String> a = new ArrayList<String>();
//     a.add("1 ... product");
//     a.add("2 ... supplier ");
//     a.add("1 ... product");
//     a.add("1 ... product");
//     a.add("1 ... product");
//     a.add("1 ... product");
//     a.add("1 ... product");
 //    ArrayList<String> b = Modal.Chart_of_Accounts.Level_1.removeDuplicates(a);
//    ArrayList<String> c = Modal.Chart_of_Accounts.Level_1.getL1List();
//          
//     for(String s: c){
//         System.out.println(s);
//     }
     
    }
    
}
