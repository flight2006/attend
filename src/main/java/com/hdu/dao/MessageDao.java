package com.hdu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hdu.model.Message;

@Component("messageDao")
public class MessageDao extends BaseDao {
	public void addMessage(Message message) {
		this.writerSqlSession.insert("com.hdu.dao.MessageDao.addMessage", message);
	}
	
	public List<Message> findMessage(int start,int size) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("size", size);
		return this.readSqlSession.selectList("com.hdu.dao.MessageDao.selectMessage",map);
	}
	
}
