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
 * @author black
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.unicundi.serviciowebconsultas.controllers.ConsultasController.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.controllers.MedicosController.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.BadRequestExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.ConstraintViolationExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.EmptyModelExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.ExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.IntegrityExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.ModelNotFoundExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.NotAllowedExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.NotFoundExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.NotSupportedExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.NullPointerExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.TransactionRolledbackLocalExceptionFilter.class);
        resources.add(co.edu.unicundi.serviciowebconsultas.exceptions.filters.WebApplicationExceptionFilter.class);
    }
    
}
