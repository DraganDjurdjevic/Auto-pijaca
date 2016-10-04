/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.dao;

import ita.domein.Brend;
import ita.domein.Model;
import ita.domein.Oglas;
import java.util.List;

/**
 *
 * @author Dragan
 */
public interface OglasDao {
    
    public void saveOglas(Oglas oglas);
    public void deleteOglas(Oglas oglas);
    public Oglas getOglasById(Integer id);
    public List<Oglas> getAllOglas();
    public void updateOglas(Oglas oglas);
    
    public List<Brend> getAllBrebd();
    public List<Model>getAllModel();
    
    public List<Oglas> getAllOglasById(int id);
    public List<Model> getAllModelByBrend(String brend);
    public List<Oglas> status();
    
    public List<Oglas> OglasiHome();
    public List<Oglas> pretragaHome(Oglas oglas);
    
}
