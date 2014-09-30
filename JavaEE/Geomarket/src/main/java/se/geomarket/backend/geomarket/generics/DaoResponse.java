/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

import java.util.Set;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class DaoResponse {

    private Set<String> validationErrors;
    private String id;

    public DaoResponse(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public DaoResponse(String id) {
        this.id = id;
    }

    public Boolean isOk() {
        return validationErrors == null;
    }

    public Set<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
