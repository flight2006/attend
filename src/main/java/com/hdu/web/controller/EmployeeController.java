package com.hdu.web.controller;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.hdu.model.Department;
import com.hdu.model.Employee;
import com.hdu.model.EmployeeMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hdu.service.EmployeeService;

/**
 * 包含学生列表菜单内的所有操作
 */
@Controller
public class EmployeeController {
	
	public static final int pagesize = 8;
	
	@Resource(name="employeeService")
	private EmployeeService employeeService;
	
	@RequestMapping(value="/manager/employee",method=RequestMethod.GET)
	public ModelAndView listStudent(String pagenum,Employee student){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("students");
		mv.addObject("sidebar","students");
		int num = 1;
		if(null!=pagenum){
			num = Integer.parseInt(pagenum);
		}
		List<Employee> list = employeeService.listemployee((num - 1) * pagesize, pagesize, student);
		List<Department> clslist = employeeService.findAlldepartment();
		mv.addObject("studentList", list);
		mv.addObject("clsList", clslist);
		mv.addObject("length", list.size());
		mv.addObject("pagenum", num);
		mv.addObject("student", student);
		return mv;
	}

	
	@RequestMapping(value="/manager/leavemessage",method=RequestMethod.GET)
	public ModelAndView leavemessage(Integer studentid){
		ModelAndView mv=new ModelAndView();
		Employee student = employeeService.findemployeeById(studentid);
		if(null == student){
			mv.setViewName("redirect:/manager/employee");
		}else{
			mv.setViewName("addstudentmessage");
			mv.addObject("sidebar","students");
			mv.addObject("student",student);
			List<EmployeeMessage> list = employeeService.listMessageByemployeeId(studentid, 100);
			mv.addObject("studentMessageList", list);
		}
		return mv;
	}
	

	@RequestMapping(value="/manager/addmessage",method=RequestMethod.POST)
	public ModelAndView addmessage(EmployeeMessage studentMessage,Integer studentid){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/manager/leavemessage?studentid=" + studentid);
		//mv.addObject("studentid",studentMessage.getEmployeeId());
		studentMessage.setInsertTime(new Date());
		studentMessage.setEmployeeId(studentid);
		System.out.println(studentMessage.getEmployeeId());
		employeeService.addemployeeMessage(studentMessage);
		mv.addObject("notice","留言成功");
		return mv;
	}
	
	@RequestMapping(value="/manager/deletemessage",method=RequestMethod.GET)
	public ModelAndView deletemessage(Integer studentid,int messageid){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/manager/leavemessage");
		mv.addObject("studentid",studentid);
		employeeService.deleteemployeeMessageById(messageid);
		mv.addObject("notice","删除成功");
		return mv;
	}

	@RequestMapping(value = "/manager/messagesenderrelate",method = RequestMethod.GET)
	public ModelAndView messagesenderrelate(String weixinid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(("weixinrelate"));
		mv.addObject("weixinid",weixinid);
		return mv;
	}

	@RequestMapping(value = "/manager/messagesenderrelatepage",method = RequestMethod.POST)
	public ModelAndView messagesenderrelatepage(String weixinid,int employeeid){
		ModelAndView mv = new ModelAndView();
		employeeService.insertWeixinId(weixinid,employeeid);
		mv.addObject("notice","微信ID关联员工编号成功！");
		mv.setViewName("redirect:/manager/messagesenderrelate");
		return mv;
	}
	
}
