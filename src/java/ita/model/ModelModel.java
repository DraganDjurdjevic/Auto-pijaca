/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.model;


public class ModelModel {
    
    private int model_id;
    private String naziv;
   


    public String getNaziv() {
        return naziv;
    }


    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * @return the model_id
     */
    public int getModel_id() {
        return model_id;
    }

    /**
     * @param model_id the model_id to set
     */
    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    
}
