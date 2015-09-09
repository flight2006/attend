package com.hdu.service;

import com.hdu.dao.AttendDao;
import com.hdu.dao.DepartmentDao;
import com.hdu.dao.EmployeeDao;
import com.hdu.model.Attend;
import com.hdu.model.Department;
import com.hdu.model.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;

import java.util.List;

/**
 * Created by Flight on 2015/7/21.
 */
@Service("attendService")
public class AttendService {

    @Resource(name = "attendDao")
    private AttendDao attendDao;

    @Resource(name = "departmentDao")
    private DepartmentDao departmentDao;

    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;


    public List<Attend> findAttendByEmployeeId(int empId){
        return attendDao.findAttendByEmployeeId(empId,1000);
    }
    public List<Attend> findAttendByDepartmentId(int start,int size,Attend attend) throws ParseException {
        return attendDao.findAttend(start,size,attend);
    }

    /**
     * 将数据库中department数据全部查出
     */
    public List<Department> findAllDepartment(){
        return departmentDao.findAllDepartments();
    }

    public List<Employee> findEmployeeByDepartmentId(int depId) {
        return employeeDao.selectEmployeeByDepartmentId(depId);
    }

    public Attend findAttendById(int id){
        return attendDao.findAttendById(id);
    }

}
