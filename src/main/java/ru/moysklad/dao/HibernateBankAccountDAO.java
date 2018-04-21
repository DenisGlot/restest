package ru.moysklad.dao;

public class HibernateBankAccountDAO extends GenericDAO<HibernateBankAccount, Integer> implements IBankAccountDAO {

    @Override
    public void save(IBankAccount bankAccount) {
        makePersistent((HibernateBankAccount) bankAccount);
    }

    @Override
    public IBankAccount getBankAccount(int id) {
        return findById(id);
    }
}
