package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryCustom;

@Repository
public class EmployeeDAO {
	 
	@Autowired
	private EmployeeRepositoryCustom employeeRepositoryCustom;
	 
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeDAO() {
	}
 
//    private void initEmps() {
//        Employee emp1 = new Employee("E01", "Smith", "Clerk");
//        Employee emp2 = new Employee("E02", "Allen", "Salesman");
//        Employee emp3 = new Employee("E03", "Jones", "Manager");
//        Employee emp4 = new Employee("E04", "Catana", "Salesman");
//        
//        empList.add(emp1);
//        empList.add(emp2);
//        empList.add(emp3);
//        empList.add(emp4);
//    }
 
    public Employee getEmployee(String empNo) {
        return this.employeeRepository.findByEmpNo(empNo);
    }
 
    public Employee addEmployee(Employee emp) {
    	Employee employee = new Employee();
    	 
        long id = this.employeeRepositoryCustom.getMaxEmpId() + 1;        
 
        employee.setId(id);
        employee.setEmpNo(emp.getEmpNo());
        employee.setEmpName(emp.getEmpName());
        employee.setPosition(emp.getPosition());
        this.employeeRepository.insert(employee);
 
        return employee;
    }
 
    public Employee updateEmployee(Employee emp) {
    	Employee employee = this.employeeRepository.findByEmpNo(emp.getEmpNo());
    	employee.setEmpName(emp.getEmpName());
    	employee.setPosition(emp.getPosition());
    	this.employeeRepositoryCustom.updateEmployee(employee);
        return employee;
    }
 
    public void deleteEmployee(String empNo) {
    	this.employeeRepository.delete(this.employeeRepository.findByEmpNo(empNo));
    }
 
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }
    
    public List<Employee> findByName(String empName){
    	List<Employee> temp = new ArrayList<Employee>();
    	temp = this.employeeRepository.findByEmpNameLike(empName);
        return temp;
    }
}
