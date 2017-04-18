package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.dto.Pagination;
import com.example.employee.model.Dept;
import com.example.employee.repository.DeptRepository;

@Transactional
@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptRepository deptRepository;
	
	@Override
	public Dept insert(Dept dept) {
		if (dept.getDeptno() != 0L) {
			dept.setDeptno(0L);
		}
		return deptRepository.save(dept);
	}

	@Override
	public Dept update(Dept dept) {
		if (dept.getDeptno() == 0L) {
			throw new RuntimeException("DEPTNO can not be 0 when try to update.");
		}
		return deptRepository.save(dept);
	}

	@Override
	public Long delete(Long deptno) {
		try {
			deptRepository.delete(deptno);
		} catch (Exception e) {
			throw e;
		}
		return 1L;
	}

	@Override
	public Long count() {
		return deptRepository.count();
	}

	@Override
	public Dept selectByDeptno(Long deptno) {
		return deptRepository.findOne(deptno);
	}

	@Override
	public List<Dept> select() {
		return deptRepository.findAll();
	}
	
	@Override
	public List<Dept> selectByLimit(int page, int size) {
		/**
		 * 1-base paging index
		 */
		page = page > 0 ? (page - 1) : page;
		
		//Pageable pageable = new PageRequest(page, size, new Sort(new Order(Direction.ASC, "deptno")));
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "deptno");
		Page<Dept> result = deptRepository.findAll(pageable);
		return result.getContent();
	}
	
	@Override
	public Page<Dept> selectByLimitForPage(Pageable pageable) {
		/**
		 * 0-base paging index
		 */
		Page<Dept> result = deptRepository.findAll(pageable);
		return result;
	}
	
	@Override
	public Pagination<Dept> selectByLimitForPagination(Pageable pageable, int bsize) {
		/**
		 * 1-base paging index
		 */
		int page = pageable.getPageNumber() > 0 ? (pageable.getPageNumber() - 1) : pageable.getPageNumber();
		Pageable p = new PageRequest(page, pageable.getPageSize(), pageable.getSort());
		Page<Dept> result = deptRepository.findAll(p);
		
		return new Pagination<Dept>(result, bsize);
	}

	@Override
	public Dept selectByDname(String dname) {
		return deptRepository.findByDname(dname);
	}

	@Override
	public List<Dept> selectByLoc(String loc) {
		return deptRepository.findByLoc(loc);
	}

}
