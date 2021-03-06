package com.medical.DAO;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.medical.model.Appointment;
import com.medical.model.Prescription;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class AppDao {
	public List<Appointment> getAppointment(String id,String role) throws UnknownHostException{
		DB db = DataConnect.getdb();
		DBCollection table = db.getCollection("appointment");
		List<Appointment> listOfappointments = new ArrayList<Appointment>();
		if(role.equals("patient")){
		BasicDBObject  query=new BasicDBObject("pat_id",id).append("treatment", "pending");
		DBCursor cursor=table.find(query);
		while(cursor.hasNext()){
			BasicDBObject basicdbobject=(BasicDBObject)cursor.next();
			Appointment app = new Appointment();
			app.setApp_id(basicdbobject.getString("app_id"));
			app.setDate(basicdbobject.getDate("Date"));
			app.setDoc_id(basicdbobject.getString("Doc_id"));
			app.setPat_id(basicdbobject.getString("pat_id"));
			app.setSt_time(basicdbobject.getDate("St_time"));
			listOfappointments.add(app);
	}
		return listOfappointments;
	}
		else{
			BasicDBObject  query=new BasicDBObject("Doc_id",id).append("treatment", "pending");
			DBCursor cursor=table.find(query);
			while(cursor.hasNext()){
				BasicDBObject basicdbobject=(BasicDBObject)cursor.next();
				Appointment app = new Appointment();
				app.setApp_id(basicdbobject.getString("app_id"));
				app.setDate(basicdbobject.getDate("Date"));
				app.setDoc_id(basicdbobject.getString("Doc_id"));
				app.setPat_id(basicdbobject.getString("pat_id"));
				app.setSt_time(basicdbobject.getDate("St_time"));
				listOfappointments.add(app);
		}
			return listOfappointments;
			
		}
	}
	public void saveprescription(Prescription pris) throws UnknownHostException{
		DB db = DataConnect.getdb();
		DBCollection table = db.getCollection("appointment");
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("treatment", "done").append("medicine", pris.getMedicine()).append("next_date", pris.getNext_date()));
		BasicDBObject searchQuery = new BasicDBObject().append("app_id", pris.getApp_id());
		table.update(searchQuery, newDocument);
		
	}
	public void saveappointment(Appointment app) throws UnknownHostException{
		DB db = DataConnect.getdb();
		DBCollection table = db.getCollection("appointment");
		String app_id=app.getPat_id()+app.getDoc_id()+app.getDate().toString();
		System.out.println(app_id);
		BasicDBObject basicdbobject=new BasicDBObject("app_id",app_id).
													 append("Date",app.getDate() ).
				                                     append("pat_id", app.getPat_id()).
				                                     append("Doc_id",app.getDoc_id()).
				                                     append("St_time", app.getSt_time()).
				                                     append("treatment","pending");
				                                     
		table.insert(basicdbobject);
	}
	public List<Appointment> getMedicalHistory(String id) throws UnknownHostException{
		DB db = DataConnect.getdb();
		DBCollection table = db.getCollection("appointment");
		List<Appointment> listOfappointments = new ArrayList<Appointment>();
		BasicDBObject  query=new BasicDBObject("pat_id",id).append("treatment", "done");
		DBCursor cursor=table.find(query);
		while(cursor.hasNext()){
			BasicDBObject basicdbobject=(BasicDBObject)cursor.next();
			Appointment app = new Appointment();
			app.setMedicine(basicdbobject.getString("medicine"));
			app.setNext_date(basicdbobject.getDate("next_date"));
			app.setApp_id(basicdbobject.getString("app_id"));
			app.setDate(basicdbobject.getDate("Date"));
			app.setDoc_id(basicdbobject.getString("Doc_id"));
			app.setPat_id(basicdbobject.getString("pat_id"));
			app.setSt_time(basicdbobject.getDate("St_time"));
			listOfappointments.add(app);
		}
		return listOfappointments;
	}
	public List<Appointment> getMutuaAppointment(String pat_id,String doc_id) throws UnknownHostException{
		DB db = DataConnect.getdb();
		DBCollection table = db.getCollection("appointment");
		List<Appointment> listOfappointments = new ArrayList<Appointment>();
		BasicDBObject  query=new BasicDBObject("pat_id",pat_id).append("doc_id", doc_id).append("treatment", "done");
		DBCursor cursor=table.find(query);
		while(cursor.hasNext()){
			BasicDBObject basicdbobject=(BasicDBObject)cursor.next();
			Appointment app = new Appointment();
			app.setMedicine(basicdbobject.getString("medicine"));
			app.setNext_date(basicdbobject.getDate("next_date"));
			app.setApp_id(basicdbobject.getString("app_id"));
			app.setDate(basicdbobject.getDate("Date"));
			app.setDoc_id(basicdbobject.getString("Doc_id"));
			app.setPat_id(basicdbobject.getString("pat_id"));
			app.setSt_time(basicdbobject.getDate("SSt_time"));
			listOfappointments.add(app);
		}
		return listOfappointments;
	}
}
