package com.example.employee;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao implements IEmployeeDao {

	@Override
	public String getEmp(int id) {
		return new Employee(id, "").toString();
	}

}
