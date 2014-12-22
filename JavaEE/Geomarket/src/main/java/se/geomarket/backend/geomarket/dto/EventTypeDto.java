/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import se.geomarket.backend.geomarket.dto.languagesupport.EventTextDto;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.dto.languagesupport.BaseTypeDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "Defines a type of event that can be published")
@XmlRootElement(name = "eventType")
public class EventTypeDto extends BaseTypeDto {

    @XmlElement(type = EventTextDto.class, required = true, name = "languages")
    @ApiModelProperty(value = "Language support for the EventType", required = true)
    List<EventTextDto> eventTexts;

    public EventTypeDto(String language, String value, String description) {
        super(language, value, description);
    }

    public EventTypeDto() {
    }

    public List<EventTextDto> getEventTexts() {
        return eventTexts;
    }

    public void setEventTexts(List<EventTextDto> eventTexts) {
        this.eventTexts = eventTexts;
    }

}
