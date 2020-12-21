/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal.Invoice;

import java.io.Serializable;
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
public class PurchaseInvoice implements Serializable{

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      protected int Invoice_ID = 0;
   protected String Description,Address,category,product,date,time,Supplier,ApprovedBy;
  

    public PurchaseInvoice() {
         this.Description = "";
        this.Address = "";
        this.category = "";
        this.product = "";
        this.date = "";
        this.time = "";
        this.Supplier = "";
        this.ApprovedBy = "";
    }

    public PurchaseInvoice( String category, String product, String date, String time,String Description, String Address, String Supplier, String ApprovedBy) {
        this.Description = Description;
        this.Address = Address;
        this.category = category;
        this.product = product;
        this.date = date;
        this.time = time;
        this.Supplier = Supplier;
        this.ApprovedBy = ApprovedBy;
    }



    public int getInvoice_ID() {
        return Invoice_ID;
    }

    public String getDescription() {
        return Description;
    }


    public String getAddress() {
        return Address;
    }

    public String getCategory() {
        return category;
    }

    public String getProduct() {
        return product;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSupplier() {
        return Supplier;
    }

    public String getApprovedBy() {
        return ApprovedBy;
    }

    public void setInvoice_ID(int Invoice_ID) {
        this.Invoice_ID = Invoice_ID;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSupplier(String Supplier) {
        this.Supplier = Supplier;
    }

    public void setApprovedBy(String ApprovedBy) {
        this.ApprovedBy = ApprovedBy;
    }



    @Override
    public String toString() {
        return super.toString() + "Supplier: " + getSupplier(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void save(PurchaseInvoice p){
    try {
        Hibernate.DB_Connection.getSession().saveOrUpdate(p);
    Hibernate.DB_Connection.endSession();
    } catch (Exception e) {
        System.out.println(e);
    }
}

public static int getPurchaseInvoiceTranscationID(){
    ArrayList<PurchaseInvoice> p = null;int r;
    try {
     String hql = "FROM Modal.Invoice.PurchaseInvoice ";

    Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           p = (ArrayList<PurchaseInvoice>) query.getResultList();
     Hibernate.DB_Connection.endSession();
   

    } catch (Exception e) {
        System.out.println(e);
    }
    if(p.isEmpty()){
        r= 0;
    }else{
        r =  p.get(p.size()-1).getInvoice_ID();
    }
     return r;
}

    public static ArrayList<PurchaseInvoice> getAllPurchaseInvoices(){
         ArrayList<PurchaseInvoice> r;
           String hql = "FROM Modal.Invoice.PurchaseInvoice";
      Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           r = (ArrayList<PurchaseInvoice>) query.getResultList();
     Hibernate.DB_Connection.endSession();
return r;
}
  
               public static boolean checkDuplicates(Integer x){
        ArrayList<Integer> r;
          String hql = "Select Invoice_ID FROM Modal.Invoice.PurchaseInvoice";
     
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
    
               
     public static void Delete(PurchaseInvoice p){
    try {
        Hibernate.DB_Connection.getSession().delete(p);
    Hibernate.DB_Connection.endSession();
    } catch (Exception e) {
        System.out.println(e);
    }
}
     
     
        public static void update(PurchaseInvoice p){
    try {
        Hibernate.DB_Connection.getSession().update(p);
    Hibernate.DB_Connection.endSession();
    } catch (Exception e) {
        System.out.println(e);
    }
}
        
  
    
}
