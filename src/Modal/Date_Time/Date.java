/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal.Date_Time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Abdul Muazzam
 */
public class Date {
   
    
    protected int day;
    protected int month;
    protected int year;

    public Date() {
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return  getDay() + "/" + getMonth() +"/"+ getYear(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void setDate(){
            Calendar calendarDate = Calendar.getInstance();
    calendarDate.getTime();
    }
    
    
   public static String getCurrentDate(){
       LocalDate localDate = LocalDate.now();//For reference
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
String formattedString = localDate.format(formatter);
return localDate.getDayOfMonth()+"/"+localDate.getMonthValue()+"/"+localDate.getYear();
   } 
    
}
