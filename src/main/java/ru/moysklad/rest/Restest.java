package ru.moysklad.rest;

import org.springframework.stereotype.Component;

import ru.moysklad.service.IBankService;
import ru.moysklad.util.BankUtils;

import javax.annotation.Resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static ru.moysklad.service.BankService.ServiceResponse;

@Component
@Path("/bankaccount")
public class Restest {

    @Resource
    private IBankService bankService;

    @POST
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String createCustomer(@PathParam("id") String id) {
        if (BankUtils.validateAccount(id)) {
            int intId = Integer.parseInt(id);
            ServiceResponse serviceResponse = bankService.saveAccount(intId);
            return serviceResponse.message();
        }
        return "Your id is not valid";
    }

    @PUT
    @Path("/{id}/deposit/{sum}")
    @Produces(MediaType.TEXT_PLAIN)
    public String depositCustomer(@PathParam("id") String id, @PathParam("sum") String sum) {
        if (BankUtils.validateAccount(id) && BankUtils.validateSum(sum)) {
            int intId = Integer.parseInt(id);
            long longSum = Long.parseLong(sum);
            ServiceResponse serviceResponse = bankService.updateSum(intId, longSum, true);
            return serviceResponse.message();
        }
        return "Your id is not valid";
    }

    @PUT
    @Path("/{id}/withdraw/{sum}")
    @Produces(MediaType.TEXT_PLAIN)
    public String withdrawCustomer(@PathParam("id") String id, @PathParam("sum") String sum) {
        if (BankUtils.validateAccount(id) && BankUtils.validateSum(sum)) {
            int intId = Integer.parseInt(id);
            long longSum = Long.parseLong(sum);
            ServiceResponse serviceResponse = bankService.updateSum(intId, longSum, false);
            return serviceResponse.message();
        }
        return "Your id is not valid";
    }

    @GET
    @Path("/{id}/balance")
    @Produces(MediaType.TEXT_PLAIN)
    public String getBalance(@PathParam("id") String id) {
        if (BankUtils.validateAccount(id)) {
            int intId = Integer.parseInt(id);
            return bankService.getAccountBalance(intId);
        }
        return "There is no such account";
    }

    @GET
    @Path("/error404")
    public String get404() {
        return "Sorry, there is no such page!";
    }

}