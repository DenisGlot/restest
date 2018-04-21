package ru.moysklad.service;

public interface IBankService {

    public BankService.ServiceResponse saveAccount(int id);

    public BankService.ServiceResponse updateSum(int id, long sum, boolean isDeposit);

    public String getAccountBalance(int id);
}
