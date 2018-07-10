package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;;

@RestController
public class MainRESTController {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@RequestMapping(value = "/api", method = RequestMethod.GET ,produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/employee/{empNo}", //
            		method = RequestMethod.GET, //
            		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Employee getEmployee(@PathVariable("empNo") String empNo) {
        return employeeDAO.getEmployee(empNo);
    }

    @RequestMapping(value = "/employee", //
            		method = RequestMethod.POST, //
            		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Employee addEmployee(@RequestBody Employee emp) {
 
        //System.out.println("Creating employee: " + emp.getEmpNo());
 
        return employeeDAO.addEmployee(emp);
    }
    
    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Employee updateEmployee(@RequestBody Employee emp) {
 
        //System.out.println("Updating employee: " + emp.getEmpNo());
 
        return employeeDAO.updateEmployee(emp);
    }
    
    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public void deleteEmployee(@PathVariable("empNo") String empNo) {
 
        //System.out.println("Deleting employee: " + empNo);
 
        employeeDAO.deleteEmployee(empNo);
    }
    
    @RequestMapping(value = "/employee/find/{empName}", //
    		method = RequestMethod.GET, //
    		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Employee>> findByName(@PathVariable("empName") String empName) {
    	
    	List<Employee> list = employeeDAO.findByName(empName);
    	return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

}
