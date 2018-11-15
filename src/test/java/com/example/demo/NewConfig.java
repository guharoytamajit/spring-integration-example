package com.example.demo;

import static org.mockito.Mockito.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.employee.EmployeeDao;
import com.example.employee.IEmployeeDao;

@Configuration
public class NewConfig {

	@Bean
	public IEmployeeDao dao(){
	 IEmployeeDao employeeDao = spy(IEmployeeDao.class);
//		IEmployeeDao employeeDao =new EmployeeDao();
		when(employeeDao.getEmp(1)).thenReturn("tamajit==================================");
//		doNothing().when(myList).add(isA(Integer.class), isA(String.class));
		return employeeDao;
	}
}
