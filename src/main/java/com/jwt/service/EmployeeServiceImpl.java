package com.jwt.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.EmployeeDAO;
import com.jwt.exception.CustomException;
import com.jwt.model.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger logger = Logger
			.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public void addEmployee(Employee employee)  {
		logger.info("Adding employee");
		try {
		employeeDAO.addEmployee(employee);
		}catch (Exception e) {
			throw new CustomException("Unable to add Employee please try again later");
		}
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		logger.info("Loading employee data");
		try {
			return employeeDAO.getAllEmployees();
		}catch (Exception e) {
			logger.info("Exception occured while loading employees.");
			throw new CustomException("Unable to get employee data.please try again later");
		}
	}

	@Override
	@Transactional
	public void deleteEmployee(Integer employeeId) {
		if(employeeDAO.getEmployee(employeeId)==null) {
			throw new CustomException("No Employee Found with given id");
		}
		try {
			employeeDAO.deleteEmployee(employeeId);
		}catch (Exception e) {
			throw new CustomException("Unable to delete empployee.Please try again later");
		}
	}

	public Employee getEmployee(int empid) {
		try {
			return employeeDAO.getEmployee(empid);
		} catch (Exception e) {
			throw new CustomException("Unable to get employee.Please try again later");
		}
		
	}

	public Employee updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}
	
	@Override
	public List<Employee> searchEmployee(String param){
		return employeeDAO.searchEmployees(param);
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

}
