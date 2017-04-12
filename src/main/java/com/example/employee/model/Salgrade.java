package com.example.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Salgrade {

	@Id
	private Integer grade;

	@Column(nullable = false)
	private Double losal;

	@Column(nullable = false)
	private Double hisal;
}
