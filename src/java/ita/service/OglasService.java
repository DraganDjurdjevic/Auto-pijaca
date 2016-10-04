/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.service;

import ita.domein.Oglas;
import ita.model.BrendModel;
import ita.model.ModelModel;
import ita.model.OglasModel;
import java.util.List;

/**
 *
 * @author Dragan
 */
public interface OglasService {

    public void saveOglas(OglasModel oglasModel);

    public void deleteOglas(OglasModel oglasModel);
    public void updateOglas(OglasModel oglasModel);

    public OglasModel getOglasById(Integer id);

    public List<OglasModel> getAllOglas();

    public List<BrendModel> getAllBrend();

    public List<ModelModel> getAllModel();

    public List<OglasModel> getAllOglasById(Integer id);

    public List<ModelModel> getAllModelByBrend(String brend);

    public List<OglasModel> status();
    
    public List<OglasModel> OglasiHome();
    
    public List<OglasModel> pretragaHome(OglasModel oglasModel);

}
