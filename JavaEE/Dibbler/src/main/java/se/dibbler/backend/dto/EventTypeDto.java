/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import se.dibbler.backend.dto.languagesupport.EventTextDto;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.dibbler.backend.dto.languagesupport.BaseTypeDto;
import se.dibbler.backend.dto.languagesupport.EventTypeTextDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "eventType")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "Defines a type of event that can be published")
public class EventTypeDto extends BaseTypeDto {

    @XmlElement(type = EventTextDto.class, required = true, name = "languages")
    @ApiModelProperty(value = "Language support for the EventType", required = true)
    List<EventTypeTextDto> eventTexts;

    public EventTypeDto(String language, String value, String description) {
        super(language, value, description);
    }

    public EventTypeDto() {
    }

    public List<EventTypeTextDto> getEventTexts() {
        return eventTexts;
    }

    public void setEventTexts(List<EventTypeTextDto> eventTexts) {
        this.eventTexts = eventTexts;
    }

}
