/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class BaseDto extends BaseDtoEmpty {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
