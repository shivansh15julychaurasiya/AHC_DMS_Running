package com.dms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CaseFileDetail;
import com.dms.model.DownloadFile;
import com.dms.model.DownloadReport;
import com.dms.model.SubDocument;

@Service
public class DownloadFileService {
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	EntityManager em;
	
	@Transactional
    public DownloadReport saveReport(DownloadReport report) {
		DownloadReport dr=null;
		try {	
			dr= em.merge(report);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
    	return dr;
	}
	
	@Transactional
    public DownloadFile saveFile(DownloadFile file) {
		DownloadFile df=null;
		try {	
			df= em.merge(file);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
    	return df;
	}

	public List<DownloadReport> getDownloadReport(Long fd_id) {
		// TODO Auto-generated method stub
		List<DownloadReport> result = em.createQuery("SELECT d FROM DownloadReport d where d.dr_fd_mid=:fd_id and d.dr_rec_status=1 order by dr_cr_date")
				.setParameter("fd_id", fd_id).getResultList();
		return result;
	}
	
	public DownloadReport getById(Long dr_id) {
		DownloadReport result=new DownloadReport();
	    Query query=null;
		query = em.createQuery("SELECT d from DownloadReport d where d.dr_id=:id").setParameter("id", dr_id);
		try {
			result=(DownloadReport) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return result;
	}

	public List<DownloadFile> getFiles(Long dr_id) {
		// TODO Auto-generated method stub
		List<DownloadFile> result = em.createQuery("SELECT d FROM DownloadFile d where d.df_dr_mid=:dr_id order by df_submitted_date")
				.setParameter("dr_id", dr_id).getResultList();
		return result;
	}
}
