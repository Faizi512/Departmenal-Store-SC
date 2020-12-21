/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal.ProductInventory;

import Modal.Chart_of_Accounts.Level_2;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Abdul Muazzam
 */

@Entity
public class Product {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int TranscationId = 0;
    protected String Product_ID;
    protected int Available_Stock,Critical_Stock_Point;
  //  protected Level_2 Catrgory;
    protected String Product_Name;       
    protected String Brand,Supplier,UOM,Status;
    protected double PurchasePrice,SalePrice;
  //  protected Date Manufacture_Date,Expiry_Date,Date;
    protected String Manufacture_Date,Expiry_Date,Date;
        
    
    public Product() {
        Product_ID="";Product_Name="";Available_Stock=0;Critical_Stock_Point=0;
        UOM="";Status="";Brand="";Supplier="";PurchasePrice=0;SalePrice=0;
        
    }

    public Product(String Product_ID,String Product_Name, int Available_Stock, int Critical_Stock_Pointy, String UOM, String Status, String Brand, String Supplier, String Manufacturer, double PurchasePrice, double SalePrice, String Manufacture_Date, String Expiry_Date, String Date) {
        this.Product_ID = Product_ID;
        this.Product_Name = Product_Name;
        this.Available_Stock = Available_Stock;
        this.Critical_Stock_Point = Critical_Stock_Pointy;
        this.UOM = UOM;
        this.Status = Status;
        this.Brand = Brand;
        this.Supplier = Supplier;
        this.PurchasePrice = PurchasePrice;
        this.SalePrice = SalePrice;
        this.Manufacture_Date = Manufacture_Date;
        this.Expiry_Date = Expiry_Date;
        this.Date = Date;
    }

    public int getTranscationId() {
        return TranscationId;
    }

    
    
    public void setAvailable_Stock(int Available_Stock) {
        this.Available_Stock = Available_Stock;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public void setCritical_Stock_Point(int Critical_Stock_Point) {
        this.Critical_Stock_Point = Critical_Stock_Point;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setExpiry_Date(String Expiry_Date) {
        this.Expiry_Date = Expiry_Date;
    }

    public void setManufacture_Date(String Manufacture_Date) {
        this.Manufacture_Date = Manufacture_Date;
    }

    public void setProduct_ID(String Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setPurchasePrice(double PurchasePrice) {
        this.PurchasePrice = PurchasePrice;
    }

    public void setSalePrice(double SalePrice) {
        this.SalePrice = SalePrice;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setSupplier(String Supplier) {
        this.Supplier = Supplier;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public int getAvailable_Stock() {
        return Available_Stock;
    }

    public String getBrand() {
        return Brand;
    }

    public int getCritical_Stock_Point() {
        return Critical_Stock_Point;
    }

    public String getDate() {
        return Date;
    }

    public String getExpiry_Date() {
        return Expiry_Date;
    }

    public String getManufacture_Date() {
        return Manufacture_Date;
    }
    public String getProduct_ID() {
        return Product_ID;
    }

    public double getPurchasePrice() {
        return PurchasePrice;
    }

    public double getSalePrice() {
        return SalePrice;
    }

    public String getStatus() {
        return Status;
    }

    public String getSupplier() {
        return Supplier;
    }

    public String getUOM() {
        return UOM;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public String getProduct_Name() {
        return Product_Name;
    }
    
   

    @Override
    public String toString() {
        return "ID: " + getProduct_ID() +"\n Name:"+ getProduct_Name() +"\n Price:"+ getSalePrice(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
public static void save(Product p){
    try {
        Hibernate.DB_Connection.getSession().saveOrUpdate(p);
    Hibernate.DB_Connection.endSession();
    } catch (Exception e) {
        System.out.println(e);
    }
}

public static int getProductTranscationID(){
    ArrayList<Product> p = null;
    try {
     String hql = "FROM Modal.ProductInventory.Product ";

    Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           p = (ArrayList<Product>) query.getResultList();
     Hibernate.DB_Connection.endSession();
   

    } catch (Exception e) {
        System.out.println(e);
    }
     return p.size();
}

    public static ArrayList<Product> getAllProducts(){
         ArrayList<Product> r;
           String hql = "FROM Modal.ProductInventory.Product";
      Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           r = (ArrayList<Product>) query.getResultList();
     Hibernate.DB_Connection.endSession();
return r;
}
    
        public static Product getSingleProduct(String Code){
         ArrayList<Product> r;
           String hql = "FROM Modal.ProductInventory.Product where Product_ID = :id";
      Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
         query.setParameter("id", Code);
           r = (ArrayList<Product>) query.getResultList();
     Hibernate.DB_Connection.endSession();
return r.get(0);
}
  
               public static boolean checkDuplicates(Integer x){
        ArrayList<Integer> r;
          String hql = "Select TranscationId FROM Modal.ProductInventory.Product";
     
      Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           r = (ArrayList<Integer>) query.getResultList();
  
     Hibernate.DB_Connection.endSession();
        
        for (int i = 0; i < r.size(); i++) {
            if(Objects.equals(r.get(i), x))
            return true;
            
        }
       // System.out.println("size"+r.size());
        
       return false; 
    }
               
        
//                       public static boolean Update(Product p){
//              String hql = "UPDATE Product set Product_ID = :Product_ID, set Product_Name = :Product_Name,"
//                      + "set Available_Stock = :Available_Stock,set Critical_Stock_Point = :Critical_Stock_Point,set UOM = :UOM,"
//                      + "set Status = :Status,set Brand = :Brand,set Supplier = :Supplier,set PurchasePrice = :PurchasePrice,"
//                      + "set SalePrice = :SalePrice,set Manufacture_Date = :Manufacture_Date,set Expiry_Date = :Expiry_Date,"
//                      + "set Date = :Date, "  + 
//             "WHERE TranscationId = :TranscationId";
//              
//                 Session s =  Hibernate.DB_Connection.getSession();
//         Query query = s.createQuery(hql);
//              
//
//query.setParameter("TranscationId", p.getTranscationId());
//query.setParameter("Product_ID",p.getProduct_ID() );
//query.setParameter("Product_Name",p.getProduct_Name() );
//query.setParameter("Available_Stock",p.getAvailable_Stock() );
//query.setParameter("Critical_Stock_Point",p.getCritical_Stock_Point() );
//query.setParameter("UOM", p.getUOM());
//query.setParameter("Status", p.getStatus());
//query.setParameter("Brand", p.getBrand());
//query.setParameter("Supplier", p.getSupplier());
//query.setParameter("PurchasePrice", p.getPurchasePrice());
//query.setParameter("SalePrice", p.getSalePrice());
//query.setParameter("Manufacture_Date", p.getManufacture_Date());
//query.setParameter("Expiry_Date", p.getExpiry_Date());
//query.setParameter("Date", p.getDate());
//int result = query.executeUpdate();
//            return result > 0;
////System.out.println("Rows affected: " + result);
//
//          }
               
               
}
