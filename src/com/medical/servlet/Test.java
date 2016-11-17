package com.medical.servlet;

import java.util.List;
import java.util.Set;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.medical.DAO.AppDao;
import com.medical.DAO.ScheduleDAO;
import com.medical.model.Appointment;
import com.medical.model.Prescription;
import com.medical.model.Schedule;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Test {
	public static void main(String args[]) throws UnknownHostException{
		 try { 		
			MongoClientURI uri  = new MongoClientURI("mongodb://admin:admin@ds063946.mlab.com:63946/ma");
        MongoClient client = new MongoClient(uri);
        DB db = client.getDB(uri.getDatabase());
        DBCollection a=db.getCollection("patient");
        DBCursor b =a.find();
        while(b.hasNext()){
        	System.out.print(b.next());
        }
              }  
         catch (Exception ex) {
                              System.out.println("Database.getConnection() Error -->"
                              + ex.getMessage());
                              }
	
	}
	/*
		Date d1 = new Date(2016, 9, 23, 8, 0, 0);
		Date d2 = new Date(2016, 9, 23, 11, 0, 0);
		Date d3 = new Date(2016, 9, 23, 17, 0, 0);
		Date d4 = new Date(2016, 9, 23, 22, 0, 0);
		Date d5 = new Date(2016, 9, 23);
		Schedule s =new Schedule(d1,d2,d3,d4,d5,"rohan");
		ScheduleDAO sd= new ScheduleDAO();
		sd.saveschedule(s);
		
		ScheduleDAO sd1= new ScheduleDAO();
		List<Schedule> a= sd1.getschedule("rohan");
		
       java.util.Iterator<Schedule> it=a.iterator();
       for (Schedule v : a){
    	      System.out.print(v.getDoc_id());
    	      System.out.print(v.getEve_list());
       }
	      System.out.println();
	      */
}

