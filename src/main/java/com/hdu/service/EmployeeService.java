package com.hdu.service;

import java.util.List;

import javax.annotation.Resource;

import com.hdu.dao.DepartmentDao;
import com.hdu.dao.EmployeeDao;
import com.hdu.dao.EmployeeMessageDao;
import com.hdu.model.Department;
import com.hdu.model.Employee;
import com.hdu.model.EmployeeMessage;
import org.springframework.stereotype.Service;


@Service("employeeService")
public class EmployeeService {

	@Resource(name="employeeDao")
	private EmployeeDao employeeDao;
	
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	
	@Resource(name="employeeMessageDao")
	private EmployeeMessageDao employeeMessageDao;
	

	
	/**
	 * 将数据库中employee数据分页查出
	 * @param start 其实数据条数
	 * @param size  展示数据每页的大小
	 */
	public List<Employee> listemployee(int start,int size,Employee employee){
		return employeeDao.findEmployee(start, size, employee);
	}
	 
	/**
	 * 根据员工编号查找对应的员工
	 * @param employeeid 员工编号
	 * @return 员工数据
	 */
	public Employee findemployeeById(int employeeid){
		return employeeDao.findEmployeeById(employeeid);
	}
	
	/**
	 * 添加主管给员工的微信留言到数据库
	 * @param employeeMessage
	 */
	public void addemployeeMessage(EmployeeMessage employeeMessage){
		employeeMessageDao.addEmployeeMessage(employeeMessage);
	}
	
	/**
	 * 根据id删除主管给员工的微信留言
	 * @param id
	 */
	public void deleteemployeeMessageById(int id){
		employeeMessageDao.deleteEmployeeMessageById(id);
	}
	
	/**
	 * 将数据库中department数据全部查出
	 */
	public List<Department> findAlldepartment(){
		return departmentDao.findAllDepartments();
	}
	
	/**
	 * 根据员工id查询对应员工的留言记录
	 * @param employeeid 员工id
	 * @param limit  展示数据每页的大小
	 */
	public List<EmployeeMessage> listMessageByemployeeId(int employeeid,int limit){
		return employeeMessageDao.findEmployeeMessageByEmployeeId(employeeid, limit);
	}

	/**
	 * 微信号关联员工帐号
	 */
	public int insertWeixinId(String weixinId, int empId){
		return employeeDao.insertWeixinId(weixinId,empId);
	}
}
