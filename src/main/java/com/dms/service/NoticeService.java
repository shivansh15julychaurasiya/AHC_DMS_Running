package com.dms.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.ApplicationNotice;
import com.dms.model.CaseNotice;

@Service
public class NoticeService {

	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	@Transactional
	public ApplicationNotice save(ApplicationNotice applicationNotice) {

		ApplicationNotice an = null;
    	try {	
    		an= em.merge(applicationNotice);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return an;
	}
	
	@Transactional
	public CaseNotice save(CaseNotice caseNotice) {

		CaseNotice cn = null;
    	try {	
    		cn= em.merge(caseNotice);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cn;
	}
}
