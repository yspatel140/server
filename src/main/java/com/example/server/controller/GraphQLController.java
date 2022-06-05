package com.example.server.controller;

import com.example.server.dao.Department;
import com.example.server.dao.DepartmentRepo;
import com.example.server.dao.Employee;
import com.example.server.dao.EmployeeRepo;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class GraphQLController {

    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;

    public GraphQLController(DepartmentRepo departmentRepo, EmployeeRepo employeeRepo) {
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
    }

    @QueryMapping
    Iterable<Department> departments(){
        return departmentRepo.findAll();
    }

    @QueryMapping
    Optional<Employee> employeeById(@Argument Long id){
        return employeeRepo.findById(id);
    }

    @MutationMapping
    Employee addEmployee(@Argument EmployeeInput employee){
        Optional<Department> department = departmentRepo.findById(employee.departmentId());
        return employeeRepo.save(new Employee(employee.name(), department.get()));
    }
    record EmployeeInput(String name, Long departmentId) {
    }

}

/*

query {
  departments {
    id
    name
    employees {
      id
      name
    }
  }
  employeeById(id : 4){
    id
    name
    department{
      id
      name
    }
  }
}


mutation {
  addEmployee(employee : {
    name: "Dom",
    departmentId: 1
  }){
    id
  }
}

 */