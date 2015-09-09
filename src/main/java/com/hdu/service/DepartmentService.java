package com.hdu.service;

import java.util.List;

import javax.annotation.Resource;

import com.hdu.dao.DepartmentDao;
import com.hdu.dao.DepartmentNewsDao;
import com.hdu.dao.EmployeeDao;
import com.hdu.model.Department;
import com.hdu.model.DepartmentNews;
import com.hdu.model.Employee;
import org.springframework.stereotype.Service;

@Service("departmentService")
public class DepartmentService {

	@Resource(name="employeeDao")
	private EmployeeDao employeeDao;
	
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	
	@Resource(name="departmentNewsDao")
	private DepartmentNewsDao departmentNewsDao;
	
	/**
	 * 将数据库中department数据分页查出
	 * @param start 其实数据条数
	 * @param size  展示数据每页的大小
	 */
	public List<Department> listdepartment(int start,int size,Department department){
		return departmentDao.findDepartments(start, size, department);
	}
	 
	public void deletedepartmentNewsById(int id){
		departmentNewsDao.deleteDepartmentNewsById(id);
	}
	
	/**
	 * 添加部门动态到数据库
	 * @param departmentNews
	 */
	public void adddepartmentNews(DepartmentNews departmentNews){
		departmentNewsDao.addDepartmentNews(departmentNews);
	}
	
	/**
	 * 获取指定部门的部门动态(钱1000个)
	 * @param departmentId
	 * @return
	 */
	public List<DepartmentNews> finddepartmentNewsByDepartmentId(int departmentId){
		return departmentNewsDao.findDepartmentNewsByDepartmentId(departmentId, 1000);
	}
	
	/**
	 * 添加部门到数据库中
	 * @param department 班级对象
	 */
	public void addDepartment(Department department){
		departmentDao.addDepartment(department);
	}
	
	/**
	 * 根据id查找对应的department对象
	 * @param id 部门编号
	 * @return
	 */
	public Department finddepartmentById(int id){
		return departmentDao.findDepartmentById(id);
	}
	
	/**
	 * 根据员工编号查找对应的员工
	 * @param employeeId 员工编号
	 * @return 员工数据
	 */
	public Employee findEmployeeById(int employeeId){
		return employeeDao.findEmployeeById(employeeId);
	}
	
	/**
	 * 根据部门id查找对应部门所有员工
	 * @param departmentId 部门id
	 * @return
	 */
	public List<Employee> findEmployeeBydepartmentId(int departmentId){
		return employeeDao.selectEmployeeByDepartmentId(departmentId);
	}
	
	/**
	 * 添加员工信息至数据库中
	 * @param employee 员工对象
	 */
	public void addStudent(Employee employee){
		employeeDao.addEmployee(employee);
	}
	
	
	/**
	 * 删除数据库中对应id的员工信息
	 * @param employeeId 员工对象
	 */
	public void deleteEmployeeById(int employeeId){
		employeeDao.deleteEmployeeById(employeeId);
	}
	
	/**
	 * 自动更新指定部门的员工数量
	 * @param departmentId 部门id
	 */
	public void updateDepartmentEmployeeCount(int departmentId){
		departmentDao.updateDepartmentEmployeeCount(departmentId);
	}
	
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}
	
}
