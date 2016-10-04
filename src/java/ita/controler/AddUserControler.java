/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.controler;

import ita.model.UserModel;
import ita.service.RegisterService;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Dragan
 */
@Controller
public class AddUserControler {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegistarValidator registarValidator;

    private HashMap<Integer, UserModel> userConect = null;

    public AddUserControler() {
        userConect = new HashMap<Integer, UserModel>();
    }

    @ModelAttribute("addUser")
    public UserModel createUserModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new UserModel();
    }

    @RequestMapping("/adduser")
    public String GetAddUser(Model model) {
        model.addAttribute("addUser", new UserModel());
        return "adduser";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String postRegistar(@Valid @ModelAttribute("addUser") UserModel userModel, BindingResult bn, Model model, HttpSession session) {

        getRegistarValidator().validate(userModel, bn);
        if (bn.hasErrors()) {
            return "adduser";
        }
        String l = session.getAttribute("username").toString();
        int i = l.length();
        String username = l.substring(0, i - 3);
        userModel.setUsername(username);
        userModel = getUserByUserName(userModel);
        model.addAttribute("user", userModel);
        List<UserModel> users = getRegisterService().getAllUsers();
        model.addAttribute("users", users);

        userConect.put(userModel.getUser_id(), userModel);
        getRegisterService().RegisterService(userModel);
        return "admin";

    }

    /**
     * @return the registerService
     */
     public UserModel getUserByUserName(UserModel userModel) {

        List<UserModel> users = getRegisterService().getAllUsers();

        for (UserModel user : users) {
            if (user.getUsername().equals(userModel.getUsername())) {
                userModel.setUser_id(user.getUser_id());
                userModel.setName(user.getName());
                userModel.setUsername(user.getUsername());
                userModel.setPassword(user.getPassword());
                userModel.setRola(user.getRola());
            }
        }
        return userModel;
    }
    public RegisterService getRegisterService() {
        return registerService;
    }

    /**
     * @param registerService the registerService to set
     */
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * @return the registarValidator
     */
    public RegistarValidator getRegistarValidator() {
        return registarValidator;
    }

    /**
     * @param registarValidator the registarValidator to set
     */
    public void setRegistarValidator(RegistarValidator registarValidator) {
        this.registarValidator = registarValidator;
    }
}
