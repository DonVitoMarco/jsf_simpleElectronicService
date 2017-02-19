package pl.thewalkingcode.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrole;

    private String name;
	
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    public int getIdrole() {
        return this.idrole;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}