/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.worker;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.dibbler.backend.dao.PublishedEventDao;
import se.dibbler.backend.entity.PublishedEvent;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Singleton
public class EventCleaner {

    private static final Logger LOG = LoggerFactory.getLogger(EventCleaner.class);

    @EJB
    PublishedEventDao eventDao;

    @Schedule(hour = "*/1", minute = "*/1", persistent = false)
    public void doShit() {
        /*Response<List<PublishedEvent>> events = eventDao.getPublishedEventsByExpiredDate(new Date());
         if (events.hasNoErrors) {
         for (PublishedEvent event : events.getData()) {
         LOG.debug(" Deleting event ");
         eventDao.delete(event);
         }
         }*/
    }

}
