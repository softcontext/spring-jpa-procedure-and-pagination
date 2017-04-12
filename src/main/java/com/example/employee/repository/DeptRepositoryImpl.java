package com.example.employee.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

public class DeptRepositoryImpl implements DeptRepositoryCustom {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int multiply(int value) {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("proc_multiply");
		
		spq.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		spq.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
		
		spq.setParameter(1, 10);
		spq.execute();
		
		Integer out = (Integer) spq.getOutputParameterValue(2);
		
		return out;
	}

}
