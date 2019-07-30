package com.airtel.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
	String Ename;
	String email;
	String id;
	@JsonCreator
	 public Employee(@JsonProperty("id") String id, @JsonProperty("Ename") String Ename,
	   @JsonProperty("email") String email) {
	  this.id=id;
	  this.Ename=Ename;
	  this.email=email;
	  
	 }
	
	public String getEname() {
		return Ename;
	}
	public void setEname(String ename) {
		Ename = ename;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Employee [Ename=" + Ename + ", email=" + email + ", id=" + id + "]";
	}
	
}
