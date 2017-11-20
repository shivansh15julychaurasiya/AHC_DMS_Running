package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CaseFileDetail;
import com.dms.model.MetaData;

@Service
public class CaseFileDetailService {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public CaseFileDetail getCaseFileDetail(Long id) {

		CaseFileDetail result=new CaseFileDetail();
	    Query query=null;
		query = em.createQuery(" SELECT c from CaseFileDetail c where c.fd_id=:id").setParameter("id", id);
		result=(CaseFileDetail) query.getSingleResult();
		
		return result;
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
	
}
