package lb.model.dao;

import lb.model.entity.Client;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 21.12.2015.
 */
@Repository("clientDAO")
public class ClientDAOImpl extends AbstractDAO<Client> implements ClientDAO {

    @Override
    public void add(final Client persistent) {
           getSession().saveOrUpdate(persistent);
    }

    @Override
    public void update(Client persistent) {
        getSession().update(persistent);
    }

    @Override
    public void delete(Long id) {
        Query qr = getSession().createSQLQuery("DELETE t from bank.transaction as t inner join " +
                " bank.account as a on a.id=t.account_id where a.client_id="+id);
        qr.executeUpdate();

        qr = getSession().createQuery("delete from Account as a where a.client.id=:id");
        qr.setParameter("id", id);
        qr.executeUpdate();
        qr = getSession().createQuery("delete From Client where id=:param");
        qr.setParameter("param", id);
        qr.executeUpdate();
    }

    @Override
    public Client findById(Long id) {
        final Query qr = getSession().createQuery("From Client where id=:param");
        qr.setLong("param", id);
        return (Client) qr.uniqueResult();
    }

    @Override
    public List<Client> getList() {
        final Query qr = getSession().createQuery("FROM Client");
        return qr.list();
    }

    @Override
    public List<Client> getList(int pageNum, int pageSize) {
        return null;
    }
}
