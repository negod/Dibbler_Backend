/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.io.Serializable;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class BaseDto implements Serializable {

    private String extId;

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

}
