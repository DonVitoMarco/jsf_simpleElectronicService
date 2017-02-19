package pl.thewalkingcode.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusers;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Repair> repairs;

    @ManyToMany
    @JoinTable(name = "user_has_device",
            joinColumns = {@JoinColumn(name = "users_idusers")},
            inverseJoinColumns = {@JoinColumn(name = "devices_iddevices")})
    private List<Device> devices;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_has_role",
            joinColumns = {@JoinColumn(name = "users_idusers")},
            inverseJoinColumns = {@JoinColumn(name = "role_idrole")})
    private List<Role> roles;

    public User() {
    }

    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}