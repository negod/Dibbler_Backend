/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.geomarket.backend.geomarket.dto;

import com.wordnik.swagger.annotations.ApiModel;
import javax.xml.bind.annotation.XmlRootElement;
import se.geomarket.backend.geomarket.dto.languagesupport.BaseNameDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */

@ApiModel(value = "Defines a type of event that can be published")
@XmlRootElement(name = "eventType")
public class EventTextDto extends BaseNameDto{

}
