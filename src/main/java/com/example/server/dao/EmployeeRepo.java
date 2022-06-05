package com.example.server.dao;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo  extends CrudRepository<Employee, Long> {
}
