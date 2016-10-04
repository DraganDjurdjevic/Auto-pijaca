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
public class GalerySliderControler {
 
    @RequestMapping("galeryslaider")
    public String getGalerySliderControler(){
        return "galeryslaider";
    }
    
}
