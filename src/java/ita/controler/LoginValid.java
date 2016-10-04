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
public class LoginValid implements Validator {

    @Autowired
    private RegisterService registerService;

    @Override
    public boolean supports(Class<?> userLogin) {
        return UserModel.class.equals(userLogin);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserModel userModel = (UserModel) o;

        UserModel um = null;
        um = getUser(userModel);
        boolean provera = proveraUserName(um);

        if (um.getUser_id() == 0) {
            if (provera) {
                errors.rejectValue("password", "negativeValue", new Object[]{"'password'"}, "Password nije tacan");

            }
            if (!provera) {
                errors.rejectValue("username", "negativeValue", new Object[]{"'username'"}, "Username sa tim imenom ne postoji");
            }

        }
    }

    public boolean proveraUserName(UserModel userModel) {
        List<UserModel> users = getRegisterService().getAllUsers();
        for (UserModel user : users) {
            if (user.getUsername().equals(userModel.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public UserModel getUser(UserModel userModel) {

        List<UserModel> users = getRegisterService().getAllUsers();

        for (UserModel user : users) {
            if (user.getUsername().equals(userModel.getUsername()) && user.getPassword().equals(userModel.getPassword())) {
                userModel.setUser_id(user.getUser_id());
                userModel.setName(user.getName());
                userModel.setEmail(user.getEmail());
                userModel.setRola(user.getRola());
            }

        }
        return userModel;
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
