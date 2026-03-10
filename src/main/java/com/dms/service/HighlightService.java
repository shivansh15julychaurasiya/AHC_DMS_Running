package com.dms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



import com.dms.model.Highlight;

@Service
@Transactional
public class HighlightService {

	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;

    //  SAVE Highlight

	public Highlight saveHighlight(Highlight highlight) {

        em.persist(highlight);
        em.flush();
        return highlight;
    }


    //  GET Highlights (FIXED - returns Entity, NOT Object[])
    public List<Highlight> getHighlights(String userId, String pdfUrl) {

        try {

            TypedQuery<Highlight> query = em.createQuery(
                "SELECT h FROM Highlight h WHERE h.userId = :userId AND h.pdfUrl = :pdfUrl",
                Highlight.class
            );

            query.setParameter("userId", userId);
            query.setParameter("pdfUrl", pdfUrl);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //  REMOVE this method if not needed (causes confusion)
    // public List<Object[]> getHighlightsRaw(String userId, String pdfUrl)


    //  DELETE Highlight
    public boolean deleteHighlight(Long id) {

        try {

            Highlight highlight = em.find(Highlight.class, id);

            if (highlight != null) {
                em.remove(highlight);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}