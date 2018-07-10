package com.example.demo.repository;

import com.example.demo.model.Employee;

public interface EmployeeRepositoryCustom {
	public long getMaxEmpId();
    
    public Employee updateEmployee(Employee emp);
}
