package com.hdu.web.controller;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.hdu.model.Department;
import com.hdu.model.DepartmentNews;
import com.hdu.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hdu.service.DepartmentService;

/**
 * 包含班级列表菜单内的所有操作
 */
@Controller
public class DepartmentController {
	
	
	public static int pagesize = 10;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	
	@RequestMapping(value="/manager/department",method=RequestMethod.GET)
	public ModelAndView listStudent(String pagenum,Department classes){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("classes");
		mv.addObject("sidebar","classes");
		int num = 1;
		if(null!=pagenum){
			num = Integer.parseInt(pagenum);
		}
		List<Department> list = departmentService.listdepartment((num - 1) * pagesize, pagesize, classes);
		mv.addObject("classesList", list);
		mv.addObject("length", list.size());
		mv.addObject("pagenum", num);
		mv.addObject("classes", classes);
		return mv;
	}

	
	@RequestMapping(value="/manager/adddepartmentpage",method=RequestMethod.GET)
	public ModelAndView addClassesPage(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("addclasses");
		mv.addObject("sidebar","classes");
		return mv;
	}
	
	@RequestMapping(value="/manager/adddepartment",method=RequestMethod.POST)
	public ModelAndView addClasses(Department classes){
		ModelAndView mv=new ModelAndView();
		Department cls = departmentService.finddepartmentById(classes.getId());
		if(null==cls){
			mv.setViewName("redirect:/manager/department");
			classes.setEmployeecount(0);
			departmentService.addDepartment(classes);
		}else{
			mv.setViewName("redirect:/manager/adddepartmentpage");
			mv.addObject("name", classes.getName());
			mv.addObject("headteacher", classes.getManager());
			mv.addObject("sidebar","classes");
			mv.addObject("notice","已存在编号为"+classes.getId()+"的部门");
		}
		return mv;
	}
	
	@RequestMapping(value="/manager/manageremployeepage",method=RequestMethod.GET)
	public ModelAndView studentPage(int departmentid){
		ModelAndView mv=new ModelAndView();
		Department cls = departmentService.finddepartmentById(departmentid);
		List<Employee> stlist = departmentService.findEmployeeBydepartmentId(departmentid);
		mv.setViewName("addstudents");
		mv.addObject("sidebar","classes");
		mv.addObject("cls",cls);
		mv.addObject("stlist",stlist);
		mv.addObject("length", stlist.size());
		return mv;
	}
	
	@RequestMapping(value="/manager/departmentnewspage",method=RequestMethod.GET)
	public ModelAndView classesnewsPage(Integer departmentid){
		ModelAndView mv=new ModelAndView();
		Department cls = departmentService.finddepartmentById(departmentid);
		List<DepartmentNews> cnlist= departmentService.finddepartmentNewsByDepartmentId(departmentid);
		mv.setViewName("addclassesnews");
		mv.addObject("sidebar","classes");
		mv.addObject("cls",cls);
		mv.addObject("cnlist",cnlist);
		return mv;
	}
	
	@RequestMapping(value="/manager/adddepartmentnews",method=RequestMethod.POST)
	public ModelAndView addClassesNews(DepartmentNews classesNews,int departmentid){
		ModelAndView mv=new ModelAndView();
		classesNews.setInsertTime(new Date());
		classesNews.setDepartmentId(departmentid);
		departmentService.adddepartmentNews(classesNews);
		mv.addObject("notice","添加部门动态成功");
		mv.addObject("departmentid",classesNews.getDepartmentId());
		mv.setViewName("redirect:/manager/departmentnewspage");
		return mv;
	}
	
	
	@RequestMapping(value="/manager/deleteclassesnews",method=RequestMethod.GET)
	public ModelAndView deleteClassesNews(int departmentid,int id){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/manager/departmentnewspage");
		mv.addObject("departmentid",departmentid);
		departmentService.deletedepartmentNewsById(id);
		mv.addObject("notice","删除动态成功");
		return mv;
	}
	
	
	@RequestMapping(value="/manager/addemployee",method=RequestMethod.POST)
	public ModelAndView addStudent(Employee student,Integer departmentid){
		ModelAndView mv=new ModelAndView();
		Employee stu = departmentService.findEmployeeById(student.getId());
		if(stu==null){
			student.setDepartment_id(departmentid);
			departmentService.addStudent(student);
			departmentService.updateDepartmentEmployeeCount(student.getDepartment_id());
			mv.addObject("departmentid",departmentid);
			mv.addObject("notice","添加员工成功");
		}else{
			mv.addObject("notice","已经存在编号为"+student.getId()+"的员工("+stu.getName()+")！");
		}
		mv.addObject("classesid",student.getDepartment_id());
		mv.setViewName("redirect:/manager/manageremployeepage");
		return mv;
	}
	
	@RequestMapping(value="/manager/deletestudent",method=RequestMethod.GET)
	public ModelAndView deleteStudent(int studentid,int departmentid){
		ModelAndView mv=new ModelAndView();
		departmentService.deleteEmployeeById(studentid);
		departmentService.updateDepartmentEmployeeCount(departmentid);
		mv.addObject("departmentid",departmentid);
		mv.addObject("notice","删除员工信息成功");
		mv.setViewName("redirect:/manager/manageremployeepage");
		return mv;
	}
	
	@RequestMapping(value="/manager/updatestudent",method=RequestMethod.POST)
	public ModelAndView updateStudent(Employee employee,int departmentid){
		ModelAndView mv=new ModelAndView();
		employee.setDepartment_id(departmentid);
		departmentService.updateEmployee(employee);
		mv.addObject("departmentid",departmentid);
		mv.addObject("notice","编辑员工信息成功");
		mv.setViewName("redirect:/manager/manageremployeepage");
		return mv;
	}
	
}
