/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.utils;

import javax.ws.rs.core.Response;

/**
 *
 * @author Joakim
 */
public class ResponseUtil {

    public static Response getMethodNotSupportedError() {
        return Response.ok("Method not supported").build();
    }

    public static Response getMethodNotSupportedError(String message) {
        return Response.ok("Method not supported.  " + message).build();
    }
    
     public static Response getErrorMessage() {
       return Response.serverError().status(Response.Status.NO_CONTENT).build();
    }

}
