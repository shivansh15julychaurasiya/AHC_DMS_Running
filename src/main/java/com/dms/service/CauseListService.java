package com.dms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CauseList;
import com.dms.model.CauseListType;
import com.dms.model.CourtOrder;
import com.dms.model.PetitionerAdvocate;
import com.dms.model.RespondentAdvocate;



@Service
public class CauseListService 
{
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	EntityManager em;
	
	@Transactional
    public CauseList save(CauseList cl) {
    
		CauseList causeList = null;
    	try {	
    		causeList= em.merge(cl);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
    	return causeList;
    }
	
	@Transactional
	public List<CauseListType> getCauseListTypes() {
		List<CauseListType> result = em.createQuery("SELECT c FROM CauseListType c").getResultList();
		return result;
	}
	
	@Transactional
	public Long findCaseType(String case_abbrivation,Long bench_code) 
	{
		
		Long case_type=0L;
		try {
			String query  ="SELECT ct.ct_id from CaseType ct WHERE ct.ct_label ='"+case_abbrivation+"' and ct.ct_bench_code= "+bench_code;
			case_type= (Long) em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return case_type;
	}
	
	@Transactional
	public Long findCauseListType(String list_name) 
	{
		
		Long list_id=0L;
		try {
			String query  ="SELECT clt.clt_id from CauseListType clt WHERE clt.clt_name ='"+list_name+"')";
			list_id=  (Long) em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list_id;
	}
	@Transactional
	public CauseListType findCauseListType(Long clt_id) 
	{
		
		CauseListType c=new CauseListType();
		try {
			String query  ="SELECT clt from CauseListType clt WHERE clt.clt_id =:clt_id";
			c=  (CauseListType) em.createQuery(query).setParameter("clt_id", clt_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	@Transactional
	public Long findDocument(Long bench_code,Long case_type,Long case_no,Long case_year) 
	{
		
		Long document_id=0L;
		try {
			//Query query  =  em.createQuery("SELECT ct.ct_lk_mid from CaseType ct WHERE ct.ct_label =:case_abbrivation and ct.ct_bench_code= :bench_code ");
			//query.setParameter("case_abbrivation", case_abbrivation).setParameter("bench_code", bench_code);
			//document_id= (Long) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document_id;
	}
	
	@Transactional
	public List<CauseList> getList(CauseList causeList) {
		// TODO Auto-generated method stub
		List<CauseList> list=new ArrayList<CauseList>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no();
		}
		if(causeList.getCl_list_type_mid()!=null)
		{	
			querystr+=" AND c.cl_list_type_mid="+causeList.getCl_list_type_mid();			
		}
		
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" order by c.cl_serial_no,c.cl_id");
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		return list;
	}
	
	@Transactional
	public List getListByType(CauseList causeList) {
		// TODO Auto-generated method stub
		List<Object> list = new  ArrayList<Object>()  ;
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(new Date());
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no();
		}
		try {
			Query query  =  em.createQuery("SELECT max(cl_serial_no)as count,cl_list_type_mid from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" group by c.cl_list_type_mid");	
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;	
	}
	
	public CauseList getByPk(Long cl_id) {
		// TODO Auto-generated method stub
		CauseList causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_id =:cl_id").setParameter("cl_id", cl_id);
			causelist= (CauseList) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return causelist;

	}
	public Integer getPrioritywise(Long cl_fd_mid) {
		// TODO Auto-generated method stub
		Integer sequence=0;
		try {
			String query  ="SELECT c.cl_sequence from CauseList c order by c.cl_sequence desc";
			sequence= (Integer) em.createQuery(query).setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sequence;
	}
	
	@Transactional
    public PetitionerAdvocate savePetAdvocate(PetitionerAdvocate a) {    
		PetitionerAdvocate pa = null;
    	try {	
    		pa= em.merge(a);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return pa;
    }
	
	@Transactional
    public RespondentAdvocate saveResAdvocate(RespondentAdvocate a) {    
		RespondentAdvocate ra = null;
    	try {	
    		ra= em.merge(a);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return ra;
    }
	@Transactional
	public void deleteCauseList(Date date) {
		// TODO Auto-generated method stub
		try {
			Query query  =  em.createQuery("DELETE  from CauseList c WHERE c.cl_dol < :cDate").setParameter("cDate", date);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Transactional
	public void deletePetitionerAdvocates() {
		// TODO Auto-generated method stub
		try {
			Query query  =  em.createQuery("DELETE  from PetitionerAdvocate");
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Transactional
	public void deleteRespondentAdvocates() {
		// TODO Auto-generated method stub
		try {
			Query query  =  em.createQuery("DELETE  from RespondentAdvocate");
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Transactional
	public CourtOrder saveCourtOrder(CourtOrder co) {
		// TODO Auto-generated method stub
		CourtOrder order = null;
    	try {	
    		order= em.merge(co);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
    	return order;
	}

	public List<CourtOrder> getOrdersList(Integer cum_court_mid) {
		// TODO Auto-generated method stub
		List<CourtOrder> orders=new ArrayList<>();
		try {
			String query  ="SELECT co from CourtOrder co WHERE co.co_court_no =:co_court_no";
			orders=em.createQuery(query).setParameter("co_court_no", cum_court_mid).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
	public CourtOrder getCourtOrder(Long co_id) {
		// TODO Auto-generated method stub
		CourtOrder co=new CourtOrder();
		try {
			String query  ="SELECT c from CourtOrder c where c.co_id=:co_id";
			co=(CourtOrder) em.createQuery(query).setParameter("co_id", co_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
	}
}
