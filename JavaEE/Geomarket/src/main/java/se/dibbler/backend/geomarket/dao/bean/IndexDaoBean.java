/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.geomarket.dao.bean;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@LocalBean
public class IndexDaoBean {

    @PersistenceContext(unitName = "geomarket_PU")
    private EntityManager em;

    private final int BATCH_SIZE = 25;

    public void reIndex(Class clazz) {
        Session session = (Session) em.getDelegate();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        try {
            fullTextSession
                    .createIndexer(clazz)
                    .batchSizeToLoadObjects(BATCH_SIZE)
                    .cacheMode(CacheMode.NORMAL)
                    .threadsToLoadObjects(5)
                    .threadsForSubsequentFetching(20)
                    .startAndWait();
        } catch (InterruptedException ex) {
            Logger.getLogger(IndexDaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
