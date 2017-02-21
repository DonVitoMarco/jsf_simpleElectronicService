package pl.thewalkingcode.service;

import org.mindrot.jbcrypt.BCrypt;
import pl.thewalkingcode.dao.*;
import pl.thewalkingcode.model.*;
import pl.thewalkingcode.util.Resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;


@Stateless
public class UserService implements Serializable {

    @Inject
    private Logger LOG;

    @Inject
    private UserDAO userDAO;

    @Inject
    private RoleDAO roleDAO;

    @Inject
    private RepairDAO repairDAO;

    @Inject
    private DeviceDAO deviceDAO;

    @Inject
    private StatusDAO statusDAO;

    public User checkUser(String email, String password) {
        User user = userDAO.findUserByEmail(email);
        if (user == null) {
            return null;
        }
        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User find(int pk) {
        return userDAO.find(pk);
    }

    public void merge(User user) {
        userDAO.merge(user);
    }

    public User create(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        Role role = roleDAO.findRoleByName("user");
        if (role != null) {
            user.setRoles(new ArrayList<>());
            user.getRoles().add(role);
        }

        userDAO.create(user);
        return userDAO.findUserByEmail(email);
    }

    public boolean isExistEmail(String email) {
        User user = userDAO.findUserByEmail(email);
        return user != null;
    }

    public void addRepair(Device device, String desc, int userId) {
        User user = find(userId);

        Repair repair = new Repair();
        Date date = new Date();
        repair.setDate(new Timestamp(date.getTime()));
        repair.setUser(user);
        repair.setDescription(desc);
        repair.setDevice(device);

        Status status = new Status();
        status.setDescription("NEW");
        status = statusDAO.create(status);

        repair.setStatus(status);
        repairDAO.create(repair);

        if(!user.getDevices().contains(device)) {
            user.getDevices().add(device);
            userDAO.merge(user);
        }
    }

}
