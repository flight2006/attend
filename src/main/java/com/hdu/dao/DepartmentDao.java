package com.hdu.dao;

import com.hdu.model.Department;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Flight on 2015/7/20.
 */
@Component("departmentDao")
public class DepartmentDao extends  BaseDao {

    public List<Department> findAllDepartments(){
        return this.readSqlSession.selectList("com.hdu.dao.DepartmentDao.selectAllDepartments");
    }

    public List<Department> findDepartments(int start,int size,Department department){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("start",start);
        map.put("size",size);
        map.put("department",department);
        return this.readSqlSession.selectList("com.hdu.dao.DepartmentDao.selectDepartments",map);
    }

    public Department findDepartmentById(int id){
        return this.readSqlSession.selectOne("com.hdu.dao.DepartmentDao.selectDepartmentById",id);
    }
    public void addDepartment(Department department){
        writerSqlSession.insert("com.hdu.dao.DepartmentDao.addDepartment",department);
    }

    public void updateDepartmentEmployeeCount(int departmentId){
        writerSqlSession.update("com.hdu.dao.DepartmentDao.updateDepartmentEmployeeCount",departmentId);
    }
}
