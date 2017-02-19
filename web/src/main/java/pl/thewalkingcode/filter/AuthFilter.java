package pl.thewalkingcode.filter;

import pl.thewalkingcode.model.Role;
import pl.thewalkingcode.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "authFilter", urlPatterns = "*.xhtml")
public class AuthFilter implements Filter {

    private static final List<String> allow = new ArrayList<>(Arrays.asList("/login.xhtml", "/register.xhtml", "/public/", "javax.faces.resource"));
    private static final List<String> adminPages = new ArrayList<>(Arrays.asList("/admin.xhtml"));
    private static final List<String> userPages = new ArrayList<>(Arrays.asList("/home.xhtml", "/details.xhtml"));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            String reqUri = req.getRequestURI();

            if (requestContains(allow, reqUri)) {
                chain.doFilter(request, response);
            } else {
                if ((ses != null && ses.getAttribute("user") != null)) {
                    User user = (User) ses.getAttribute("user");
                    if (requestContains(adminPages, reqUri)) {
                        if (isAdmin(user)) {
                            chain.doFilter(request, response);
                        } else {
                            res.sendRedirect(req.getContextPath() + "/login.xhtml");
                        }
                    } else if (requestContains(userPages, reqUri)) {
                        if (isUser(user)) {
                            chain.doFilter(request, response);
                        } else {
                            res.sendRedirect(req.getContextPath() + "/login.xhtml");
                        }
                    } else if (requestContains(allow, reqUri)) {
                        chain.doFilter(request, response);
                    }
                } else {
                    res.sendRedirect(req.getContextPath() + "/login.xhtml");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAdmin(User user) {
        for (Role role : user.getRoles()) {
            if (role.getName().equals("admin")) {
                return true;
            }
        }
        return false;
    }

    private boolean isUser(User user) {
        for (Role role : user.getRoles()) {
            if (role.getName().equals("user")) {
                return true;
            }
        }
        return false;
    }

    private boolean requestContains(List<String> list, String req) {
        for (String s : list) {
            if (req.contains(s)) {
                return true;
            }
        }
        return false;
    }

}
