/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.model;


/**
 *
 * @author Dragan
 */
public class OglasModel {
    
    
    private int oglas_id;
    
    private String brend;
    private BrendModel brendModel;
    
    private String model;
    private ModelModel modelModel;
    
    private int korisnik;
    private UserModel userModel;
    
    private String tekst;
    private float cena;
    private String slika;
    private String status;
    private String godiste;

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
     * @return the brend
     */
    public String getBrend() {
        return brend;
    }

    /**
     * @param brend the brend to set
     */
    public void setBrend(String brend) {
        this.brend = brend;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the korisnik
     */
    public int getKorisnik() {
        return korisnik;
    }

    /**
     * @param korisnik the korisnik to set
     */
    public void setKorisnik(int korisnik) {
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
     * @return the brendModel
     */
    public BrendModel getBrendModel() {
        return brendModel;
    }

    /**
     * @param brendModel the brendModel to set
     */
    public void setBrendModel(BrendModel brendModel) {
        this.brendModel = brendModel;
    }

    /**
     * @return the modelModel
     */
    public ModelModel getModelModel() {
        return modelModel;
    }

    /**
     * @param modelModel the modelModel to set
     */
    public void setModelModel(ModelModel modelModel) {
        this.modelModel = modelModel;
    }

    /**
     * @return the userModel
     */
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * @param userModel the userModel to set
     */
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

   
    
}
