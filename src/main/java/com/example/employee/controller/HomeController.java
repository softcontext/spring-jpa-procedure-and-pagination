package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.employee.service.DeptService;

@Controller
public class HomeController {
	@Autowired
	private DeptService deptService;
	
	@GetMapping("/")
	public String home(){
//		return "redirect:/depts/all";
		
		return "welcome";
	}
	
	/**
	 * 1-base paging index
	 */
	/* 
	 * http://localhost:8080/departments
	 * http://localhost:8080/departments?page=1&size=2
	 * http://localhost:8080/departments?page=1&size=2&sort=deptno,asc
	 * http://localhost:8080/departments?page=1&size=2&sort=deptno,asc&bsize=2
	 */
	@GetMapping("/departments")
//	@ResponseBody
	public Object getDeptsLimitForPage(Pageable pageable, 
			@RequestParam(name="bsize", required=false, defaultValue="5") int bsize,
			Model model){
		
//		return deptService.selectByLimitForPagination(pageable, bsize);
		
		model.addAttribute("pager", deptService.selectByLimitForPagination(pageable, bsize));
		return "dept/dept_list";
	}
}
