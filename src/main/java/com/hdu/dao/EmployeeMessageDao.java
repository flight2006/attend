package com.hdu.dao;

import com.hdu.model.EmployeeMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Flight on 2015/7/21.
 */
@Component("employeeMessageDao")
public class EmployeeMessageDao extends BaseDao{
    public List<EmployeeMessage> findEmployeeMessageByEmployeeId(int employeeId,int limit){
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("employeeId",employeeId);
        map.put("limit",limit);
        return this.readSqlSession.selectList("com.hdu.dao.EmployeeMessageDao.selectEmployeeMessageByEmployeeId",map);
    }
    public int addEmployeeMessage(EmployeeMessage employeeMessage){
        return writerSqlSession.insert("com.hdu.dao.EmployeeMessageDao.addEmployeeMessage",employeeMessage);
    }
    public int deleteEmployeeMessageById(int id){
        return writerSqlSession.delete("com.hdu.dao.EmployeeMessageDao.deleteEmployeeMessageById",id);
    }
}
