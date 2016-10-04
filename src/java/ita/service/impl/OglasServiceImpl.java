/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.service.impl;

import ita.dao.OglasDao;
import ita.dao.RegisterDao;
import ita.domein.Brend;
import ita.domein.Model;
import ita.domein.Oglas;
import ita.domein.User;
import ita.model.BrendModel;
import ita.model.ModelModel;
import ita.model.OglasModel;
import ita.service.OglasService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dragan
 */
@Service
public class OglasServiceImpl implements OglasService {

    @Autowired
    private OglasDao oglasDao;
    @Autowired
    private RegisterDao registerDao;

    @Override
    public void saveOglas(OglasModel oglasModel) {
        Oglas oglas = new Oglas();
        Brend brend = new Brend();
        Model model = new Model();
        oglas.setStatus("nije_odobren");
        oglas.setTekst(oglasModel.getTekst());
        oglas.setGodiste(oglasModel.getGodiste());
        oglas.setCena(oglasModel.getCena());

        String brend_naziv = oglasModel.getBrend();
        brend = getBrendByName(brend_naziv);
        oglas.setBrend(brend);

        int id_korisnik = oglasModel.getKorisnik();
        User user = getRegisterDao().getUserById(id_korisnik);
        oglas.setKorisnik(user);

        String naziv_model = oglasModel.getModel();
        model = getModelByName(naziv_model);
        oglas.setModel(model);

        getOglasDao().saveOglas(oglas);

    }

    @Override
    public List<BrendModel> getAllBrend() {
        List<BrendModel> brendsModel = new ArrayList<>();
        List<Brend> brends = getOglasDao().getAllBrebd();
        for (Brend brend : brends) {
            brendsModel.add(repackBrend(brend));
        }
        return brendsModel;
    }

    @Override
    public List<ModelModel> getAllModel() {
        List<ModelModel> modelsModels = new ArrayList<>();
        List<Model> models = getOglasDao().getAllModel();
        for (Model model : models) {
            modelsModels.add(repackModel(model));
        }

        return modelsModels;
    }

    public BrendModel repackBrend(Brend brend) {
        BrendModel brendModel = new BrendModel();
        brendModel.setBrend_id(brend.getBrend_id());
        brendModel.setNaziv(brend.getNaziv());

        return brendModel;
    }

    public ModelModel repackModel(Model model) {
        ModelModel modelModel = new ModelModel();
        modelModel.setModel_id(model.getModel_id());
        modelModel.setNaziv(model.getNaziv());

        return modelModel;
    }

    public OglasModel repackOglas(Oglas oglas) {
        OglasModel oglasModel = new OglasModel();
        oglasModel.setBrend(oglas.getBrend().getNaziv());
        oglasModel.setModel(oglas.getModel().getNaziv());
        oglasModel.setCena(oglas.getCena());
        oglasModel.setGodiste(oglas.getGodiste());
        oglasModel.setOglas_id(oglas.getOglas_id());
        oglasModel.setStatus(oglas.getStatus());
        oglasModel.setSlika(oglas.getSlika());
        oglasModel.setTekst(oglas.getTekst());
        oglasModel.setKorisnik(oglas.getKorisnik().getUser_id());

        return oglasModel;
    }

    public Brend getBrendByName(String naziv) {
        List<Brend> brends = getOglasDao().getAllBrebd();
        Brend brend = new Brend();
        for (Brend b : brends) {
            if (b.getNaziv().equals(naziv)) {
                brend.setBrend_id(b.getBrend_id());
                brend.setNaziv(b.getNaziv());
            }
        }
        return brend;
    }

    public Model getModelByName(String naziv) {
        List<Model> models = getOglasDao().getAllModel();
        Model model = new Model();
        for (Model b : models) {
            if (b.getNaziv().equals(naziv)) {
                model.setNaziv(b.getNaziv());
                model.setModel_id(b.getModel_id());
            }
        }
        return model;
    }

    /**
     * @return the oglasDao
     */
    public OglasDao getOglasDao() {
        return oglasDao;
    }

    /**
     * @param oglasDao the oglasDao to set
     */
    public void setOglasDao(OglasDao oglasDao) {
        this.oglasDao = oglasDao;
    }

    /**
     * @return the registerDao
     */
    public RegisterDao getRegisterDao() {
        return registerDao;
    }

