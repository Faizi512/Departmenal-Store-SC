/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal.Date_Time;

/**
 *
 * @author Abdul Muazzam
 */
public class Time {

    protected int hour;
    protected int minute;
    protected int seconds;
    
    public Time() {
    }

    public Time(int hour, int minute, int seconds) {
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return getHour() +":"+ getMinute() +":"+ getSeconds(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
