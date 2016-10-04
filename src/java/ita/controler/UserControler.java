/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.controler;

import ita.dao.OglasDao;
import ita.model.OglasModel;
import ita.model.UserModel;
import ita.service.OglasService;
import ita.service.RegisterService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Dragan
 */
@Controller
public class UserControler {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private OglasService oglasService;

    @RequestMapping(value = "/usercomplet", method = RequestMethod.POST)
    public String getUserControler() {

        return "usercomplet";
    }

    @RequestMapping(value = "/pageuser", method = RequestMethod.GET)
    public String getUserPageControler(HttpServletRequest request, HttpSession session, Model model, UserModel userModel) {
        if (session.getAttribute("username") != null) {
            String l = session.getAttribute("username").toString();
            int i = l.length();
            String username = l.substring(0, i - 3);
            userModel.setUsername(username);
            userModel = getUserByUserName(userModel);
            List<UserModel> users = getRegisterService().getAllUsers();

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
        return "login";
    }

    @RequestMapping(value = "/pageuser", method = RequestMethod.POST)
    public String postPageUser(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "login";
        }
        return "login";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public String GetEditUser(@RequestParam Integer id, Model model, HttpSession session, UserModel userModel) {
        if (session.getAttribute("username") != null) {
            String l = session.getAttribute("username").toString();
            int i = l.length();
            String username = l.substring(0, i - 3);
            userModel.setUsername(username);
            UserModel admin = getUserByUserName(userModel);
            List<OglasModel> oglasiZaOdobrenje = getOglasService().status();
            List<OglasModel> oglasi = getOglasService().getAllOglasById(userModel.getUser_id());
            model.addAttribute("oglasi", oglasi);
            model.addAttribute("status", oglasiZaOdobrenje);
            model.addAttribute("editUser", getRegisterService().getUserById(id));
            if (admin.getRola() != 1) {
                return "edituser";
            }
            return "redirect:login";
        }
        return "redirect:login";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String PostEditUser(@Valid @ModelAttribute("editUser") UserModel userModel, BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            return "edituser";
        }
        getRegisterService().EditUser(userModel);
        String l = session.getAttribute("username").toString();
        int i = l.length();
        String username = l.substring(0, i - 3);
        userModel.setUsername(username);
        userModel = getUserByUserName(userModel);
        model.addAttribute("user", userModel);
        List<UserModel> users = getRegisterService().getAllUsers();
        model.addAttribute("users", users);
        List<OglasModel> oglasiZaOdobrenje = getOglasService().status();
        model.addAttribute("status", oglasiZaOdobrenje);
        
        return "admin";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String getUserDelete(@RequestParam Integer id, UserModel userModel, HttpSession session, Model model) {
        if (session.getAttribute("username") != null) {

            getRegisterService().deleteUser(getRegisterService().getUserById(id));
            String l = session.getAttribute("username").toString();
            int i = l.length();
            String username = l.substring(0, i - 3);
            userModel.setUsername(username);
            userModel = getUserByUserName(userModel);
            model.addAttribute("user", userModel);
            List<UserModel> users = getRegisterService().getAllUsers();
            List<OglasModel> oglasiZaOdobrenje = getOglasService().status();
            model.addAttribute("status", oglasiZaOdobrenje);
            model.addAttribute("users", users);
        }
        return "admin";

    }

    public UserModel getUserByUserName(UserModel userModel) {

        List<UserModel> users = getRegisterService().getAllUsers();

        for (UserModel user : users) {
            if (user.getUsername().equals(userModel.getUsername())) {
                userModel.setUser_id(user.getUser_id());
                userModel.setName(user.getName());
                userModel.setUsername(user.getUsername());
                userModel.setEmail(user.getEmail());
                userModel.setPassword(user.getPassword());
                userModel.setRola(user.getRola());
                userModel.setSlika(user.getSlika());
            }
        }
        return userModel;
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

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

    /**
     * @return the oglasDao
     */
}
