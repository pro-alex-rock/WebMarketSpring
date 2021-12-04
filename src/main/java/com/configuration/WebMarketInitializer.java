/*
package com.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebMarketInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);
        DispatcherServlet servlet = new DispatcherServlet(context);
        */
/*servletContext.addListener(new ContextLoaderListener(context));
        context.setServletContext(servletContext);*//*

        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher",
                servlet);

        registration.setLoadOnStartup(1);
        registration.addMapping("/com/*");
    }
}
*/
