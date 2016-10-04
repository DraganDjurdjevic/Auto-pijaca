/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.domein;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brend")
public class Brend {
    @Id
    @Column(name = "brend_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int brend_id;

    @Column(name = "naziv")
    private String naziv;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brend")
    private List<Model> modeli = new ArrayList<Model>(0);

    /**
     * @return the brend_id
     */
    public int getBrend_id() {
        return brend_id;
    }

    /**
     * @param brend_id the brend_id to set
     */
    public void setBrend_id(int brend_id) {
        this.brend_id = brend_id;
    }

    /**
     * @return the naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * @param naziv the naziv to set
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * @return the modeli
     */
    public List<Model> getModeli() {
        return modeli;
    }

    /**
     * @param modeli the modeli to set
     */
    public void setModeli(List<Model> modeli) {
        this.modeli = modeli;
    }
    
}
