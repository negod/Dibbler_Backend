/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.webservice;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 * @param <T>
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "code",
    "data"})
public class WsResponse<T> implements Serializable {

    @XmlElement(required = true)
    private final T data;
    
    @XmlElement(type = Integer.class, required = true)
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
