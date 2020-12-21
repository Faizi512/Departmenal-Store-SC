/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal.RegisterUser;

import java.util.ArrayList;
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
public class User {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Integer ID;
    protected String Name,Phone_No,Email,Address,City,POS_ID;
     protected String User_Name;
    protected String Password;
    protected String designation;


    public User() {
    }

    public User(String Name, String Phone_No, String Email, String Address, String City, String POS_ID, String User_Name, String Password, String designation) {
        this.Name = Name;
        this.Phone_No = Phone_No;
        this.Email = Email;
        this.Address = Address;
        this.City = City;
        this.POS_ID = POS_ID;
        this.User_Name = User_Name;
        this.Password = Password;
        this.designation = designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getPassword() {
        return Password;
    }

    public String getUser_Name() {
        return User_Name;
    }


    public void setPOS_ID(String POS_ID) {
        this.POS_ID = POS_ID;
    }

    public String getPOS_ID() {
        return POS_ID;
    }

        
    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getEmail() {
        return Email;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getPhone_No() {
        return Phone_No;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String City) {
        this.City = City;
    }


    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPhone_No(String Phone_No) {
        this.Phone_No = Phone_No;
    }

    //   Methods Save,Delete,Search
    
    @Override
    public String toString() {
        return "User ID: " + getID() +" Name: "+ getName() + " Designation: " + getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void Save(User user){
        Hibernate.DB_Connection.getSession().saveOrUpdate(user);
        Hibernate.DB_Connection.endSession();
    }
    
        public static void Update(User user){
        Hibernate.DB_Connection.getSession().saveOrUpdate(user);
        Hibernate.DB_Connection.endSession();
    }
        
//        public static void Update1(User user){
//            String hql = "UPDATE Modal.RegisterUser.User set Name = :name ,Phone_No = :phone ,"
//                    + "Email = :email ,Address = :address , = : ,POS_ID = :posid "  + 
//             "WHERE ID = :id";
//           Session session = Hibernate.DB_Connection.getSession();
//Query query = session.createQuery(hql);
//query.setParameter("name", user.getName());
//query.setParameter("phone", user.getPhone_No());
//query.setParameter("email", user.getEmail());
//query.setParameter("address", user.getAddress());
//query.setParameter("posid", user.getPOS_ID());
//query.setParameter("id", user.getID());
//int result = query.executeUpdate();
//System.out.println("Rows affected: " + result);
//        }
        
        
    
    public static int getUserRegisteredID(){
    ArrayList<User> p = null;int id =0;
    try {
     String hql = "FROM Modal.RegisterUser.User ";

    Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           p = (ArrayList<User>) query.getResultList();
     Hibernate.DB_Connection.endSession();
     id = p.get(p.size()-1).getID();

    } catch (Exception e) {
        System.out.println(e);
    }
   
     return id;
}
    
      public static ArrayList<String> getAllUsers(){
         ArrayList<User> r;
           String hql = "FROM Modal.RegisterUser.User";
      Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           r = (ArrayList<User>) query.getResultList();
     Hibernate.DB_Connection.endSession();
     
     ArrayList<String> temp = new ArrayList<String>();
          for (int i = 0; i < r.size(); i++) {
              temp.add(r.get(i).getID() +" ... "+ r.get(i).getName());
              
          }
     
     
return temp;
}
      
            public static ArrayList<User> getAllUsersRecord(){
         ArrayList<User> r;
           String hql = "FROM Modal.RegisterUser.User";
      Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           r = (ArrayList<User>) query.getResultList();
     Hibernate.DB_Connection.endSession();
     
       
return r;
}
   
      
              public static User getSingleUserRecord(Integer i){
              String hql = "FROM Modal.RegisterUser.User "  + 
             "WHERE id = :id";
Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
query.setParameter("id", i);
ArrayList<User> r = (ArrayList<User>) query.getResultList();


     Hibernate.DB_Connection.endSession();
     
     if(r.isEmpty()){
       return null;  
     }else
     
     return r.get(0);
          }
              
     public static boolean duplicationCheck(Integer n){
         boolean c = false;
          String hql = "FROM Modal.RegisterUser.User ";
Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
ArrayList<User> r = (ArrayList<User>) query.getResultList();

         for (int i = 0; i < r.size(); i++) {
             System.out.println("r:"+r.get(i)+" ... UserID:"+n);
            if(r.get(i).getID() == n){
                c = true;
                break;
            }
             
         }
     Hibernate.DB_Connection.endSession();
         
         System.out.println("check ==> " + c);
         return c;
     }
     
     
//                   public static User getSingleUserRecordThroughLogin(Integer i){
//              String hql = "FROM Modal.RegisterUser.User ";
//Session s =  Hibernate.DB_Connection.getSession();
//         Query query = s.createQuery(hql);
//ArrayList<User> r = (ArrayList<User>) query.getResultList();
//User u = null;
//                       for (int j = 0; j < r.size(); j++) {
//                          if(r.get(j).getL().getLogin_id() == i){
//                              u = r.get(j);
//                              break;
//                          }
//                           
//                       }
//
//     Hibernate.DB_Connection.endSession();
//     
//     
//     
//     return u;
//          }
              
}
