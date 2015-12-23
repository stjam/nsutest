package lb.model.dao;

import lb.model.entity.Transaction;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 21.12.2015.
 */
@Repository("transactionDAO")
public class TransactionDAOImpl extends AbstractDAO<Transaction> implements TransactionDAO {

    @Autowired
    @Qualifier(value = "accountDAO")
    private AccountDAO accountDAO;

    @Override
    public void add(Transaction persistent) {

        getSession().saveOrUpdate(persistent);
    }

    @Override
    public void update(Transaction persistent) {
        getSession().update(persistent);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Transaction findById(Long id) {
        return null;
    }

    @Override
    public List<Transaction> getList(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<Transaction> getList() {
        final Query qr = getSession().createQuery("FROM Transaction");
        return qr.list();
    }

    @Override
    public List<Transaction> getListByAccountId(Long id) {
        final Query qr = getSession().createQuery("FROM Transaction AS t WHERE t.account.id=:param");
        qr.setParameter("param", id);
        return qr.list();

    }
}
