package lb.model.dao;

import lb.model.entity.Transaction;

import java.util.List;

/**
 * Created by root on 21.12.2015.
 */
public interface TransactionDAO extends DAO<Transaction> {
    List<Transaction> getListByAccountId(Long id);
}
