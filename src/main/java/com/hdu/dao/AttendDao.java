package com.hdu.dao;

import com.hdu.model.Attend;
import com.hdu.util.DateUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Flight on 2015/7/21.
 */
@Component("attendDao")
public class AttendDao extends BaseDao{
    public List<Attend> findAttendByEmployeeId(int employeeId,int limit){
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("employeeId",employeeId);
        map.put("limit",limit);
        List<Attend> attends = readSqlSession.selectList("com.hdu.dao.AttendDao.findAttendByEmployeeId",map);
        int length = attends.size();
        for (int i = 0;i< length;i++){
            Attend attend1 = attends.get(i);
            Timestamp punchTime = new DateUtils().converIntoNormal(attend1.getPunchtime());
            attend1.setPunchtime(punchTime);
            attends.set(i, attend1);
        }
        return attends;
    }

    public List<Attend> findAttend(int start,int size,Attend attend){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("start", start);
        map.put("size", size);
        map.put("attend", attend);
        List<Attend> attends = this.readSqlSession.selectList("com.hdu.dao.AttendDao.selectAttend",map);
        int length = attends.size();
        for (int i = 0;i< length;i++){
            Attend attend1 = attends.get(i);
            Timestamp  punchTime = new DateUtils().converIntoNormal(attend1.getPunchtime());
            attend1.setPunchtime(punchTime);
            attends.set(i, attend1);
        }
        return attends;
    }


    public Attend findAttendById(int id) {
        Attend attend = readSqlSession.selectOne("com.hdu.dao.AttendDao.findAttendById",id);
        Timestamp  punchTime = new DateUtils().converIntoNormal(attend.getPunchtime());
        attend.setPunchtime(punchTime);
        return attend;
    }
}