    /**
     * @param registerDao the registerDao to set
     */
    public void setRegisterDao(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    @Override
    public List<OglasModel> getAllOglas() {
        List<OglasModel> oglasiModel = new ArrayList<OglasModel>();
        List<Oglas> oglasi = getOglasDao().getAllOglas();
        for (Oglas o : oglasi) {
            oglasiModel.add(repackOglas(o));
        }
        return oglasiModel;
    }

    @Override
    public List<ModelModel> getAllModelByBrend(String brend) {
        List<Model> modeli = getOglasDao().getAllModelByBrend(brend);
        List<ModelModel> modeliModel = new ArrayList<>(modeli.size());
        for (Model model : modeli) {
            modeliModel.add(repackModel(model));
        }
        return modeliModel;
    }

    @Override
    public List<OglasModel> getAllOglasById(Integer id) {
        List<Oglas> oglasi = getOglasDao().getAllOglasById(id);
        List<OglasModel> oglasModel = new ArrayList<>();

        for (Oglas oglas : oglasi) {
            System.out.println("Oglasi " + oglas.getTekst());
            oglasModel.add(repackOglas(oglas));
        }
        return oglasModel;
    }

    @Override
    public List<OglasModel> status() {
        List<Oglas> oglasi = getOglasDao().status();
        List<OglasModel> oglasiModel = new ArrayList<OglasModel>();
        for (Oglas oglas : oglasi) {
            oglasiModel.add(repackOglas(oglas));
        }

        return oglasiModel;
    }

    @Override
    public void deleteOglas(OglasModel oglasModel) {
        Oglas oglas = new Oglas();
        oglas.setOglas_id(oglasModel.getOglas_id());

        getOglasDao().deleteOglas(oglas);
    }

    @Override
    public OglasModel getOglasById(Integer id) {
        Oglas oglas = getOglasDao().getOglasById(id);
        OglasModel oglasModel = repackOglas(oglas);

        return oglasModel;
    }

    @Override
    public void updateOglas(OglasModel oglasModel) {
        Oglas oglas = new Oglas();
        Brend brend = new Brend();
        Model model = new Model();

        String brend_naziv = oglasModel.getBrend();
        brend = getBrendByName(brend_naziv);
        oglas.setBrend(brend);

        int id_korisnik = oglasModel.getKorisnik();
        User user = getRegisterDao().getUserById(id_korisnik);
        oglas.setKorisnik(user);

        String naziv_model = oglasModel.getModel();
        model = getModelByName(naziv_model);
        oglas.setModel(model);

        oglas.setStatus("odobren");
        oglas.setTekst(oglasModel.getTekst());
        oglas.setGodiste(oglasModel.getGodiste());
        oglas.setCena(oglasModel.getCena());
        oglas.setOglas_id(oglasModel.getOglas_id());

        getOglasDao().updateOglas(oglas);
    }

    @Override
    public List<OglasModel> OglasiHome() {
        List<Oglas> oglasi = getOglasDao().OglasiHome();
        List<OglasModel> oglasiModel = new ArrayList<>();
        for (Oglas oglas : oglasi) {
            oglasiModel.add(repackOglas(oglas));
        }
        return oglasiModel;
    }

    @Override
    public List<OglasModel> pretragaHome(OglasModel oglasModel) {

        Oglas oglas = new Oglas();
        Brend brend = new Brend();
        Model model = new Model();

        String brend_id = oglasModel.getBrend();

        int b_id = Integer.parseInt(brend_id);
        brend = getBrendById(b_id);
        oglas.setBrend(brend);
        
        String model_id = oglasModel.getModel();
        int m_id = Integer.parseInt(model_id);
        model = getModelById(m_id);
        oglas.setModel(model);
        
        oglas.setCena(oglasModel.getCena());

        System.out.println("Naziv je : " + oglas.getModel().getNaziv());

        List<OglasModel> oglasiModel = new ArrayList<OglasModel>();
        List<Oglas> oglasi = getOglasDao().pretragaHome(oglas);
        for (Oglas o : oglasi) {

            oglasiModel.add(repackOglas(o));

        }

        return oglasiModel;
    }

    public Brend getBrendById(int id) {
        List<Brend> brends = getOglasDao().getAllBrebd();
        Brend brend = new Brend();
        for (Brend b : brends) {
            if (b.getBrend_id() == (id)) {
                brend.setBrend_id(b.getBrend_id());
                brend.setNaziv(b.getNaziv());
            }
        }
        return brend;
    }

    public Model getModelById(int id) {
        List<Model> models = getOglasDao().getAllModel();
        Model model = new Model();
        for (Model b : models) {
            if (b.getModel_id() == id) {
                model.setNaziv(b.getNaziv());
                model.setModel_id(b.getModel_id());
            }
        }
        return model;
    }

}
