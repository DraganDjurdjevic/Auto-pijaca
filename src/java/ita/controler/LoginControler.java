/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.controler;

import ita.model.OglasModel;
import ita.model.UserModel;
import ita.service.OglasService;
import ita.service.RegisterService;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
public class LoginControler {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private OglasService oglasService;

    @Autowired
    private LoginValid loginValid;

    private HashMap<Integer, UserModel> loginConect = null;

    @ModelAttribute("UserLogin")
    public UserModel createUserModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new UserModel();
    }

    @RequestMapping("/login")
    public String getLogin(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "login";
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@Valid @ModelAttribute("UserLogin") UserModel userModel, BindingResult bn, Model model, HttpServletRequest request, HttpSession sesion) {

        loginValid.validate(userModel, bn);
        if (bn.hasErrors()) {
            return "login";
        }
        List<UserModel> users = getRegisterService().getAllUsers();
        
        sesion = request.getSession(true);
        sesion.setAttribute("username", userModel.getUsername() + "log");
        userModel = getUserByUserName(userModel);
        List<OglasModel> oglasi = getOglasService().getAllOglasById(userModel.getUser_id());
        List<OglasModel> oglasiZaOdobrenje = getOglasService().status();
        model.addAttribute("status", oglasiZaOdobrenje);
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("user", userModel);
        model.addAttribute("users", users);

        if (userModel.getRola() == 1) {
            
            return "pageuser";
        }
        return "admin";

    }

    @RequestMapping(value = "/izlaz")
    public String getIzlaz(HttpServletRequest request, HttpSession sesion) {
        sesion.invalidate();
        return "redirect:login";

    }

    public UserModel getUserByUserName(UserModel userModel) {

        List<UserModel> users = getRegisterService().getAllUsers();

        for (UserModel user : users) {
            if (user.getUsername().equals(userModel.getUsername())) {
                userModel.setUser_id(user.getUser_id());
                userModel.setName(user.getName());
                userModel.setEmail(user.getEmail());
                userModel.setUsername(user.getUsername());
                userModel.setPassword(user.getPassword());
                userModel.setRola(user.getRola());
                userModel.setSlika(user.getSlika());
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

    /**
     * @return the oglasService
     */
    public OglasService getOglasService() {
        return oglasService;
    }

    /**
     * @param oglasService the oglasService to set
     */
    public void setOglasService(OglasService oglasService) {
        this.oglasService = oglasService;
    }

}
