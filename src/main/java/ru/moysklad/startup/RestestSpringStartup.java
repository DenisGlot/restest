package ru.moysklad.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RestestSpringStartup {

    private static final Logger logger = LoggerFactory.getLogger(RestestSpringStartup.class);
    private static final String CONTEXT_LOCATION = "/webapp-context-r.xml";

    public static void main(String[] p_args) throws Exception {
        try {
            AbstractApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_LOCATION);
            context.start();
            context.registerShutdownHook();
        } catch (BeansException e) {
            logger.error("RestestSpringStartup configration error", e);
        }
    }
}
