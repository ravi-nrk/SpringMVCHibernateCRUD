package com.jwt.service;

import java.util.List;

import com.jwt.exception.CustomException;
import com.jwt.model.Employee;

public interface EmployeeService {
	
	public void addEmployee(Employee employee) throws CustomException;

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Integer employeeId);

	public Employee getEmployee(int employeeid);

	public Employee updateEmployee(Employee employee);

	List<Employee> searchEmployee(String param);
}
