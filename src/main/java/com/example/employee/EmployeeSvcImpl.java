package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class EmployeeSvcImpl implements EmployeeSvc {
	
	@Autowired
	IEmployeeDao empDao;

	@Override
	public String getEmp(int id) {
		return empDao.getEmp(id);
	}

}
