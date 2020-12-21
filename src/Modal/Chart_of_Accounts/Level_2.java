package Modal.Chart_of_Accounts;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Query;
import org.hibernate.Session;

@Entity
public class Level_2 extends Level_1{

//	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public int code2;
	public String name2;
	public String l1l2;

    public Level_2() {
    }
	
        
        
	public Level_2(int code1,String name1,int code2, String name2) {
		super(code1,name1);
	this.setCode2(code2);
		this.setName2(name2);
		l1l2 = Integer.toString(code1) + "-" + Integer.toString(code2) ; 
	}

         public void setCode2(int code2) {
              if(code2 < 0){
            throw new IllegalArgumentException();
        }else{
          this.code2 = code2; 
        }   
        
    }
         
    public int getCode2() {
        return code2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName2() {
        return name2;
    }

    public String getL1l2() {
        return l1l2;
    }

    public void setL1l2(String l1l2) {
        this.l1l2 = l1l2;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + "Code2: " + getCode2() + " Name2:" + getName2(); //To change body of generated methods, choose Tools | Templates.
    }

         // Helper Methods
    
    public static ArrayList<Level_2> getLevel_2Data(){
         ArrayList<Level_2> r;
           String hql = "FROM Modal.Chart_of_Accounts.Level_2";
      //   SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	// Session s = sf.openSession();
       //  s.beginTransaction();
      Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           r = (ArrayList<Level_2>) query.getResultList();
     //    s.getTransaction().commit();
	//	s.close();
     
     Hibernate.DB_Connection.endSession();
        

//
//        for (int i = 0; i < r.size(); i++) {
//            System.out.println(r.get(i));
//        }
return r;
}
    
        public static ArrayList<String> getL2ComboList(){
         ArrayList<Level_2> list= getLevel_2Data();
       ArrayList<String> s = new ArrayList<String>();
         for (int i = 0; i < list.size(); i++) {
             s.add(list.get(i).getL1l2() +" ... "+ list.get(i).getName2());
        }
            
return removeDuplicates(s);
}
	
        @Override
    public boolean checkDuplicates(Integer x){
        ArrayList<Integer> r;
          String hql = "Select code2 FROM Modal.Chart_of_Accounts.Level_2";
     
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
    
            public boolean Delete(Integer code){
 
           
 String hql = "DELETE FROM Level_2 "  + 
             "WHERE code2 = :code2";
Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
query.setParameter("code2", code);
int result = query.executeUpdate();
// System.out.println("Rows affected: " + result);
     Hibernate.DB_Connection.endSession();
     
     return result > 0;

}
        
          public boolean Update(Level_2 l2){
              String hql = "UPDATE Level_2 set name2 = :name2 "  + 
             "WHERE code2 = :code2";
              
                 Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
              

query.setParameter("code2", l2.getCode2());
query.setParameter("name2", l2.getName2());
int result = query.executeUpdate();
            return result > 0;
//System.out.println("Rows affected: " + result);

          }
          
          public static Level_2 getL2Record(Integer i){
              String hql = "FROM Modal.Chart_of_Accounts.Level_2 "  + 
             "WHERE code2 = :code2";
Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
query.setParameter("code2", i);
ArrayList<Level_2> r = (ArrayList<Level_2>) query.getResultList();


     Hibernate.DB_Connection.endSession();
     
     return r.get(0);
          }
    
    
}
