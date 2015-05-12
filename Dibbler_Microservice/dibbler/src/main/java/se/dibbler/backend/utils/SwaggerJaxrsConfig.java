/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Joakim
 */

@WebServlet(name = "SwaggerJaxrsConfig", loadOnStartup = 1)
public class SwaggerJaxrsConfig extends HttpServlet {
    
    /*@Override
    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
            SwaggerConfig swaggerConfig = new SwaggerConfig();
            ConfigFactory.setConfig(swaggerConfig);
            swaggerConfig.setBasePath("http://109.74.2.250:7200/dibbler/resource");
            swaggerConfig.setApiVersion("1.0.0");
            ScannerFactory.setScanner(new DefaultJaxrsScanner());
            ClassReaders.setReader(new DefaultJaxrsApiReader());
        } catch (ServletException e) {
            System.out.println(e.getMessage());
        }
    }*/
    
}
