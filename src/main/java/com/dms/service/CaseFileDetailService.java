package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CaseFileDetail;
import com.dms.model.CauseList;
import com.dms.model.ImpugnedOrder;
import com.dms.model.MetaData;
import com.efiling.model.EfilingCaseFileDetail;
import com.efiling.model.RegisteredCaseDetails;

@Service
public class CaseFileDetailService {

	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em2;
	
	@Transactional
	public CaseFileDetail getCaseFileDetail(Long id) {

		CaseFileDetail result=new CaseFileDetail();
	    Query query=null;
		query = em.createQuery("SELECT c from CaseFileDetail c where c.fd_id=:id").setParameter("id", id);
		result=(CaseFileDetail) query.getSingleResult();
		
		return result;
	}
	@Transactional
	public CaseFileDetail save(CaseFileDetail casefile) {

		CaseFileDetail cfd = null;
    	try {	
    		cfd= em.merge(casefile);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cfd;
	}
	@Transactional("transactionManagerEfiling")
	public EfilingCaseFileDetail saveCaseFile(EfilingCaseFileDetail casefile) {
		EfilingCaseFileDetail cfd = null;
    	try {	
    		cfd= em2.merge(casefile);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cfd;
	}
	@Transactional
	public EfilingCaseFileDetail getEfilingCaseFileDetail(Long casetypeId,String caseNo,Integer caseYear) {
		EfilingCaseFileDetail cfd = null;
		try {
			cfd = (EfilingCaseFileDetail) em2.createQuery("SELECT c FROM EfilingCaseFileDetail c where c.fd_case_type=:fd_case_type and c.fd_case_no=:fd_case_no and c.fd_case_year=:fd_case_year")
					.setParameter("fd_case_type", casetypeId).setParameter("fd_case_no", caseNo).setParameter("fd_case_year", caseYear)
					.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return cfd;
	}
	
	// Session session=em.unwrap(Session.class);
	public List<CaseFileDetail> getCaseFiles(CaseFileDetail casefile) {
		// TODO Auto-generated method stub
		List<CaseFileDetail> result = em.createQuery("SELECT c FROM CaseFileDetail c where c.fd_case_type=:fd_case_type and c.fd_case_no=:fd_case_no and c.fd_case_year=:fd_case_year")
				.setParameter("fd_case_type", casefile.getFd_case_type()).setParameter("fd_case_no", casefile.getFd_case_no()).setParameter("fd_case_year", casefile.getFd_case_year())
				.getResultList();

		return result;
	}
	
	public CaseFileDetail getCaseFile(CaseFileDetail casefile) {
		// TODO Auto-generated method stub
		CaseFileDetail result = null;
		try {
			result = (CaseFileDetail) em.createQuery("SELECT c FROM CaseFileDetail c where c.fd_case_type=:fd_case_type and c.fd_case_no=:fd_case_no and c.fd_case_year=:fd_case_year")
					.setParameter("fd_case_type", casefile.getFd_case_type()).setParameter("fd_case_no", casefile.getFd_case_no()).setParameter("fd_case_year", casefile.getFd_case_year())
					.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		return result;
	}
	@Transactional
	public List<MetaData> getMetadata(Long fd_id) {
		// TODO Auto-generated method stub
		List<MetaData> result =new ArrayList<MetaData>();
		
		try {
			result=em.createQuery("select md from MetaData md where md_fd_mid=:md_fd_mid AND md_rec_status=1 order by md_sequence asc").setParameter("md_fd_mid", fd_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public ImpugnedOrder getImpugnedOrder(Long ioId) {
		// TODO Auto-generated method stub
		ImpugnedOrder result=new ImpugnedOrder();
	    Query query=null;
		try {
			query = em.createQuery("SELECT i from ImpugnedOrder i where i.io_id=:id").setParameter("id", ioId);
			result=(ImpugnedOrder) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
		return result;
	}
	public CaseFileDetail getCaseFileDetail(Long io_hc_case_type, String io_case_no, Integer io_case_year) {
		// TODO Auto-generated method stub
		CaseFileDetail result = new CaseFileDetail();
		try {
			result = (CaseFileDetail) em.createQuery("SELECT c FROM CaseFileDetail c where c.fd_case_type=:fd_case_type and c.fd_case_no=:fd_case_no and c.fd_case_year=:fd_case_year")
					.setParameter("fd_case_type", io_hc_case_type).setParameter("fd_case_no", io_case_no).setParameter("fd_case_year", io_case_year)
					.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return result;
	}
	
}
