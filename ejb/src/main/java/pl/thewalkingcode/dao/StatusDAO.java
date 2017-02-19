package pl.thewalkingcode.dao;

import pl.thewalkingcode.model.Device;
import pl.thewalkingcode.model.Role;
import pl.thewalkingcode.model.Status;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

public class StatusDAO {

    @Inject
    private Logger LOG;

    @Inject
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Device> findAll() {
        List<Device> list = null;
        Query query = entityManager.createQuery("SELECT s FROM Status s");
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Status find(int pk) {
        return entityManager.find(Status.class, pk);
    }

    public Status create(Status status) {
        entityManager.persist(status);
        entityManager.flush();
        return status;
    }

}
