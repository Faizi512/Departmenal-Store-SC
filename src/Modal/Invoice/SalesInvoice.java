/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal.Invoice;

import GUI.SalesInvoice.salesEntery;
import Modal.ProductInventory.Product;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Abdul Muazzam
 */
@Entity
public class SalesInvoice{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int SalesInvoiceID = 1000;
    protected int UserCode;
    protected double DiscountPercentAmount,Gross,NetBill,paidAmount,DueChange;
    protected String UserName,POS_ID;
    public final static String UAN = "111-IMTIAZ(468429)",Gst_Number = "12-02-9999-124-64";
   // protected salesEntery se;
    
    @Basic
    private LocalDate date;
 
    @Basic
    private LocalTime time;
    
    public SalesInvoice() {
    }

    public SalesInvoice( Integer DiscountPercent, Integer UserCode, Double Gross, Double NetBill, Double DueChange, String UserName, String POS_ID, LocalDate date, LocalTime time) {
       
        this.DiscountPercentAmount = DiscountPercent;
        this.UserCode = UserCode;
        this.Gross = Gross;
        this.NetBill = NetBill;
        this.DueChange = DueChange;
        this.UserName = UserName;
        this.POS_ID = POS_ID;
        this.date = date;
        this.time = time;
    }
    
    
    @OneToMany(cascade=CascadeType.ALL)
    public Collection<salesEntery> SalesProducts = new ArrayList<salesEntery>();

    public void setSalesProducts(Collection<salesEntery> SalesProducts) {
        this.SalesProducts = SalesProducts;
    }

    public Collection<salesEntery> getSalesProducts() {
        return SalesProducts;
    }

    public void setDiscountPercentAmount(int DiscountPercentAmount) {
        this.DiscountPercentAmount = DiscountPercentAmount;
    }

    public void setDueChange(double DueChange) {
        this.DueChange = DueChange;
    }

    public void setGross(double Gross) {
        this.Gross = Gross;
    }

    public void setNetBill(double NetBill) {
        this.NetBill = NetBill;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public int getSalesInvoiceID() {
        return SalesInvoiceID;
    }



    public void setSalesInvoiceID(int SalesInvoiceID) {
        this.SalesInvoiceID = SalesInvoiceID;
    }


    public void setUserCode(int UserCode) {
        this.UserCode = UserCode;
    }
    

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public static String getGst_Number() {
        return Gst_Number;
    }

    public LocalTime getTime() {
        return time;
    }

    public static String getUAN() {
        return UAN;
    }


    public Double getGross() {
        return Gross;
    }

    public void setGross(Double Gross) {
        this.Gross = Gross;
    }

    public void setDiscountPercent(Integer DiscountPercent) {
        this.DiscountPercentAmount = DiscountPercent;
    }

   

    public void setNetBill(Double NetBill) {
        this.NetBill = NetBill;
    }

    public Double getNetBill() {
        return NetBill;
    }

    public void setUserCode(Integer UserCode) {
        this.UserCode = UserCode;
    }

    public Integer getUserCode() {
        return UserCode;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setPOS_ID(String POS_ID) {
        this.POS_ID = POS_ID;
    }

    public String getPOS_ID() {
        return POS_ID;
    }

    public void setDueChange(Double DueChange) {
        this.DueChange = DueChange;
    }

    public Double getDueChange() {
        return DueChange;
    }

    public void setDiscountPercentAmount(double DiscountPercentAmount) {
        this.DiscountPercentAmount = DiscountPercentAmount;
    }

    public double getDiscountPercentAmount() {
        return DiscountPercentAmount;
    }
    
    

    @Override
    public String toString() {
        return "%Discount: "+ getDiscountPercentAmount() +"Total Bill: "
                + getNetBill() + "Due Change: " + getDueChange(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static int getSalesTranscationID(){
    ArrayList<SalesInvoice> p = null;
    try {
     String hql = "FROM Modal.Invoice.SalesInvoice ";

    Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           p = (ArrayList<SalesInvoice>) query.getResultList();
     Hibernate.DB_Connection.endSession();
   

    } catch (Exception e) {
        System.out.println(e);
    }
    int r = 0;
    if(!p.isEmpty())
        return p.size();
    else
     return r;
}

    
    public static ArrayList<SalesInvoice> getAllSales(){
    ArrayList<SalesInvoice> p = null;
    try {
     String hql = "FROM Modal.Invoice.SalesInvoice";

    Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           p = (ArrayList<SalesInvoice>) query.getResultList();
     Hibernate.DB_Connection.endSession();
   

    } catch (Exception e) {
        System.out.println(e);
    }
   
     return p;
}
    
    
}

