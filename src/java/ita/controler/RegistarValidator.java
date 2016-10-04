/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.controler;

import ita.model.UserModel;
import ita.service.RegisterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Dragan
 */
@Component
public class RegistarValidator implements Validator {

    @Autowired
    private RegisterService registerService;

    @Override
    public boolean supports(Class<?> userModel) {
        return UserModel.class.equals(userModel);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserModel userModel = (UserModel) o;
        boolean provera = proveraUserName(userModel);
        if (!provera) {
            errors.rejectValue("username", "negativeValue", new Object[]{"'username'"}, "Username sa tim imenom vec postoji");
        }

    }

    public boolean proveraUserName(UserModel userModel) {
        List<UserModel> users = getRegisterService().getAllUsers();
        for (UserModel user : users) {
            if (user.getUsername().equals(userModel.getUsername())) {
                return false;
            }
        }
        return true;
    }

    

    /**
     * @return the registerService
     */
    public RegisterService getRegisterService() {
        return registerService;
    }

    /**
     * @param registerService the registerService to set
     */
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

}
