package ru.moysklad.service;

import ru.moysklad.dao.*;

import javax.annotation.Resource;

public class BankService implements IBankService{

    public enum ServiceResponse {
        outOfMoney {
            @Override
            public String message() {
                return "There is no money!";
            }
        },
        noSuchId {
            @Override
            public String message() {
                return "There is no such id!";
            }
        },
        idExists {
            @Override
            public String message() {
                return "Such id already exist!";
            }
        },
        ok {
            @Override
            public String message() {
                return "Success!";
            }
        };

        public abstract String message();
    }

    @Resource
    private IBankAccountDAO bankAccountDAO;

    @Resource
    private IBankOperationDAO bankOperationDAO;

    public ServiceResponse saveAccount(int id) {
        HibernateBankAccount hBankAccount = (HibernateBankAccount) bankAccountDAO.getBankAccount(id);
        if (hBankAccount == null) {
            hBankAccount = new HibernateBankAccount();
            hBankAccount.setId(id);
            hBankAccount.setSum(0);
            bankAccountDAO.save(hBankAccount);
            return ServiceResponse.ok;
        }
        return ServiceResponse.idExists;
    }

    public ServiceResponse updateSum(int id, long sum, boolean isDeposit) {
        HibernateBankAccount hBankAccount = (HibernateBankAccount) bankAccountDAO.getBankAccount(id);

        if (hBankAccount == null) {
            return ServiceResponse.noSuchId;
        }

        sum = isDeposit ? sum : -sum;
        long newAccountSum = hBankAccount.getSum() + sum;
        if (newAccountSum < 0) {
            return ServiceResponse.outOfMoney;
        }
        hBankAccount.setSum(newAccountSum);
        bankAccountDAO.save(hBankAccount);


        HibernateBankOpertaion hBankOpertaion = new HibernateBankOpertaion();
        hBankOpertaion.setAccount_id(id);
        hBankOpertaion.setSum(sum);
        bankOperationDAO.save(hBankOpertaion);
        return ServiceResponse.ok;
    }

    public String getAccountBalance(int id) {
        IBankAccount bankAccount = bankAccountDAO.getBankAccount(id);
        if (bankAccount != null) {
            return String.valueOf(bankAccount.getSum());
        }
        return ServiceResponse.noSuchId.message();
    }
}
