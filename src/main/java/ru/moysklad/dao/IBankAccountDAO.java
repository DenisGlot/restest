package ru.moysklad.dao;

public interface IBankAccountDAO {
    void save(IBankAccount hBankAccount);

    IBankAccount getBankAccount(int id);
}
