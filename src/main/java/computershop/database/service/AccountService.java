package computershop.database.service;

import computershop.database.dao.AccountDAO;
import computershop.database.dao.impl.AccountDAOImpl;
import computershop.database.entity.Account;

import java.util.List;

/**
 * Created by Kamil Cieślik on 05.01.2018.
 */
public class AccountService {
    private AccountDAO accountDAO = new AccountDAOImpl();

    public List<Account> getAccounts() {
        return accountDAO.getEntities();
    }

    public void saveAccount(Account account) {
        accountDAO.saveEntity(account);
    }

    public Account getAccount(int id) {
        return accountDAO.getEntity(id);
    }

    public void deleteAccount(int id) {
        accountDAO.deleteEntity(id);
    }
}
