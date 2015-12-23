package lb.service.impl;

import lb.model.dao.ClientDAO;
import lb.model.entity.Client;
import lb.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by root on 21.12.2015.
 */


@Service("clientService")
@Transactional
public class ClientServiceImpl implements PersistenceService<Client> {

    @Autowired
    private ClientDAO clientDAO;

    @Override

    public void add(final Client persistent) {
           clientDAO.add(persistent);
    }

    @Override
    public void update(final Client persistent) {
        clientDAO.update(persistent);
    }

    @Override
    public void delete(Long id) {
        clientDAO.delete(id);
    }

    @Override
    public Client findById(Long id) {
        return clientDAO.findById(id);
    }

    @Override
    public List<Client> getList(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<Client> getList() {
        return clientDAO.getList();
    }

}
