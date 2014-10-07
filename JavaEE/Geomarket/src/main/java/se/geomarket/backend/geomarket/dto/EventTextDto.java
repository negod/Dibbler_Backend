/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.generics.BaseDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@ApiModel(value = "The eventText for the event")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eventText")
public class EventTextDto extends BaseDto {

    @ApiModelProperty(value = "The id for the language to be added", required = true)
    @XmlElement(required = true, type = String.class)
    private String languageId;

    @ApiModelProperty(value = "The heading for the event", required = true)
    @XmlElement(required = true, type = String.class)
    private String heading;

    @ApiModelProperty(value = "The description and body for the event", required = true)
    @XmlElement(required = true, type = String.class)
    private String body;

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
