package ru.moysklad.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;

public class RestestTest extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig(Restest.class);
    }

    @Test
    public void createCostumerTest() {
        target("bankaccount/99999").request();
        String response = target("bankaccount/99999/balance").request().get(String.class);
        Assert.assertTrue("0".equals(response));
    }

    @Test
    public void depositCustomerTest() {
        target("bankaccount/99999/deposit/100").request();
        String response = target("bankaccount/99999/balance").request().get(String.class);
        Assert.assertTrue("100".equals(response));
    }

    @Test
    public void withdrawCustomerTest() {
        target("bankaccount/99999/withdraw/1").request();
        String response = target("bankaccount/99999/balance").request().get(String.class);
        Assert.assertTrue("99".equals(response));
    }

}