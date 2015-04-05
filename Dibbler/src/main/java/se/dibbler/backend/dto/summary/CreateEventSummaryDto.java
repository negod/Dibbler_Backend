/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto.summary;

import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import se.dibbler.backend.generics.BaseDtoNoId;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class CreateEventSummaryDto extends BaseDtoNoId {

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The default event header", required = true)
    String eventHeader;

    @XmlElement(type = String.class, required = true)
    @ApiModelProperty(value = "The default eventText Bodys", required = true)
    String eventTextBody;

    @XmlElement(type = Date.class, required = true)
    @ApiModelProperty(value = "The startdate of the event", required = true)
    Date startDate;

    @XmlElement(type = Date.class, required = true)
    @ApiModelProperty(value = "The enddate of the event", required = true)
    Date endDate;

}
