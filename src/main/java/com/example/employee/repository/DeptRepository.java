package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.model.Dept;

public interface DeptRepository extends JpaRepository<Dept, Long>, DeptRepositoryCustom {

	public Dept findByDname(String dname);
	public List<Dept> findByLoc(String loc);
}
