/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Dragan
 */
@Controller
public class GaleryControler {

    @RequestMapping("/galery")
    public String getGalery() {

        return "galery";
    }
}
