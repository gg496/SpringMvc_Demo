package com.airtel.servicveImp;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import com.airtel.model.Employee;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


public class DaoImp {
	private MongoClient client=null;
	private MongoDatabase db=null;
	private MongoCollection collection=null;
  public DaoImp( String db,String collection) {
	  this.client=new MongoClient("localhost",27017);
	  this.db=this.client.getDatabase(db);
	  this.collection=this.db.getCollection(collection);
  }
  public ArrayList<Employee> getCollection(){
	  System.out.println("hi");
	  ArrayList<Employee> employees=new ArrayList<Employee>();
	 
	  FindIterable<Document> iterDocs = this.collection.find();
	  	Iterator iterator=iterDocs.iterator();
	  	while(iterator.hasNext()) {
	  		//System.out.println(iterator.next());
	  		Document doc=(Document)iterator.next();
	  		//System.out.println(doc.toString());
	  		 Employee employee=new Employee(doc.get("_id").toString(),doc.get("name").toString(),doc.get("email").toString());
	  		 employees.add(employee);
	  	}
	  return employees;
  }
  public void addEmployee(Employee employee) {
	  Document document =new Document("name",employee.getEname())
				.append("email", employee.getEmail())
		.append("_id",employee.getId());
		collection.insertOne(document);
  }
  public Employee viewEmployee(String id) {
		Document document=null;
		Employee employee=null;
		  FindIterable<Document> iterDocs=collection.find(Filters.eq("_id", id));
		     Iterator<Document> iterator=iterDocs.iterator();
		     while(iterator.hasNext()) {
		    	 document=(iterator.next());
		    	break;
		      }
		     employee=new Employee(document.get("_id").toString(),document.get("name").toString(),document.get("email").toString());
		     return employee;
		
	}
  public void deleteEmployee(String id) {
	  try {
		  collection.deleteOne(Filters.eq("_id", id));
	  }catch(Exception ex){
		  System.out.println(ex.getMessage());
	  }
		
		
	}
public void updateEmployee(String id,Employee employee) {
	System.out.println(id);
	Bson filter = null;
  Bson query = null;
filter = eq("_id", id);
query = combine(set("name", employee.getEname()),set("email", employee.getEmail()));
collection.updateOne(filter, query);
	
	}
}
