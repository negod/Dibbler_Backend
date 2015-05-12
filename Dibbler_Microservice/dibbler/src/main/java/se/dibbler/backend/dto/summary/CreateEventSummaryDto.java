/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.summary;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import se.dibbler.generic.dto.BaseDtoNoId;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class CreateEventSummaryDto extends BaseDtoNoId {

    @XmlElement(type = String.class, required = true)
    String eventHeader;

    @XmlElement(type = String.class, required = true)
    String eventTextBody;

    @XmlElement(type = Date.class, required = true)
    Date startDate;

    @XmlElement(type = Date.class, required = true)
    Date endDate;

    public String getEventHeader() {
        return eventHeader;
    }

    public void setEventHeader(String eventHeader) {
        this.eventHeader = eventHeader;
    }

    public String getEventTextBody() {
        return eventTextBody;
    }

    public void setEventTextBody(String eventTextBody) {
        this.eventTextBody = eventTextBody;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    

}
