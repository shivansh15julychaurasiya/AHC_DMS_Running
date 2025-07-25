package com.dms.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CaseType;
import com.dms.model.CaseTypeLko;
import com.dms.model.CauseListType;



@Service
public class CasetypeService 
{
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	EntityManager em;
	
	@Transactional
	public CaseType getById(Long ct_id) 
	{		
		CaseType ct=new CaseType();
		try {
			String query  ="SELECT ct from CaseType ct WHERE ct.ct_id =:ct_id";
			ct=  (CaseType) em.createQuery(query).setParameter("ct_id", ct_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
	
	
	@Transactional
	public CaseTypeLko getByIdLko(Long ct_id) 
	{		
		CaseTypeLko ct=new CaseTypeLko();
		try {
			String query  ="SELECT ct from CaseTypeLko ct WHERE ct.ct_id =:ct_id";
			ct=  (CaseTypeLko) em.createQuery(query).setParameter("ct_id", ct_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
}
