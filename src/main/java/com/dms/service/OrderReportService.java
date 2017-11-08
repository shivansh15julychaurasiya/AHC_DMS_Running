package com.dms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CauseList;
import com.dms.model.CauseListType;
import com.dms.model.OrderReport;

@Service
public class OrderReportService {
	@PersistenceContext
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
		List<OrderReport> result = em.createQuery("SELECT o FROM OrderReport o where o.ord_fd_mid=:fd_id").setParameter("fd_id", fd_id).getResultList();
		return result;
	}
	
}
