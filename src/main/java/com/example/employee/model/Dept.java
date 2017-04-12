package com.example.employee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude={"emps"})
@Entity
public class Dept {

	@Id
	private Long deptno;

	@Column(length = 14, nullable = false)
	private String dname;

	@Column(length = 13)
	private String loc;

	@OneToMany(mappedBy="dept")
	@JsonIgnore
	private List<Emp> emps = new ArrayList<Emp>();
	
	public void addEmp(Emp emp){
		this.emps.add(emp);
		if (emp.getDept() != this) {
			emp.setDept(this);
		}
	}
	
}
