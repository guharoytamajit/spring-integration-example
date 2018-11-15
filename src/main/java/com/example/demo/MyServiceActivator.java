package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.example.employee.EmployeeSvc;

@Component("serviceActivator")
public class MyServiceActivator {
	@Autowired
	EmployeeSvc employeeSvc;
	
//	@ServiceActivator(inputChannel="inputChannel",outputChannel="outputChannel")
	public String greet(String msg) {
		System.out.println(msg);
		String emp = employeeSvc.getEmp(1);
		System.out.println(emp);
		return "modified "+msg;
	}
	
	
//	public boolean filterMethod(String msg){
//		if(msg.contains("world")){
//			return false;
//		}
//		return true;
//	}
}
