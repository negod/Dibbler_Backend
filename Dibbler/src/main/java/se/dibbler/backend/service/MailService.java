/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.dibbler.backend.dao.bean.EmailBean;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Path("/mail")
@Stateless
public class MailService {
    
    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);
    
    @EJB
    EmailBean mail;

    /*@EJB
     ErrorLogDao errorLog;*/
    /**
     * @summary sends an email ( Only as test for now )
     */
    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public void getById(@QueryParam("message") String id) {
        try {
            mail.sendTestEmail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
    
}
