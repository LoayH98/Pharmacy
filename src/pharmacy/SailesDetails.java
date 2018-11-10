/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;


import java.sql.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author lenovo Y700 ISK
 */
public class SailesDetails {
    
    
    private final StringProperty saileID;
    private final StringProperty invntoryID;
    private final StringProperty name;
    private final StringProperty price;
    private final StringProperty quantity;
    private final StringProperty date;
    private final StringProperty TypeOfSale;
    private final StringProperty discount;
    
    public SailesDetails(String saileID , String invntoryID , String name , String price , String quantity , String Date,String typeOfSale,String discount){
        this.saileID = new SimpleStringProperty(saileID);
        this.invntoryID = new SimpleStringProperty(invntoryID);
        this.price = new SimpleStringProperty(price);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleStringProperty(quantity);
        this.date = new SimpleStringProperty(Date);
        this.TypeOfSale = new SimpleStringProperty(typeOfSale);
        this.discount = new SimpleStringProperty(discount);
    
    }
    
    
    
    public void setSaileID(String value){
        saileID.set(value);
    }
    
    public String getSaileID(){
        return saileID.get();
    }
    
     public void setName(String value){
        name.set(value);
    }
    
    public String getName(){
        return name.get();
    }
    
     public void setInvntoryID(String value){
        invntoryID.set(value);
    }
    
    public String getInvntoryID(){
        return invntoryID.get();
    }
    
     public void setPrice(String value){
        price.set(value);
    }
    
    public String getPrice(){
        return price.get();
    }
    
     public void setQuantity(String value){
       quantity.set(value);
    }
    
    public String getQuantity(){
        return quantity.get();
    }
    
     public void setDate(String value){
        date.set(value);
    }
    
    public String getDate(){
        return date.get();
    }
     public void setTypeOfSale(String value){
        TypeOfSale.set(value);
    }
    
    public String getTypeOfSale(){
        return TypeOfSale.get();
    }
         public void setDiscount(String value){
        discount.set(value);
    }
    
    public String getDiscount(){
        return discount.get();
    }
    
   public StringProperty SaileIDProperty(){return saileID;}
   public StringProperty nameProperty(){return name;}
   public StringProperty InvntoryIDProperty(){return invntoryID;}
   public StringProperty quantityProperty(){return quantity;}
   public StringProperty priceProperty(){return price;}
   public StringProperty dateProperty(){return date;}
   public StringProperty typeOfSaleProperty(){return TypeOfSale;}
   public StringProperty discountProperty(){return discount;}

   
}