package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.model.Salgrade;

public interface SalgradeRepository extends JpaRepository<Salgrade, Integer>{

}
