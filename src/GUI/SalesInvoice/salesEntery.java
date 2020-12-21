/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SalesInvoice;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author Abdul Muazzam
 */

@Entity
public class salesEntery implements Serializable{
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int SerailNumber=0;
    public int price=0,amount=0;
    public String Product,ProductQty="0";
    @Transient
    public int index;
    
        public salesEntery() {
        }
        
              

        public int getSerailNumber() {
            return SerailNumber;
        }

        public int getPrice() {
            return price;
        }

        public String getProductQty() {
            return ProductQty;
        }

        public int getAmount() {
            return amount;
        }

        public String getProduct() {
            return Product;
        }

        public void setSerailNumber(int SerailNumber) {
            this.SerailNumber = SerailNumber;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setProductQty(String ProductQty) {
            this.ProductQty = ProductQty;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setProduct(String Product) {
            this.Product = Product;
        }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
        
        

        @Override
        public String toString() {
            return "Product : " + getProduct() +" "+ "\nQuantity: " + getProductQty() + "\nRate : " + getPrice(); //To change body of generated methods, choose Tools | Templates.
        }


        
    }
