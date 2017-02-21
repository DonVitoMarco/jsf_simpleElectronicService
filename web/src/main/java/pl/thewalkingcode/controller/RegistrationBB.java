package pl.thewalkingcode.controller;

import pl.thewalkingcode.dto.RegistrationDto;
import pl.thewalkingcode.model.User;
import pl.thewalkingcode.service.UserService;
import pl.thewalkingcode.util.Util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.logging.Logger;


@ManagedBean(name = "registrationBean")
public class RegistrationBB {

    @EJB
    private UserService userService;

    @Inject
    private Logger LOG;

    private RegistrationDto registrationDto;

    private static final String REG_COMPLETED = "Registration Completed! Now you can log in.";
    private static final String USER_EXIST = "User already exist.";
    private static final String INTERNAL_ERROR = "Internal server error";

    @PostConstruct
    public void postConstruct() {
        registrationDto = new RegistrationDto();
    }

    public RegistrationDto getRegistrationDto() {
        return registrationDto;
    }

    public void setRegistrationDto(RegistrationDto registrationDto) {
        this.registrationDto = registrationDto;
    }

    public String sendRegisterData() {
        Util.clearMessages();

        if(userService.isExistEmail(registrationDto.getEmail())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, USER_EXIST, null));
        }

        User user = userService.create(registrationDto.getEmail(), registrationDto.getPassword());
        if(user != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, REG_COMPLETED, null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, INTERNAL_ERROR, null));
        }
        return "login";
    }

}
