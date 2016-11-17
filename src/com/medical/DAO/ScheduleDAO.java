package com.medical.DAO;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import com.medical.model.Schedule;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ScheduleDAO {
	public void saveschedule(Schedule schedule) throws UnknownHostException{
		DB db = DataConnect.getdb();
		DBCollection table = db.getCollection("schedule");
		BasicDBList mor_list = new BasicDBList();
		BasicDBList eve_list = new BasicDBList();
		mor_list.addAll(schedule.getMor_list());
		eve_list.addAll(schedule.getEve_list());
		BasicDBObject basicdbobject=new BasicDBObject("doc_id",schedule.getDoc_id()).
													 append("Date",schedule.getDate() ).
				                                     append("mor_list", mor_list).
				                                     append("eve_list",eve_list);				                                   
				                                     
		table.insert(basicdbobject);
}
	public List<Schedule> getschedule(String doc_id) throws UnknownHostException{
		DB db = DataConnect.getdb();
		DBCollection table = db.getCollection("schedule");
		List<Schedule> listOfschedules = new ArrayList<Schedule>();
		BasicDBObject  query=new BasicDBObject("doc_id",doc_id);
		DBCursor cursor=table.find(query);
		while(cursor.hasNext()){
			BasicDBObject basicdbobject=(BasicDBObject)cursor.next();
			Schedule schedule = new Schedule();
			schedule.setDoc_id(basicdbobject.getString("doc_id"));
			schedule.setDate(basicdbobject.getDate("Date"));
			BasicDBList list = (BasicDBList) basicdbobject.get("mor_list");
			BasicDBList list1 = (BasicDBList) basicdbobject.get("eve_list");
			List<Date> res = new ArrayList<Date>();
			for(Object el: list) {
			     res.add((Date) el);
			}
			schedule.setMor_list(res);
			List<Date> res1 = new ArrayList<Date>();
			for(Object el1: list1) {
			     res1.add((Date) el1);
			}
			schedule.setMor_list(res);
			schedule.setEve_list(res1);
			listOfschedules.add(schedule);
		}
		return listOfschedules;
	}
}
