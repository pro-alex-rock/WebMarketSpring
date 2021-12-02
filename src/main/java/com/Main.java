package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com"})
public class Main {
    ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
}
