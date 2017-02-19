package pl.thewalkingcode.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s")
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idstatuses;

    @Lob
    private String description;

    @OneToMany(mappedBy = "status")
    private List<Repair> repairs;

    public Status() {
    }

    public int getIdstatuses() {
        return this.idstatuses;
    }

    public void setIdstatuses(int idstatuses) {
        this.idstatuses = idstatuses;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Repair> getRepairs() {
        return this.repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public Repair addRepair(Repair repair) {
        getRepairs().add(repair);
        repair.setStatus(this);

        return repair;
    }

    public Repair removeRepair(Repair repair) {
        getRepairs().remove(repair);
        repair.setStatus(null);

        return repair;
    }

}