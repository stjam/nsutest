package lb.service.impl;

import lb.model.dao.AccountDAO;
import lb.model.dao.TransactionDAO;
import lb.model.entity.Account;
import lb.model.entity.Transaction;
import lb.model.enums.TransactionType;
import lb.service.PersistenceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by root on 21.12.2015.
 */

@Service("accountService")
@PropertySource(value = {"classpath:bankmodel.properties"})
@Transactional
public class AccountServiceImpl implements PersistenceService<Account> {

    private static final Logger LOGGER = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private Environment env;

    @Override
    public void add(final Account persistent) {
        accountDAO.add(persistent);
        if(persistent.getBalance()>0){
              final Transaction transaction = new Transaction();
              transaction.setTransactionType(TransactionType.INCOME);
              transaction.setAccount(persistent);
              transaction.setTargetAccount(persistent.getAccountNumber());
              transaction.setOperationValue(persistent.getBalance());
              transaction.setInfo(env.getRequiredProperty("account.income.operation"));
              transactionDAO.add(transaction);
           }

    }

    @Override
    public void update(final Account persistent) {
        accountDAO.update(persistent);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public List<Account> getList(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<Account> getList() {
        return accountDAO.getList();
    }
}
