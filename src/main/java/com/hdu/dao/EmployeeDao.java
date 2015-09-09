package com.hdu.dao;

import com.hdu.model.Employee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Flight on 2015/7/21.
 */
@Component("employeeDao")
public class EmployeeDao extends BaseDao {

    public Employee findEmployeeById(int id){
        return this.readSqlSession.selectOne("com.hdu.dao.EmployeeDao.selectEmployeeById",id);
    }

    /**
     * 通过姓名查找
     * @param start
     * @param size
     * @param employee
     * @return
     */
    public List<Employee> findEmployee(int start,int size,Employee employee){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("start",start);
        map.put("size",size);
        map.put("employee",employee);
        return readSqlSession.selectList("com.hdu.dao.EmployeeDao.findEmployee",map);
    }

    public List<Employee> selectEmployeeByDepartmentId(int department_id){
        return readSqlSession.selectList("com.hdu.dao.EmployeeDao.selectEmployeeByDepartmentId",department_id);
    }

    public int addEmployee(Employee employee){
        return writerSqlSession.insert("com.hdu.dao.EmployeeDao.addEmployee",employee);
    }
    public int deleteEmployeeById(int employeeId){
        return writerSqlSession.delete("com.hdu.dao.EmployeeDao.deleteEmployeeById",employeeId);
    }
    public int updateEmployee(Employee employee){
        return writerSqlSession.update("com.hdu.dao.EmployeeDao.updateEmployee",employee);
    }

    public int insertWeixinId(String weixinId,int empId){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("weixinId",weixinId);
        map.put("empId",empId);
        return writerSqlSession.insert("com.hdu.dao.EmployeeDao.insertWeixinId",map);
    }

}
