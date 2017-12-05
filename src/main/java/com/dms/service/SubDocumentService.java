package com.dms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.SubDocument;

@Service
public class SubDocumentService
{
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public SubDocument save(SubDocument s) {
		SubDocument master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	@Transactional
	public Integer getCount(Long fd_id) {
		// TODO Auto-generated method stub
		Integer count=0;
		try {
			Query query=em.createQuery("SELECT Count(d) FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid").setParameter("sd_fd_mid",fd_id);
			count= Integer.parseInt(query.getSingleResult().toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return count;
	}
	
	@Transactional
	public SubDocument getSubDocument(Long fd_id) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid order by sd_id asc").setParameter("sd_fd_mid",fd_id);
			subDocument= (SubDocument) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			return subDocument;	
		}
	}
	
	public List<SubDocument> getSubDocuments(Long fd_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_id not in (select ord.ord_sd_mid from OrderReport ord where ord.ord_fd_mid=:ord_fd_mid and ord.ord_sd_mid is not null)order by sd_submitted_date")
				.setParameter("sd_fd_mid", fd_id).setParameter("ord_fd_mid", fd_id).getResultList();

		return result;
	}
	
	public SubDocument getByJudgementID(Long judgement_id) {
		// TODO Auto-generated method stub
		SubDocument result=null;
		
		try {
			result = (SubDocument) em.createQuery("SELECT d FROM SubDocument d where d.sd_judgement_id=:judgement_id")
					.setParameter("judgement_id", judgement_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	public SubDocument getByPK(Long sd_id) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=em.createQuery("SELECT d FROM SubDocument d where d.sd_id=:sd_id").setParameter("sd_id",sd_id);
			subDocument= (SubDocument) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			return subDocument;	
		}

	}
	public SubDocument getApplication(Integer app_no, Integer app_year) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=em.createQuery("SELECT d FROM SubDocument d where d.sd_document_no=:app_no and d.sd_document_year=:app_year and d.sd_if_mid=14").setParameter("app_no",app_no).setParameter("app_year", app_year);
			subDocument= (SubDocument) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			return subDocument;	
		}		
		
	}
	public List<SubDocument> getAllSubDocuments(Long fd_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid order by d.sd_submitted_date")
				.setParameter("sd_fd_mid", fd_id).getResultList();

		return result;
	}
	@Transactional
	public SubDocument getPetitionSubDocument(Long fd_id) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_if_mid=1 order by sd_id asc").setParameter("sd_fd_mid",fd_id);
			subDocument= (SubDocument) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
		}finally{
			return subDocument;	
		}
	}

}
