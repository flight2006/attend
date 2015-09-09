package com.hdu.web.controller;


import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import com.hdu.model.Attend;
import com.hdu.model.Department;
import com.hdu.model.Employee;
import com.hdu.service.AttendService;
import com.hdu.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 包含考试列表菜单内的所有操作
 */
@Controller
public class AttendController {
	
	public static final int pagesize = 10;
	
	@Resource(name="attendService")
	private AttendService attendService;
	@Resource(name = "employeeService")
	private EmployeeService employeeService;
	
	@RequestMapping(value="/manager/attend",method=RequestMethod.GET)
	public ModelAndView listStudent(String pagenum,Attend exam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("exams");
		mv.addObject("sidebar","exams");
		int num = 1;
		if(null!=pagenum){
			num = Integer.parseInt(pagenum);
		}
		List<Attend> list = null;
		try {
			list = attendService.findAttendByDepartmentId((num-1)*pagesize, pagesize,exam);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Department> clslist = attendService.findAllDepartment();
		mv.addObject("examList", list);
		mv.addObject("clsList", clslist);
		mv.addObject("length", list.size());
		mv.addObject("pagenum", num);
		mv.addObject("exam", exam);
		return mv;
	}

 
	@RequestMapping(value="/manager/addexam",method=RequestMethod.POST)
	public ModelAndView addExam(Attend exam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("addexam");
		mv.addObject("sidebar","exams");
		List<Employee> stlist = attendService.findEmployeeByDepartmentId(exam.getDepartment_id());
		mv.addObject("exam",exam);
		mv.addObject("stlist",stlist);
		return mv;
	}
	
	@RequestMapping(value="/manager/viewattend",method=RequestMethod.GET)
	public ModelAndView viewExam(int id){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("viewexam");
		mv.addObject("sidebar","exams");
		Attend exam =  attendService.findAttendById(id);
		mv.addObject("exam",exam);
		return mv;
	}

	@RequestMapping(value = "/manager/attendlist",method = RequestMethod.GET)
	public ModelAndView attendList(int studentid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("attendlist");
		Employee student = employeeService.findemployeeById(studentid);
		mv.addObject("student",student);
		mv.addObject("sidebar","exams");
		List<Attend> attends = attendService.findAttendByEmployeeId(studentid);

		mv.addObject("attendslist",attends);
		return mv;
	}
}
