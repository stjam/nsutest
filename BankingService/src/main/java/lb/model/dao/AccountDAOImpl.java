package lb.model.dao;

import lb.model.entity.Account;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 21.12.2015.
 */

@Repository("accountDAO")
public class AccountDAOImpl extends AbstractDAO<Account> implements AccountDAO {

    @Override
    public void add(final Account persistent) {
        getSession().saveOrUpdate(persistent);
    }

    @Override
    public void update(final Account persistent) {
          String accountNumber = persistent.getAccountNumber();
          Double balance = persistent.getBalance();
          Long clientId = persistent.getClient().getId();
          Long accountId = persistent.getId();
          final Query qr = getSession().createQuery("update Account set accountNumber=:accountNumber," +
                  " balance=:balance, client_id=:clientId where id=:accountId");
          qr.setParameter("accountNumber",accountNumber);
          qr.setParameter("balance", balance);
          qr.setParameter("clientId", clientId);
          qr.setParameter("accountId",accountId);
          qr.executeUpdate();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Account findById(final Long id) {
        final Query qr = getSession().createQuery("From Account as c where c.id=:param");
        qr.setParameter("param", id);
        return (Account) qr.uniqueResult();
    }

    public Account findByAccountNumber(final String number) {
        final Query qr = getSession().createQuery("From Account as a where a.accountNumber=:param");
        qr.setParameter("param", number);
        return (Account)qr.uniqueResult();
    }

    @Override
    public List<Account> getList(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<Account> getList() {
        final Query qr = getSession().createQuery("From Account");

        return qr.list();
    }
}
