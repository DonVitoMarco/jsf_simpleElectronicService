package pl.thewalkingcode.controller;

import pl.thewalkingcode.model.User;
import pl.thewalkingcode.service.UserService;
import pl.thewalkingcode.util.Util;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@RequestScoped
@ManagedBean(name = "loginBean")
public class LoginBB {

    @EJB
    private UserService userService;

    private String username;
    private String password;


    public String loginService() {
        User user = userService.checkUser(username, password);
        if (user != null) {
            HttpSession session = Util.getSession();
            session.setAttribute("user", user);
            if (Util.isAdmin(user)) {
                return "admin";
            }
            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login", "Please Try Again"));
            return "login";
        }
    }

    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "login";
    }

    public boolean isLogged() {
        HttpSession session = Util.getSession();
        return (session.getAttribute("user") != null);
    }

    public String getUsernameFromSession() {
        User user = Util.getUserFromSession();
        return user.getEmail();
    }


    /*GETTERS AND SETTERS*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
