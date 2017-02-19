package pl.thewalkingcode.dao;

import pl.thewalkingcode.model.Repair;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;


public class RepairDAO {

    @Inject
    private Logger LOG;

    @Inject
    EntityManager entityManager;

    public void create(Repair repair) {
        entityManager.persist(repair);
    }

    @SuppressWarnings("unchecked")
    public List<Repair> findAll() {
        List<Repair> list = null;
        Query query = entityManager.createQuery("SELECT r FROM Repair r");
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
