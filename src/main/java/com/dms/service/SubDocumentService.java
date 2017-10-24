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
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return subDocument;
	}
	
	public List<SubDocument> getSubDocuments(Long fd_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid order by sd_submitted_date")
				.setParameter("sd_fd_mid", fd_id).getResultList();

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
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return subDocument;

	}
	
	

}
