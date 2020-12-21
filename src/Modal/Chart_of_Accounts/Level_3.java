package Modal.Chart_of_Accounts;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Query;
import org.hibernate.Session;

@Entity
public class Level_3 extends Level_2{

//	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public int code3;
	public String name3;
	public String l1l2l3;

    public Level_3() {
    }
	
        
        
	public Level_3(int code1,String name1,int code2, String name2,int code3, String name3) {
		super(code1,name1,code2,name2);
	this.setCode3(code3);
		this.setName3(name3);
		l1l2l3 = Integer.toString(code1) + "-" + Integer.toString(code2) + "-" + Integer.toString(code3) ;
	}

    public void setCode3(int code3) {
         if(code3 < 0){
            throw new IllegalArgumentException();
        }else{
         this.code3 = code3;
        }   
        
    }

    public int getCode3() {
        return code3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName3() {
        return name3;
    }

    public String getL1l2l3() {
        return l1l2l3;
    }

    public void setL1l2l3(String l1l2l3) {
        this.l1l2l3 = l1l2l3;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + "Code3: " + getCode3() + " Name3" + getName3(); //To change body of generated methods, choose Tools | Templates.
    }

	  public static ArrayList<Level_3> getLevel_3Data(){
         ArrayList<Level_3> r;
           String hql = "FROM Modal.Chart_of_Accounts.Level_3";
      //   SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	// Session s = sf.openSession();
       //  s.beginTransaction();
      Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
           r = (ArrayList<Level_3>) query.getResultList();
     //    s.getTransaction().commit();
	//	s.close();
     
     Hibernate.DB_Connection.endSession();
        


//        for (int i = 0; i < r.size(); i++) {
//            System.out.println(r.get(i));
//        }
return r;
}

          
          
                  public static ArrayList<String> getL3ComboList(){
         ArrayList<Level_3> list= getLevel_3Data();
       ArrayList<String> s = new ArrayList<String>();
         for (int i = 0; i < list.size(); i++) {
             s.add(list.get(i).getL1l2l3() +" ... "+ list.get(i).getName3());
        }
return removeDuplicates(s);
}
          
        @Override
              public boolean checkDuplicates(Integer x){
        ArrayList<Integer> r;
          String hql = "Select code3 FROM Modal.Chart_of_Accounts.Level_3";
     
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
 
           
 String hql = "DELETE FROM Level_3 "  + 
             "WHERE code3 = :code3";
Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
query.setParameter("code3", code);
int result = query.executeUpdate();
// System.out.println("Rows affected: " + result);
     Hibernate.DB_Connection.endSession();
     
     return result > 0;

}
        
          public boolean Update(Level_3 l3){
              String hql = "UPDATE Level_3 set name3 = :name3 "  + 
             "WHERE code3 = :code3";
              
                 Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
              

query.setParameter("code3", l3.getCode3());
query.setParameter("name3", l3.getName3());
int result = query.executeUpdate();
            return result > 0;
//System.out.println("Rows affected: " + result);

          }
          
                 public static Level_3 getL3Record(Integer i){
              String hql = "FROM Modal.Chart_of_Accounts.Level_3 "  + 
             "WHERE code3 = :code3";
Session s =  Hibernate.DB_Connection.getSession();
         Query query = s.createQuery(hql);
query.setParameter("code3", i);
ArrayList<Level_3> r = (ArrayList<Level_3>) query.getResultList();


     Hibernate.DB_Connection.endSession();
     
     return r.get(0);
          }
}