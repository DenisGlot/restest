package ru.moysklad.dao;

public class HibernateBankOperationDAO extends GenericDAO<HibernateBankOpertaion, Integer> implements IBankOperationDAO {

    @Override
    public void save(IBankOpertaion bankOpertaion) {
        makePersistent((HibernateBankOpertaion) bankOpertaion);
    }
}
