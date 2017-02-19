package pl.thewalkingcode.dao;

import pl.thewalkingcode.model.Device;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;


public class DeviceDAO {

    @Inject
    private Logger LOG;

    @Inject
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Device> findAll() {
        List<Device> list = null;
        Query query = entityManager.createQuery("SELECT d FROM Device d");
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Device find(int pk) {
        return entityManager.find(Device.class, pk);
    }

    public void merge(Device device) {
        entityManager.merge(device);
    }

}
