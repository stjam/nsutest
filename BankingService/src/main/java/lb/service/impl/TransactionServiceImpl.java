package lb.service.impl;

import lb.model.dao.AccountDAO;
import lb.model.dao.TransactionDAO;
import lb.model.entity.Account;
import lb.model.entity.Transaction;
import lb.model.enums.TransactionType;
import lb.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by root on 21.12.2015.
 */


@Service("transactionService")
@PropertySource(value = {"classpath:bankmodel.properties"})
@Transactional
public class TransactionServiceImpl implements PersistenceService<Transaction> {

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private Environment env;

    @Override
    public void add(Transaction persistent) {
      final Double operationValue = persistent.getOperationValue();
      if(operationValue > 0) {
          transactionDAO.add(persistent);
          if (persistent.getTransactionType().equals(TransactionType.OUTCOME)) {
              Account from = accountDAO.findById(persistent.getAccount().getId());
              System.out.println(from.getBalance());
              from.setBalance(new Double(from.getBalance().doubleValue() - operationValue));
              System.out.println(from.getBalance());
              accountDAO.add(from);

              Account targetAccount = accountDAO.findByAccountNumber(persistent.getTargetAccount());
              if (targetAccount != null) {
                  System.out.println(targetAccount.getBalance());
                  targetAccount.setBalance(new Double(targetAccount.getBalance().doubleValue() + operationValue));
                  System.out.println(targetAccount.getBalance());
                  accountDAO.update(targetAccount);
                  final Transaction transaction = new Transaction();
                  transaction.setAccount(targetAccount);
                  transaction.setTransactionType(TransactionType.INCOME);
                  transaction.setInfo(env.getRequiredProperty("account.transfer.operation"));
                  transactionDAO.add(transaction);
              }
              persistent.setInfo(env.getRequiredProperty("account.outcome.operation"));
          }

      }
    }

    @Override
    public void update(Transaction persistent) {
        transactionDAO.update(persistent);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Transaction findById(Long id) {
        return null;
    }

    public List<Transaction> getListByAccountId(final Long id){
          return transactionDAO.getListByAccountId(id);
    }
    @Override
    public List<Transaction> getList(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<Transaction> getList() {
        return transactionDAO.getList();
    }
}
