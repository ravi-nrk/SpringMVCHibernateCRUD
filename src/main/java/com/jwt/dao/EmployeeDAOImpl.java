package com.jwt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.exception.CustomException;
import com.jwt.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		return sessionFactory.getCurrentSession().createQuery("from Employee")
				.list();
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(
				Employee.class, employeeId);
		if (null != employee) {
			this.sessionFactory.getCurrentSession().delete(employee);
		}

	}

	public Employee getEmployee(int empid) {
		return (Employee) sessionFactory.getCurrentSession().get(
				Employee.class, empid);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
		return employee;
	}
	
	@Override
	public List<Employee> searchEmployees(String searchQuery){
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(Employee.class);
	        Criterion email = Restrictions.gt("email","P%");
	        Criterion name = Restrictions.like("name","P%");
	        LogicalExpression orExp = Restrictions.or(email,name);
	        crit.add(orExp);
	        return crit.list();
		} catch (Exception e) {
			// TODO: handle exception
			throw new CustomException("Try again later");
		}
	}

}