package pl.thewalkingcode.controller;

import org.primefaces.event.RowEditEvent;
import pl.thewalkingcode.model.Repair;
import pl.thewalkingcode.service.RepairService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@SessionScoped
@ManagedBean(name = "adminBean")
public class AdminBB {

    @Inject
    private RepairService repairService;

    private List<Repair> filteredRepairs;


    public List<Repair> getAllRepairs() {
        return repairService.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        Repair repair = (Repair) event.getObject();

        FacesMessage msg = new FacesMessage("Device Edited", "device serial number: " + (Integer.toString(repair.getDevice().getIdDevice())));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        Repair repair = (Repair) event.getObject();

        FacesMessage msg = new FacesMessage("Edit Cancelled", "device serial number: " + (Integer.toString(repair.getIdrepairs())));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    /* GETTER AND SETTER*/
    public List<Repair> getFilteredRepairs() {
        return filteredRepairs;
    }

    public void setFilteredRepairs(List<Repair> filteredRepairs) {
        this.filteredRepairs = filteredRepairs;
    }

}
