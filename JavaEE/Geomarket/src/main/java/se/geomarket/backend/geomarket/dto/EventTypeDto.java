/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import se.geomarket.backend.geomarket.dto.languagesupport.EventTextDto;
import se.geomarket.backend.geomarket.dto.languagesupport.LanguageTextDto;
import com.wordnik.swagger.annotations.ApiModel;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "Defines a type of event that can be published")
@XmlRootElement(name = "eventType")
public class EventTypeDto extends LanguageTextDto {

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
