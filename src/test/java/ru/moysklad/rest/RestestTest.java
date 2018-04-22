package ru.moysklad.rest;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.glassfish.jersey.servlet.ServletContainer;
import com.sun.jersey.test.framework.JerseyTest;;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import java.net.URISyntaxException;


public class RestestTest extends JerseyTest{

    @Override
    public AppDescriptor configure() {
        return new WebAppDescriptor.Builder("ru.moysklad.rest")
                .contextParam("contextConfigLocation", "classpath:/spring-context.xml")
                .contextPath("/").servletClass(ServletContainer.class)
                .contextListenerClass(ContextLoaderListener.class)
                .requestListenerClass(RequestContextListener.class)
                .build();
    }

    @Test
    public void createCostumerTest() throws URISyntaxException {
        WebResource webResource = resource();
        String response = webResource.path("/bankaccount/99999/").post(String.class);
        Assert.assertTrue("Success!".equals(response));
    }

    @Test
    public void depositCustomerTest() {
        WebResource webResource = resource();
        String response = webResource.path("bankaccount/99999/deposit/100").put(String.class);
        Assert.assertTrue("Success!".equals(response));
    }

    @Test
    public void withdrawCustomerTest() {
        WebResource webResource = resource();
        String response = webResource.path("bankaccount/99999/withdraw/50").put(String.class);
        Assert.assertTrue("Success!".equals(response));
    }

    @Test
    public void getBalanceTest() {
        WebResource webResource = resource();
        String response = webResource.path("bankaccount/99999/balance").get(String.class);
        Assert.assertTrue("50".equals(response));
    }

}