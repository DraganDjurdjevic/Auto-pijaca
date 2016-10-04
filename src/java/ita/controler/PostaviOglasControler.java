/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.controler;

import ita.model.BrendModel;
import ita.model.ModelModel;
import ita.model.OglasModel;
import ita.model.UserModel;
import ita.service.OglasService;
import ita.service.RegisterService;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Dragan
 */
@Controller
public class PostaviOglasControler {

    @Autowired
    private OglasService oglasService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegistarValidator registarValidator;

    @RequestMapping(value = "/postavioglas", method = RequestMethod.GET)
    public String initForm(Model model, HttpServletRequest request, HttpSession session) {
        OglasModel oglasModel = new OglasModel();
        model.addAttribute("PostaviOglas", oglasModel);
        initBrendList(model);
        initModelList(model);
        initGodisteList(model);
        return "postavioglas";
    }

    @RequestMapping(value = "/postavioglas", method = RequestMethod.POST)
    public String postRegistar(@Valid @ModelAttribute("PostaviOglas") OglasModel oglasModel, BindingResult bn, Model model, HttpSession session) {

        String l = session.getAttribute("username").toString();
        int i = l.length();
        String username = l.substring(0, i - 3);
        UserModel userModel = getUserByUserName(username);
        oglasModel.setKorisnik(userModel.getUser_id());

        getOglasService().saveOglas(oglasModel);
        List<OglasModel> oglasi = getOglasService().getAllOglasById(userModel.getUser_id());
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("oglas", oglasModel);
        model.addAttribute("user", userModel);

        return "pageuser";

    }

    @RequestMapping(value = "/deleteOglas", method = RequestMethod.GET)
    public String getUserDelete(@RequestParam Integer id, OglasModel oglasModel, HttpSession session, Model model) {
        if (session.getAttribute("username") != null) {

            oglasModel = getOglasService().getOglasById(id);
            getOglasService().deleteOglas(oglasModel);
            String l = session.getAttribute("username").toString();
            int i = l.length();
            String username = l.substring(0, i - 3);
            UserModel userModel = getUserByUserName(username);
            model.addAttribute("user", userModel);
            List<UserModel> users = getRegisterService().getAllUsers();
            model.addAttribute("users", users);
            List<OglasModel> oglasi = getOglasService().getAllOglasById(userModel.getUser_id());
            model.addAttribute("oglasi", oglasi);
            List<OglasModel> oglasiZaOdobrenje = getOglasService().status();
            model.addAttribute("status", oglasiZaOdobrenje);
            if (userModel.getRola() == 1) {
                return "pageuser";
            }
        }
        return "admin";

    }

    @RequestMapping(value = "/odobriOglas", method = RequestMethod.GET)
    public String odobriOglas(@RequestParam Integer id, OglasModel oglasModel, HttpSession session, Model model) {
        if (session.getAttribute("username") != null) {

            oglasModel = getOglasService().getOglasById(id);
            getOglasService().updateOglas(oglasModel);
            String l = session.getAttribute("username").toString();
            int i = l.length();
            String username = l.substring(0, i - 3);
            UserModel userModel = getUserByUserName(username);
            model.addAttribute("user", userModel);
            List<UserModel> users = getRegisterService().getAllUsers();
            model.addAttribute("users", users);
            List<OglasModel> oglasi = getOglasService().getAllOglasById(userModel.getUser_id());
            model.addAttribute("oglasi", oglasi);
            List<OglasModel> oglasiZaOdobrenje = getOglasService().status();
            model.addAttribute("status", oglasiZaOdobrenje);
            if (userModel.getRola() == 1) {
                return "pageuser";
            }
        }
        return "admin";
    }

    private void initBrendList(Model model) {
        List<String> brendlList = new ArrayList<String>();
        List<BrendModel> brendsModel = getOglasService().getAllBrend();
        for (BrendModel brendModel : brendsModel) {
            brendlList.add(brendModel.getNaziv());
        }

        model.addAttribute("brendovi", brendlList);

    }

    private void initModelList(Model model) {
        List<String> modelList = new ArrayList<String>();
        List<ModelModel> modelsModel = getOglasService().getAllModel();
        for (ModelModel modelModel : modelsModel) {
            modelList.add(modelModel.getNaziv());
        }

        model.addAttribute("modeli", modelList);

    }

    private void initGodisteList(Model model) {
        List<String> godisteList = new ArrayList<String>();

        for (Integer i = 1990; i <= 2016; i++) {
            String godiste = i.toString();
            godisteList.add(godiste);
        }

        model.addAttribute("godiste", godisteList);

    }

    public UserModel getUserByUserName(String username) {

        List<UserModel> users = getRegisterService().getAllUsers();
        UserModel userModel = new UserModel();
        for (UserModel user : users) {
            if (user.getUsername().equals(username)) {
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
