/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.domein;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author Dragan
 */

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "getAllOglasById",
	query = "select * from oglas o where o.korisnik_id = :id",
        resultClass=Oglas.class),
        
        @NamedNativeQuery(
	name = "status",
	query = "select * from oglas o where o.status = :nije_odobren",
        resultClass=Oglas.class),
        
        @NamedNativeQuery(
	name = "oglasiHome",
	query = "select * from oglas o where o.status = :odobren",
        resultClass=Oglas.class),
        
        @NamedNativeQuery(
	name = "pretragaHome",
	query = "select * from oglas o where o.brend_id = :brend and o.model_id = :model and o.cena <= :cena and o.status = :odobren",
        resultClass=Oglas.class),
        
        @NamedNativeQuery(
	name = "pretragaCena",
	query = "select * from oglas o where o.cena <= :cena and o.status = :odobren",
        resultClass=Oglas.class)
        
})

@Entity
@Table(name = "oglas")
public class Oglas {

    @Id
    @Column(name = "oglas_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int oglas_id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brend_id", nullable = false)
    private Brend brend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private User korisnik;

    @Column(name = "tekst")
    private String tekst;

    @Column(name = "cena")
    private float cena;
    
    @Column(name = "godiste")
    private String godiste;

    @Column(name = "slika")
    private String slika;

    @Column(name = "status")
    private String status;

    /**
     * @return the oglas_id
     */
    public int getOglas_id() {
        return oglas_id;
    }

    /**
     * @param oglas_id the oglas_id to set
     */
    public void setOglas_id(int oglas_id) {
        this.oglas_id = oglas_id;
    }

    /**
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * @return the korisnik
     */
    public User getKorisnik() {
        return korisnik;
    }

    /**
     * @param korisnik the korisnik to set
     */
    public void setKorisnik(User korisnik) {
        this.korisnik = korisnik;
    }

    /**
     * @return the tekst
     */
    public String getTekst() {
        return tekst;
    }

    /**
     * @param tekst the tekst to set
     */
    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    /**
     * @return the cena
     */
    public float getCena() {
        return cena;
    }

    /**
     * @param cena the cena to set
     */
    public void setCena(float cena) {
        this.cena = cena;
    }

    /**
     * @return the slika
     */
    public String getSlika() {
        return slika;
    }

    /**
     * @param slika the slika to set
     */
    public void setSlika(String slika) {
        this.slika = slika;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the godiste
     */
    public String getGodiste() {
        return godiste;
    }

    /**
     * @param godiste the godiste to set
     */
    public void setGodiste(String godiste) {
        this.godiste = godiste;
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
}
