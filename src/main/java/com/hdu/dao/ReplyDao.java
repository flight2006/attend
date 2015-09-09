package com.hdu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hdu.model.Article;
import com.hdu.model.Reply;

@Component("replyDao")
public class ReplyDao extends BaseDao {

	public void addReply(Reply reply) {
		this.writerSqlSession.insert("com.hdu.dao.ReplyDao.addReply", reply);
	}
	
	public void addArticle(Article article){
		this.writerSqlSession.insert("com.hdu.dao.ReplyDao.addArticle", article);
	}
	
	public List<Reply> findReply(int start,int size) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("size", size);
		return this.readSqlSession.selectList("com.hdu.dao.ReplyDao.selectReply",map);
	}
	
}
