package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom{
	@Autowired
    MongoTemplate mongoTemplate;
	
	@Override
	public long getMaxEmpId() {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "id"));
		query.limit(1);
		Employee maxObject = mongoTemplate.findOne(query, Employee.class);
		if (maxObject == null) {
			return 0L;
		}
			return maxObject.getId();
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		Query query = new Query(Criteria.where("empNo").is(emp.getEmpNo()));
        Update update = new Update();
        update.set("empName", emp.getEmpName());
        update.set("position", emp.getPosition());
 
        this.mongoTemplate.updateFirst(query, update, Employee.class);
 
        return emp;
	}

}
