package com.revature.webstore.services;

import com.revature.webstore.DatabaseAccess.AccountDAO;
import com.revature.webstore.models.Account;

import java.util.List;

public class AccountService {

    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public void register(Account account) {
        accountDAO.save(account);
    }

    public List<Account> getAll() {
        return accountDAO.getAll();
    }

    public Account getAccountByColumnValue(String column, String value){
        return accountDAO.getAccountByColumnValue(column, value);
    }

    public boolean getExists(String column, String input){
        return accountDAO.getExistsInColumnByString(column, input);
    }

}
