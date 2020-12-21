/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal.Invoice;

import Modal.Date_Time.Date;
import Modal.Date_Time.Time;
import Modal.ProductInventory.Product;
import java.util.ArrayList;

/**
 *
 * @author Abdul Muazzam
 */
public abstract class Invoice {
 
   protected int Invoice_ID;
   protected String Description,City,Address;
   protected ArrayList<Product> productList = new ArrayList<Product>();
   protected Date date;
   protected Time time;
   
   public Invoice() {
    }

    public Invoice(int Invoice_ID, String Description, String City, String Address, Date date, Time time) {
        this.Invoice_ID = Invoice_ID;
        this.Description = Description;
        this.City = City;
        this.Address = Address;
        this.date = date;
        this.time = time;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setInvoice_ID(int Invoice_ID) {
        this.Invoice_ID = Invoice_ID;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return Description;
    }

    public int getInvoice_ID() {
        return Invoice_ID;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public Time getTime() {
        return time;
    }
    
    public void PrintProducts(){
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(i+1 +") "+productList.get(i));  
        }
    }

    @Override
    public String toString() {
        
        
        System.out.println("Invoice ID" + getInvoice_ID() +"\n Products: \n");
        PrintProducts();
        return ""; //To change body of generated methods, choose Tools | Templates.
    }
   
   
    
}
