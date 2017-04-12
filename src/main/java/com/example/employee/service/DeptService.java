package com.example.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.employee.dto.Pagination;
import com.example.employee.model.Dept;


public interface DeptService {

	public Dept insert(Dept dept);
	public Dept update(Dept dept);
	public Long delete(Long deptno);
	
	public Long count();
	public Dept selectByDeptno(Long deptno);
	public List<Dept> select();
	public List<Dept> selectByLimit(int page, int size);
	public Page<Dept> selectByLimitForPage(Pageable pageable);
	public Pagination<Dept> selectByLimitForPagination(Pageable pageable, int bsize);
	
	public Dept selectByDname(String dname);
	public List<Dept> selectByLoc(String loc);
}
