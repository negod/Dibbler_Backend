/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@javax.ws.rs.ApplicationPath("resource")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        //Swagger
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(se.dibbler.backend.ws.CategoryService.class);
        resources.add(se.dibbler.backend.ws.CompanyService.class);
        resources.add(se.dibbler.backend.ws.CompanyUsersService.class);
        resources.add(se.dibbler.backend.ws.EventService.class);
        resources.add(se.dibbler.backend.ws.EventTypeService.class);
        resources.add(se.dibbler.backend.ws.FilterService.class);
        resources.add(se.dibbler.backend.ws.IdService.class);
        resources.add(se.dibbler.backend.ws.IndexService.class);
        resources.add(se.dibbler.backend.ws.LanguageService.class);
        resources.add(se.dibbler.backend.ws.MovementService.class);
        resources.add(se.dibbler.backend.ws.RolesService.class);
        resources.add(se.dibbler.backend.ws.SettingService.class);
        resources.add(se.dibbler.backend.ws.UsersService.class);
    }

}
