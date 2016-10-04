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
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Dragan
 */
@Controller
public class UploadFileControler {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private OglasService oglasService;

    @RequestMapping("/uploadfile")
    public String getUpload() {
        return "uploadfile";
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public String Save(HttpServletRequest request, HttpSession session, UserModel userModel, Model model) throws FileUploadException, Exception {

        String l = session.getAttribute("username").toString();
        int i = l.length();
        String username = l.substring(0, i - 3);
        userModel = getUserByUserName(username);
        String path = request.getRealPath("/resources/images");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\resources\\images";
        String putanja = "/resources/images";
        DiskFileItemFactory d = new DiskFileItemFactory();
        ServletFileUpload s = new ServletFileUpload(d);
        List<FileItem> list = s.parseRequest(request);
        for (FileItem f : list) {
            if (f.isFormField() == false) {

                f.write(new File(path + "/" + f.getName()));
                putanja = putanja + "/" + f.getName();
            }
        }
        userModel.setSlika(putanja);

        List<OglasModel> oglasiZaOdobrenje = getOglasService().status();
        List<UserModel> users = getRegisterService().getAllUsers();
        List<OglasModel> oglasi = getOglasService().getAllOglasById(userModel.getUser_id());
        model.addAttribute("oglasi", oglasi);
        model.addAttribute("user", userModel);
        model.addAttribute("users", users);
        getRegisterService().EditUser(userModel);
        if (userModel.getRola() == 1) {
            return "pageuser";
        }
         model.addAttribute("status", oglasiZaOdobrenje);
        return "admin";
    }

    public UserModel getUserByUserName(String username) {

        List<UserModel> users = getRegisterService().getAllUsers();
        UserModel userModel = new UserModel();
        for (UserModel user : users) {
            if (user.getUsername().equals(username)) {

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
