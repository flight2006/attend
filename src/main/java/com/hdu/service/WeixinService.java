package com.hdu.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import com.hdu.dao.*;
import com.hdu.model.*;
import org.springframework.stereotype.Service;



@Service("weixinService")
public class WeixinService {

	@Resource(name="messageDao")
	private MessageDao messageDao;
	
	@Resource(name="replyDao")
	private ReplyDao replyDao;
	
	@Resource(name="attendDao")
	private AttendDao attendDao;
	
	@Resource(name="employeeDao")
	private EmployeeDao employeeDao;
	
	@Resource(name="employeeMessageDao")
	private EmployeeMessageDao employeeMessageDao;
	
	@Resource(name="departmentNewsDao")
	private DepartmentNewsDao departmentNewsDao;
	
	

	public String getSingleAttendStringByemployeeId(int employeeid){
		StringBuilder sb = new StringBuilder();
		Employee employee = employeeDao.findEmployeeById(employeeid);
		if(employee == null){
			sb.append("您好，未找到编号为").append(employeeid).append("的员工！");
		}else{
			List<Attend> list = attendDao.findAttendByEmployeeId(employeeid, 1);
			sb.append("您好，编号为").append(employeeid).append("的员工(").append(employee.getName());
			if(list == null || list.size()<1 || list.get(0).getPunchtime()==null){
				sb.append(")无考勤记录！");
			}else{
				Attend e = list.get(0);
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				sb.append(")最近一次考勤记录如下:\n").append("考勤时间：").append(e.getPunchtime())
				  .append("\n考勤说明：").append(e.getRemark());
			}
		}
		return sb.toString();
	}


	public String getAttendHistoryStringByemployeeId(int employeeid){
		StringBuilder sb = new StringBuilder();
		Employee employee = employeeDao.findEmployeeById(employeeid);
		if(employee == null){
			sb.append("您好，未找到编号为").append(employeeid).append("的员工！");
		}else{
			sb.append("您好，编号为").append(employeeid).append("的员工(").append(employee.getName());
			List<Attend> list = attendDao.findAttendByEmployeeId(employeeid, 10);
			if(list == null || list.size()<1 || list.get(0).getPunchtime()==null){
				sb.append(")无考勤记录！");
			}else{
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Attend e = null;
				sb.append(")最近10次考勤情况如下:");
				for(Attend em : list){
					sb.append("考勤时间：").append(e.getPunchtime())
							.append("\n考勤说明：").append(e.getRemark())
					  		.append("\n------分割线-------");
				}
			}
		}
		return sb.toString();
	}


	public String getSingleEmployeeMessageByemployeeId(int employeeid){
		StringBuilder sb = new StringBuilder();
		Employee employee = employeeDao.findEmployeeById(employeeid);
		if(employee == null){
			sb.append("您好，未找到编号为").append(employeeid).append("的员工！");
		}else{
			sb.append("您好，编号为").append(employeeid).append("的员工(").append(employee.getName());
			List<EmployeeMessage> list = employeeMessageDao.findEmployeeMessageByEmployeeId(employeeid, 1);
			if(list == null || list.size()<1 ){
				sb.append(")无部门主管留言！");
			}else{
				sb.append(")最近部门主管留言如下:");
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				EmployeeMessage sm = list.get(0);
				sb.append("\n留言时间：").append(sf.format(sm.getInsertTime()))
				  .append("\n留言内容：").append(sm.getContent());
			}	
		}
		return sb.toString();
	}
	

	public String getEmployeeMessageHistoryByEmployeeId(int employeeid){
		StringBuilder sb = new StringBuilder();
		Employee employee = employeeDao.findEmployeeById(employeeid);
		if(employee == null){
			sb.append("您好，未找到编号为").append(employeeid).append("的员工！");
		}else{
			sb.append("您好，编号为").append(employeeid).append("的员工(").append(employee.getName());
			List<EmployeeMessage> list = employeeMessageDao.findEmployeeMessageByEmployeeId(employeeid, 10);
			if(list == null || list.size()<1 ){
				sb.append(")无部门主管留言！");
			}else{
				sb.append(")最近(10次)部门留言如下:");
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				for(EmployeeMessage sm : list){
					sb.append("\n留言时间：").append(sf.format(sm.getInsertTime()))
					  .append("\n留言内容：").append(sm.getContent())
					  .append("\n------分割线-------");
				}
			}	
		}
		return sb.toString();
	}
	

	public String getSingleDeptNewsByemployeeId(int employeeid){
		StringBuilder sb = new StringBuilder();
		Employee employee = employeeDao.findEmployeeById(employeeid);
		if(employee == null){
			sb.append("您好，未找到编号为").append(employeeid).append("的员工！");
		}else{
			sb.append("您好，编号为").append(employeeid).append("的员工(").append(employee.getName());
			List<DepartmentNews> list = departmentNewsDao.findDepartmentNewsByDepartmentId(employee.getDepartment_id(), 1);
			if(list == null || list.size()<1 ){
				sb.append(")所在部门无部门动态！");
			}else{
				sb.append(")最近部门动态如下:");
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				DepartmentNews cn = list.get(0);
				sb.append("\n动态时间：").append(sf.format(cn.getInsertTime()))
				  .append("\n动态内容：").append(cn.getContent());
			}	
		}
		return sb.toString();
	}
	

	public String getDeptNewsHistoryByemployeeId(int employeeid){
		StringBuilder sb = new StringBuilder();
		Employee employee = employeeDao.findEmployeeById(employeeid);
		if(employee == null){
			sb.append("您好，未找到编号为").append(employeeid).append("的员工！");
		}else{
			sb.append("您好，编号为").append(employeeid).append("的员工(").append(employee.getName());
			List<DepartmentNews> list = departmentNewsDao.findDepartmentNewsByDepartmentId(employee.getDepartment_id(), 10);
			if(list == null || list.size()<1 ){
				sb.append(")所在班级无部门动态！");
			}else{
				sb.append(")最近部门动态如下:");
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				for(DepartmentNews cn:list){
					sb.append("\n动态时间：").append(sf.format(cn.getInsertTime()))
					  .append("\n动态内容：").append(cn.getContent())
					  .append("\n------分割线-------");
				}
			}	
		}
		return sb.toString();
	}
	


	public void addMessage(Message message) {
		messageDao.addMessage(message);
	}


	public List<Message> listMessage(int start,int size){
		return messageDao.findMessage(start,size);
	}
	


	public List<Reply> listReply(int start,int size){
		return replyDao.findReply(start,size);
	}
	

	public void addReply(Reply reply){
		replyDao.addReply(reply);
		if(Reply.NEWS.equals(reply.getMsgType())&&null != reply.getArticles()){
			List<Article> articles = reply.getArticles();
			for(Article a: articles){
				a.setReplyId(reply.getId());
				replyDao.addArticle(a);
			}
		}
	}
	
}
