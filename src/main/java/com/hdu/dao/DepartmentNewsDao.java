package com.hdu.dao;

import com.hdu.model.DepartmentNews;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Flight on 2015/7/20.
 */
@Component("departmentNewsDao")
public class DepartmentNewsDao extends BaseDao {
    public List<DepartmentNews> findDepartmentNewsByDepartmentId(int departmentId,int limit){
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("departmentId",departmentId);
        map.put("limit",limit);
        return this.readSqlSession.selectList("com.hdu.dao.DepartmentNewsDao.selectDepartmentNewsByDepartmentId",map);
    }
    public void addDepartmentNews(DepartmentNews departmentNews){
        writerSqlSession.insert("com.hdu.dao.DepartmentNewsDao.addDepartmentNews",departmentNews);
    }
    public void deleteDepartmentNewsById(int id){
        writerSqlSession.delete("com.hdu.dao.DepartmentNewsDao.deleteDepartmentNewsById",id);
    }
}
