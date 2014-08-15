package com.phoenixjcam.dashboard.employee.service;

import java.util.List;

import com.phoenixjcam.dashboard.employee.model.EmployeeModel;

public interface EmployeeService
{
	public void addEmployee(EmployeeModel employee);

	public void updateEmployee(EmployeeModel employee);

	public EmployeeModel getEmployee(int id);

	public void deleteEmployee(int id);
	
	public List<EmployeeModel> getEmployees(int offset, int pageSize);

	public List<EmployeeModel> getEmployees(int offset, int pageSize, String query);

	public long getEmployeesCount();
}
