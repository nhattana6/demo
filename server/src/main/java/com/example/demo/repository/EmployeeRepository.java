package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Long>{
	@Query(value = "{'empName': {$regex : ?0, $options: 'i'}}")
	List<Employee> findByEmpNameLike(String empName);
	
	Employee findByEmpNo(String empNo);
}
