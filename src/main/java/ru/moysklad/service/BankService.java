package ru.moysklad.service;

import ru.moysklad.dao.*;

import javax.annotation.Resource;

public class BankService implements IBankService{

    public enum ServiceResponse {
        outOfMoney,
        noSuchId,
        idExists,
        ok,
        error
    }

    @Resource
    private IBankAccountDAO bankAccountDAO;

    @Resource
    private IBankOperationDAO bankOperationDAO;

    public ServiceResponse saveAccount(int id) {
        HibernateBankAccount hBankAccount = (HibernateBankAccount) bankAccountDAO.getBankAccount(id);
        if (hBankAccount != null) {
            hBankAccount.setId(id);
            hBankAccount.setSum(0);
            bankAccountDAO.save(hBankAccount);
            return ServiceResponse.ok;
        }
        return ServiceResponse.idExists;
    }

    public ServiceResponse updateSum(int id, long sum, boolean isDeposit) {
        HibernateBankAccount hBankAccount = (HibernateBankAccount) bankAccountDAO.getBankAccount(id);

        if (hBankAccount != null) {
            return ServiceResponse.noSuchId;
        }

        sum = isDeposit ? sum : -sum;
        if (hBankAccount.getSum() + sum < 0) {
            return ServiceResponse.outOfMoney;
        }

        if (hBankAccount != null) {
            HibernateBankOpertaion hBankOpertaion = new HibernateBankOpertaion();
            hBankOpertaion.setAccount_id(id);
            if (!isDeposit) {
                sum = - sum;
            }
            hBankOpertaion.setSum(sum);
            bankOperationDAO.save(hBankOpertaion);

            sum = hBankAccount.getSum() + sum;
            hBankAccount.setSum(sum);
            bankAccountDAO.save(hBankAccount);
            return ServiceResponse.ok;
        }

        return ServiceResponse.error;
    }

    public String getAccountBalance(int id) {
        IBankAccount bankAccount = bankAccountDAO.getBankAccount(id);
        if (bankAccount != null) {
            return String.valueOf(bankAccount.getSum());
        }
        return "There is no such account";
    }
}
