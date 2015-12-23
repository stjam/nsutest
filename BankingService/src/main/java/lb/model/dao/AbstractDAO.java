package lb.model.dao;

import lb.model.entity.Persistent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
/**
 * Created by root on 21.12.2015.
 */
public abstract class AbstractDAO<T extends Persistent> implements DAO<T> {

    @Autowired
    private SessionFactory sessionFactory;
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

}
