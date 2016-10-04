/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.controler;

import ita.model.BrendModel;
import ita.model.ModelModel;
import ita.model.OglasModel;
import ita.service.OglasService;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.Produces;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dragan
 */
@Controller
public class HomeControler {

    @Autowired
    private OglasService oglasService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initForm(Model model, HttpServletRequest request, HttpSession session) {
        OglasModel oglasModel = new OglasModel();
        List<OglasModel> oglasi = getOglasService().OglasiHome();
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("Oglas", oglasModel);
        initBrendList(model);
        initModelList(model);
        return "home";
    }

    @RequestMapping(value = "/onPromenaBrenda", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<ModelModel> getModel(HttpServletRequest request, HttpServletResponse response) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        String brend = request.getParameter("brend");
        List<ModelModel> lista = getOglasService().getAllModelByBrend(brend);
        return lista;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postLogin(@Valid @ModelAttribute("Oglas") OglasModel oglasModel, BindingResult bn, Model model) {

        List<OglasModel> oglasi = getOglasService().pretragaHome(oglasModel);
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("Oglas", oglasModel);
        initBrendList(model);
        initModelList(model);
        if (bn.hasErrors()) {
            return "login";
        }

        return "home";
    }

    private void initBrendList(Model model) {
        List<BrendModel> brendsModel = getOglasService().getAllBrend();
        model.addAttribute("brendovi", brendsModel);
    }

    private void initModelList(Model model) {
        List<String> modelList = new ArrayList<String>();
        List<ModelModel> modelsModel = getOglasService().getAllModel();
        for (ModelModel modelModel : modelsModel) {
            modelList.add(modelModel.getNaziv());
        }

        model.addAttribute("modeli", modelList);

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
