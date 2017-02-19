package pl.thewalkingcode.util;

import pl.thewalkingcode.model.Role;
import pl.thewalkingcode.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;


public class Util {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static int getUserId() {
        User user = (User) Util.getSession().getAttribute("user");
        return user.getIdusers();
    }

    public static User getUserFromSession() {
        return (User) Util.getSession().getAttribute("user");
    }

    public static void clearMessages() {
        FacesContext context = FacesContext.getCurrentInstance();
        Iterator<FacesMessage> it = context.getMessages();
        while (it.hasNext()) {
            FacesMessage message = it.next();
            if (message.getSeverity() == FacesMessage.SEVERITY_WARN) {
                it.remove();
            }
        }
    }

    public static boolean isAdmin(User user) {
        for (Role role : user.getRoles()) {
            if (role.getName().equals("admin")) {
                return true;
            }
        }
        return false;
    }

}
