package pl.thewalkingcode.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDevice;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date warrantydate;

    @OneToMany(mappedBy = "device")
    private List<Repair> repairs;

    @ManyToMany(mappedBy = "devices")
    private List<User> users;

    public Device() {
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int iddevice) {
        this.idDevice = iddevice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getWarrantydate() {
        return this.warrantydate;
    }

    public void setWarrantydate(Date warrantydate) {
        this.warrantydate = warrantydate;
    }

    public List<Repair> getRepairs() {
        return this.repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public Repair addRepair(Repair repair) {
        getRepairs().add(repair);
        repair.setDevice(this);

        return repair;
    }

    public Repair removeRepair(Repair repair) {
        getRepairs().remove(repair);
        repair.setDevice(null);

        return repair;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}