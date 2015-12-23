package lb.model.dao;

import lb.model.entity.Account;

/**
 * Created by root on 21.12.2015.
 */
public interface AccountDAO extends DAO<Account> {
       public Account findByAccountNumber(final String accountNumber);
}
