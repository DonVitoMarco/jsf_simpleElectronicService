package pl.thewalkingcode.service;

import pl.thewalkingcode.dao.RepairDAO;
import pl.thewalkingcode.model.Repair;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class RepairService {

    @Inject
    private RepairDAO repairDAO;

    public void create(Repair repair) {
        repairDAO.create(repair);
    }

    public List<Repair> findAll() {
        return repairDAO.findAll();
    }

}
