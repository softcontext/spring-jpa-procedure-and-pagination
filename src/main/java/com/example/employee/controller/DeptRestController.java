package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.service.DeptService;

@RestController
public class DeptRestController {
	@Autowired
	private DeptService deptService;

	/* http://localhost:8080/depts/all */
	@GetMapping("/depts/all")
	public Object getDepts(){
		return deptService.select();
	}
	
	/**
	 * 0-base paging index
	 */
	/* 
	 * http://localhost:8080/depts
	 * http://localhost:8080/depts?page=0&size=2
	 * http://localhost:8080/depts?page=0&size=2&sort=deptno,asc
	 */
	@GetMapping("/depts")
	public Object getDeptsLimitForPage(Pageable pageable){
		return deptService.selectByLimitForPage(pageable);
	}
	
	/**
	 * 1-base paging index
	 */
	/* http://localhost:8080/depts/1?size=2 */
	@GetMapping("/depts/{page}")
	public Object getDeptsLimitForPage(@PathVariable int page,
			@RequestParam(name="size", required=false, defaultValue="10") int size){
		return deptService.selectByLimit(page, size);
	}
}
