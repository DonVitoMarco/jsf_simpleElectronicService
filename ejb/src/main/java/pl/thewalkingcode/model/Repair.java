package pl.thewalkingcode.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@NamedQuery(name = "Repair.findAll", query = "SELECT r FROM Repair r")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrepairs;

    private Timestamp date;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "devices_iddevices")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "statuses_idstatuses")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "users_idusers")
    private User user;

    public Repair() {
    }

    public int getIdrepairs() {
        return this.idrepairs;
    }

    public void setIdrepairs(int idrepairs) {
        this.idrepairs = idrepairs;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}