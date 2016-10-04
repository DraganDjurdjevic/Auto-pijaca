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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author Dragan
 */
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "getAllModelByBrend",
	query = "select * from model m where m.brend_id = :brend",
        resultClass=Model.class)
})

@Entity
@Table(name = "model")
public class Model {
    
    @Id
    @Column(name = "model_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int model_id;

    @Column(name = "naziv")
    private String naziv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brend_id", nullable = false)
    private Brend brend;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model")
    private List<Oglas> model = new ArrayList<Oglas>(0);

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
     * @return the brend
     */
    public Brend getBrend() {
        return brend;
    }

    /**
     * @param brend the brend to set
     */
    public void setBrend(Brend brend) {
        this.brend = brend;
    }

    /**
     * @return the model
     */
    public List<Oglas> getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(List<Oglas> model) {
        this.model = model;
    }
    
}
