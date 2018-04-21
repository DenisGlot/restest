package ru.moysklad.rest;

import ru.moysklad.service.IBankService;
import ru.moysklad.util.BankUtils;

import javax.annotation.Resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bankaccount")
public class Restest {

    @Resource
    private IBankService bankService;

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer(@PathParam("id") String id) {
        if (BankUtils.validateAccount(id)) {
            int intId = Integer.parseInt(id);
            bankService.saveAccount(intId);
        }
    }

    @PUT
    @Path("/{id}/deposit{sum}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void depositCustomer(@PathParam("id") String id, @PathParam("sum") String sum) {
        if (BankUtils.validateAccount(id) && BankUtils.validateSum(sum)) {
            int intId = Integer.parseInt(id);
            long longSum = Long.parseLong(sum);
            bankService.updateSum(intId, longSum, true);
        }
    }

    @PUT
    @Path("/{id}/withdraw/{sum}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void withdrawCustomer(@PathParam("id") String id, @PathParam("sum") String sum) {
        if (BankUtils.validateAccount(id) && BankUtils.validateSum(sum)) {
            int intId = Integer.parseInt(id);
            long longSum = Long.parseLong(sum);
            bankService.updateSum(intId, longSum, false);
        }
    }

    @GET
    @Path("/{id}/balance")
    public String getBalance(@PathParam("id") String id) {
        if (BankUtils.validateAccount(id)) {
            int intId = Integer.parseInt(id);
            return bankService.getAccountBalance(intId);
        }
        return "There is no such account";
    }

}