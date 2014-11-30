/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 * @param <T>
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "A generic response for Dibbler")
public class WsResponse<T> implements Serializable {

    @XmlElement(required = true)
    @ApiModelProperty(value = "The data payload of the response", required = true)
    private final T data;
    @XmlElement(type = Integer.class, required = true)
    @ApiModelProperty(value = "The code provided from the service", required = true)
    private final Integer code;

    public WsResponse(T data, Integer code) {
        this.data = data;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

}
