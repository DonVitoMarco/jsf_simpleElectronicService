package pl.thewalkingcode.controller;

import pl.thewalkingcode.model.Role;
import pl.thewalkingcode.model.User;
import pl.thewalkingcode.util.Util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "navBean")
public class NavigationBB {

    public String goToState(String toState) {
        return toState;
    }

    public String gotoApp() {
        User user = Util.getUserFromSession();
        for (Role role : user.getRoles()) {
            if (role.getName().equals("admin")) {
                return "admin";
            }
        }
        return "home";
    }

}
