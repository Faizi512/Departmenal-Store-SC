package Modal.Chart_of_Accounts;

import static Modal.Chart_of_Accounts.Level_2.getLevel_2Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Query;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
 @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Level_1 {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	public int code1;
	public String name1;

    public Level_1() {
    }
       
        
        
	public Level_1(int code1, String name1) {
            this.setCode1(code1);
		this.setName1(name1);
	}

        
        
        //  Constructors
        
    public void setCode1(int code1) {
        
        if(code1 < 0){
            throw new IllegalArgumentException();
        }else{
          this.code1 = code1;  
        }   
    }

    public int getCode1() {
        return code1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName1() {
        return name1;
    }

       // toString method
    
    @Override
    public String toString() {
        return "Code1: " + getCode1() + " Name1:" + getName1();  //To change body of generated methods, choose Tools | Templates.
    }
	
      // Helper Methods
    
    public static ArrayList<Level_1> getLevel_1Data(){
         ArrayList<Level_1> r;
           String hql = "FROM Modal.Chart_of_Accounts.Level_1";
      //   SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	// Session s = sf.openSession();
       //  s.beginTransaction();
      Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           r = (ArrayList<Level_1>) query.getResultList();
     //    s.getTransaction().commit();
	//	s.close();
     
     Hibernate.DB_Connection.endSession();
        


//        for (int i = 0; i < r.size(); i++) {
//            System.out.println(r.get(i));
//        }
return r;
}
    
            public static ArrayList<String> getL1List(){
         ArrayList<Level_1> list= getLevel_1Data();
       ArrayList<String> s = new ArrayList<String>();
         for (int i = 0; i < list.size(); i++) {
             s.add(list.get(i).getCode1()+" ... "+ list.get(i).getName1());
        }
         removeDuplicates(s);
return s;
}
    
    public boolean checkDuplicates(Integer x){
        ArrayList<Integer> r;
          String hql = "Select code1 FROM Modal.Chart_of_Accounts.Level_1";
     
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
    
    public static ArrayList<String> removeDuplicates(ArrayList<String> a){
        
        for(int i = 0; i < a.size(); i++) {
         String t = a.get(i);
            
            for (int j = i+1; j < a.size(); j++) {
               String t1 =  a.get(j);
              //  System.out.println(i + t +"   "+ j + t1);
                if(t.matches(t1)){
                    //System.out.println("remove");
                    a.remove(j);
                    --j;
                }
               
            }
            
        }

        return a;
    }
    
        public boolean Delete(Integer code){
 
           
 String hql = "DELETE FROM Level_1 "  + 
             "WHERE code1 = :code1";
Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
query.setParameter("code1", code);
int result = query.executeUpdate();
// System.out.println("Rows affected: " + result);
     Hibernate.DB_Connection.endSession();
     
     return result > 0;

}
        
          public boolean Update(Level_1 l1){
              String hql = "UPDATE Level_1 set name1 = :name1 "  + 
             "WHERE code1 = :code1";
              
                 Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
              

query.setParameter("code1", l1.getCode1());
query.setParameter("name1", l1.getName1());
int result = query.executeUpdate();
            return result > 0;
//System.out.println("Rows affected: " + result);

          }
	
}