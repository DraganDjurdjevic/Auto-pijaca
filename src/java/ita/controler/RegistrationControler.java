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
public class RegistrationControler {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegistarValidator registarValidator;

    private HashMap<Integer, UserModel> userConect = null;

    public RegistrationControler() {
        userConect = new HashMap<Integer, UserModel>();
    }

    @ModelAttribute("NewUser")
    public UserModel createUserModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new UserModel();
    }

    @RequestMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("NewUser", new UserModel());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String postRegistar(@Valid @ModelAttribute("NewUser") UserModel userModel, BindingResult bn, Model model) {

        registarValidator.validate(userModel, bn);
        if (bn.hasErrors()) {
            return "registration";
        }
        model.addAttribute("um", userModel);
        userConect.put(userModel.getUser_id(), userModel);
        getRegisterService().RegisterService(userModel);
        return "usercomplet";

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
