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
public class PharmacistsDetails {
    
    
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty gender;
    private final StringProperty address;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty ShiftTime;
    private final StringProperty salary;
    
    public PharmacistsDetails(String id , String name , String gender , String address , String email , String phone,String ShiftTime,String salary){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.ShiftTime = new SimpleStringProperty(ShiftTime);
        this.salary = new SimpleStringProperty(salary);
    }
    
    
    
    public void setId(String value){
        id.set(value);
    }
    
    public String getId(){
        return id.get();
    }
    
     public void setName(String value){
        name.set(value);
    }
    
    public String getName(){
        return name.get();
    }
    
     public void setGender(String value){
        gender.set(value);
    }
    
    public String getGender(){
        return gender.get();
    }
    
     public void setAddress(String value){
        address.set(value);
    }
    
    public String getAddress(){
        return address.get();
    }
    
     public void setEmail(String value){
        email.set(value);
    }
    
    public String getEmail(){
        return email.get();
    }
    
     public void setPhone(String value){
        phone.set(value);
    }
    
    public String getPhone(){
        return phone.get();
    }
         public void setSalary(String value){
        salary.set(value);
    }
    
    public String getSalary(){
        return salary.get();
    }
         public void setShiftTime(String value){
        ShiftTime.set(value);
    }
    
    public String getShiftTime(){
        return ShiftTime.get();
    }
    
   public StringProperty idProperty(){return id;}
   public StringProperty nameProperty(){return name;}
   public StringProperty genderProperty(){return gender;}
   public StringProperty AddressProperty(){return address;}
   public StringProperty emailProperty(){return email;}
   public StringProperty phoneProperty(){return phone;}
   public StringProperty ShiftTimeProperty(){return ShiftTime;}
   public StringProperty salaryProperty(){return salary;}
   
}