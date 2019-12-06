package com.example.test;

public class ResponseObject {

	String statusCode;
	double count;
	
	ResponseObject(String statusCode,double count){
		this.statusCode=statusCode;
		this.count= count;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	
	
}
