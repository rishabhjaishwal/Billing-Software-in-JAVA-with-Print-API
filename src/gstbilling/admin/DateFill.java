/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gstbilling.admin;

import java.util.Date;

/**
 *
 * @author Rishabh
 */
public class DateFill {
    private static String date1,month,year;

    public String getDate1() {
       
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    public void fill(){
                Date dd=new Date();
                String arr[]=new String[5];
                arr=dd.toGMTString().split("\\s");
               
                String d1=arr[0];
                String y1=arr[2];
                DateFill df=new DateFill();
                df.setDate1(d1);
                df.setYear(y1);
                String mon=String.valueOf(dd.getMonth()+1);
                df.setMonth(mon);
    }
    
    
}
