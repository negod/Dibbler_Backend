/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.service.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@WebFilter
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("INIT CORS FILTER");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            chain.doFilter(req, res);

            /*responseCtx.getHeaders().add("Access-Control-Allow-Origin", "*");
             responseCtx.getHeaders().add("Access-Control-Allow-Credentials", "true");
             responseCtx.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
             chain.doFilter(request, response);*/
        } catch (IOException e) {
            System.out.println("CORS FILTER EXCEPTION");

        } catch (ServletException e) {
            System.out.println("CORS FILTER EXCEPTION");
        }
    }

    @Override
    public void destroy() {
        System.out.println("DESTROY CORS FILTER");
    }

}