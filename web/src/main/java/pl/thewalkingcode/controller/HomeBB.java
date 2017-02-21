package pl.thewalkingcode.controller;

import pl.thewalkingcode.model.Device;
import pl.thewalkingcode.model.User;
import pl.thewalkingcode.service.DeviceService;
import pl.thewalkingcode.service.UserService;
import pl.thewalkingcode.util.Util;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

@SessionScoped
@ManagedBean(name = "homeBean")
public class HomeBB {

    @EJB
    private DeviceService deviceService;

    @EJB
    private UserService userService;


    private String addDevice;
    private String descDevice;

    private Device selectedDevice;


    public List<Device> getUserDevices() {
        User user = userService.find(Util.getUserId());
        return user.getDevices();
    }

    public List<Device> getAllDevices() {
        return deviceService.findAll();
    }

    public void addDeviceToRepair() {
        Util.clearMessages();
        int id = tryParseId(addDevice);
        if (id == -1) {
            return;
        }

        Device device = deviceService.find(id);
        if (device == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Device does not exist", "Please Try Again"));
            return;
        }

        if (device.getWarrantydate().before(new Date())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warranty date", null));
            return;
        }

        userService.addRepair(device, descDevice, Util.getUserId());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Devices and repair added", null));
    }

    private int tryParseId(String id) {
        try {
            return Integer.parseInt(addDevice);
        } catch (NumberFormatException nfe) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid id", "Please Try Again"));
            return -1;
        }
    }


    /*GETTERS AND SETTERS*/
    public String getAddDevice() {
        return addDevice;
    }

    public void setAddDevice(String addDevice) {
        this.addDevice = addDevice;
    }

    public String getDescDevice() {
        return descDevice;
    }

    public void setDescDevice(String descDevice) {
        this.descDevice = descDevice;
    }

    public Device getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(Device selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

}
