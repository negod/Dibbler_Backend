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
        resources.add(se.dibbler.backend.service.CategoryService.class);
        resources.add(se.dibbler.backend.service.CompanyService.class);
        resources.add(se.dibbler.backend.service.CompanyUsersService.class);
        resources.add(se.dibbler.backend.service.EventService.class);
        resources.add(se.dibbler.backend.service.EventTypeService.class);
        resources.add(se.dibbler.backend.service.FilterService.class);
        resources.add(se.dibbler.backend.service.IdService.class);
        resources.add(se.dibbler.backend.service.IndexService.class);
        resources.add(se.dibbler.backend.service.LanguageService.class);
        resources.add(se.dibbler.backend.service.LocationGroupService.class);
        resources.add(se.dibbler.backend.service.LocationService.class);
        resources.add(se.dibbler.backend.service.MovementService.class);
        resources.add(se.dibbler.backend.service.PublishedEventService.class);
        resources.add(se.dibbler.backend.service.RolesService.class);
        resources.add(se.dibbler.backend.service.SettingService.class);
        resources.add(se.dibbler.backend.service.UsersService.class);
        //resources.add(se.dibbler.backend.service.filter.CORSFilterTest.class);
    }

}
