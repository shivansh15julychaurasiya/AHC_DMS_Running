package com.dms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CauseList;
import com.dms.model.CauseListType;
import com.dms.model.OrderReport;

@Service
public class OrderReportService {
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	
	@Transactional
    public OrderReport save(OrderReport report) {
    
		OrderReport o = null;
    	try {	
    		o= em.merge(report);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return o;
    }
	
	
	public List<OrderReport> getOrderReports(Long fd_id) {
			
		List<OrderReport> result = em.createQuery("SELECT o FROM OrderReport o where o.ord_fd_mid=:fd_id and o.ord_rec_status=1").setParameter("fd_id", fd_id).getResultList();
		return result;
	}
	
	@Transactional
    public OrderReport getOrderReport(Long ord_id) {
    
		OrderReport or = null;
    	try {	
    		or= (OrderReport) em.createQuery("SELECT o from OrderReport o where o.ord_id=:ord_id").setParameter("ord_id", ord_id).getSingleResult();	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return or;
    }
	
}
