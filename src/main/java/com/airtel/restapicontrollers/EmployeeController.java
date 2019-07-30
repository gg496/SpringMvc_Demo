package com.airtel.restapicontrollers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.model.Employee;

import com.airtel.servicveImp.DaoImp;

@RestController
public class EmployeeController {
	
	@RequestMapping(value="/employee",method= RequestMethod.GET,produces="application/json")
	public ResponseEntity<ArrayList<Employee>> getEmployees(){
		DaoImp mongo=new DaoImp("ems_maven","ems");
		System.out.println("hey");
		ArrayList<Employee> employees=mongo.getCollection();
		if(employees==null) {
			return new ResponseEntity<ArrayList<Employee>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ArrayList<Employee>>(employees,HttpStatus.OK);
	}
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET,produces="application/json")
	 public ResponseEntity<Employee> getEmployee(@PathVariable("id") String id) {
		DaoImp mongo=new DaoImp("ems_maven","ems");
	  Employee employee = mongo.viewEmployee(id);
	  if (employee == null) {
	   return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	  }
	  return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	 }
	@RequestMapping(value="/employee",method=RequestMethod.POST,produces="application/json")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		DaoImp mongo=new DaoImp("ems_maven","ems");
		mongo.addEmployee(employee);
		if(employee==null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
	}
	@RequestMapping(value="/employee/delete/{id}",method=RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") String id){
		DaoImp mongo=new DaoImp("ems_maven","ems");
		
		  Employee employee = mongo.viewEmployee(id);
		
		if(employee==null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		mongo.deleteEmployee(id);
		return new ResponseEntity<Employee>(employee,HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value="/employee/{id}",method=RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id,@RequestBody Employee employee){
		DaoImp mongo=new DaoImp("ems_maven","ems");
		
		  Employee isEmployee = mongo.viewEmployee(id);
		  if (isEmployee == null) {   
			   return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		  }
		  else if(employee==null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		mongo.updateEmployee(id,employee);
		return new ResponseEntity<Employee>(employee,HttpStatus.NO_CONTENT);
	}
}
