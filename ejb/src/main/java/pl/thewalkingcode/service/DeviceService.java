package pl.thewalkingcode.service;

import pl.thewalkingcode.dao.DeviceDAO;
import pl.thewalkingcode.model.Device;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class DeviceService {

    @Inject
    private DeviceDAO deviceDAO;

    public List<Device> findAll() {
        List<Device> list = deviceDAO.findAll();
        return list;
    }

    public Device find(int pk) {
        return deviceDAO.find(pk);
    }

}
