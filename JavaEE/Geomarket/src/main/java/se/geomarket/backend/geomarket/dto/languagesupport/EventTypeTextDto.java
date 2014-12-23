/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dto.languagesupport;

import com.wordnik.swagger.annotations.ApiModel;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@XmlRootElement(name = "eventTypeName")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "Defines a type of event that can be published")
public class EventTypeTextDto extends LanguageTextDto {
}
