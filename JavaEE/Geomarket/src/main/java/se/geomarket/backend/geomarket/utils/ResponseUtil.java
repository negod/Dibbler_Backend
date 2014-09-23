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
        return Response.ok("Method not appliccable").build();
    }

}
