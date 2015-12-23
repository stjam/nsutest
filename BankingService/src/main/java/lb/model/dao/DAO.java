package lb.model.dao;

import lb.model.entity.Persistent;

import java.util.List;

/**
 * Created by root on 21.12.2015.
 */
public interface DAO <T extends Persistent> {
    void add(T persistent);
    void update(T persistent);
    void delete(final Long id);
    T findById(Long id);
    List<T> getList(int pageNum, int pageSize);
    List<T> getList();
}
