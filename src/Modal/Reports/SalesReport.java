/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal.Reports;

import Modal.Invoice.SalesInvoice;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Abdul Muazzam
 */
public class SalesReport {
    
    public int Invoice_no;
    public LocalDate Date;
    public Double Gross;
    public Double Discount;
    public Double Amount;

    public SalesReport() {
    }

    
    
    
    //  Getters/Setters 
    


    public int getInvoice_no() {
        return Invoice_no;
    }

    public void setInvoice_no(int Invoice_no) {
        this.Invoice_no = Invoice_no;
    }

    public LocalDate getDate() {
        return Date;
    }

    public Double getGross() {
        return Gross;
    }

    public Double getDiscount() {
        return Discount;
    }

    public Double getAmount() {
        return Amount;
    }


    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public void setGross(Double Gross) {
        this.Gross = Gross;
    }

    public void setDiscount(Double Discount) {
        this.Discount = Discount;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }

    @Override
    public String toString() {
        return getInvoice_no() + " " + getAmount(); //To change body of generated methods, choose Tools | Templates.
    }
            
    
    
    
    
    //  Methods
 
    public static ObservableList<SalesReport> getReport(String Month,String Year){
        ArrayList<SalesInvoice> si = SalesInvoice.getAllSales();
        
//        System.out.println("Sales Invoice:");
//        for(SalesInvoice sr : si){
//        System.out.println(sr);
//    }  
        
      //  ArrayList<SalesReport> srl = new ArrayList<SalesReport>();
        
        ObservableList<SalesReport> sr = FXCollections.observableArrayList();
        
        for (int i = 0; i < si.size(); i++) {
            String date = si.get(i).getDate().toString();
            String[] dateSplitter = date.split("-");
//            System.out.println(dateSplitter[0]+"=="+Year);
//                System.out.println(dateSplitter[1]+"=="+Month);
            if(dateSplitter[0].contains(Year) && dateSplitter[1].contains(Month)){
                
            //    System.out.println("In Loop !!!");
                
                SalesReport srObj = new SalesReport();
                srObj.setInvoice_no(si.get(i).getSalesInvoiceID());
                srObj.setDate(si.get(i).getDate());
                srObj.setGross(si.get(i).getGross());
                srObj.setDiscount(si.get(i).getDiscountPercentAmount());
                srObj.setAmount(si.get(i).getNetBill());
                sr.add(srObj);
            }
            
            
        }
//         System.out.println("Sales Report:");
//        for(SalesReport s : sr){
//        System.out.println(s);
//    } 
        return sr;
    }
    
    
}
