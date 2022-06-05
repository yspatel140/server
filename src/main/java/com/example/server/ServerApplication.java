package com.example.server;

import com.example.server.dao.Department;
import com.example.server.dao.DepartmentRepo;
import com.example.server.dao.Employee;
import com.example.server.dao.EmployeeRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean(DepartmentRepo departmentRepo, EmployeeRepo employeeRepo) {
		return (args) -> {
			Department it = departmentRepo.save(new Department("IT"));
			Department admin = departmentRepo.save(new Department( "Admin"));
			Department hr = departmentRepo.save(new Department( "HR"));

			employeeRepo.save(new Employee("Yogesh", it));
			employeeRepo.save(new Employee("Raj", admin));
			employeeRepo.save(new Employee("Tom", hr));

			employeeRepo.findAll().forEach(System.out::println);
			departmentRepo.findAll().forEach(System.out::println);

		};
	}
}
