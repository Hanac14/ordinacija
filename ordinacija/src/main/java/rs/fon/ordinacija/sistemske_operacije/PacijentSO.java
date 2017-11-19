package rs.fon.ordinacija.sistemske_operacije;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import rs.fon.ordinacija.baza.DBBroker;
import rs.fon.ordinacija.baza.Poruka;
import rs.fon.ordinacija.baza.Signal;
import rs.fon.ordinacija.modeli.Pacijent;
import rs.fon.ordinacija.modeli.Intervencija;

public class PacijentSO implements OpstaSO<Pacijent> {

    @Autowired
    private DBBroker dbb;

    @Override
    public Signal<Pacijent> pretraga() {
        Signal<Pacijent> signal = new Signal<Pacijent>();
        signal.setRezultat(dbb.pretraga(Pacijent.class));
        if (signal.getRezultat() != null) {
            signal.setUspeh(true);
            signal.setPoruka(Poruka.PACIJENT_PRETRAGA);
        } else {
            signal.setUspeh(false);
            signal.setPoruka(Poruka.PACIJENT_PRETRAGA_ERR);
        }
        return signal;
    }

    
    public List<Pacijent> pretraga(String pretraga) {
        List<Pacijent> lista= new ArrayList<>();
        
        lista =dbb.pretragaPacijenata(pretraga);
        return lista;
        
    }
    @Override
    public Signal<Pacijent> ucitaj(int id) {
        Signal<Pacijent> signal = new Signal<>();
        signal.setRezultat(dbb.ucitaj(Pacijent.class, id));
        if (signal.getRezultat() != null) {
            signal.setUspeh(true);
            signal.setPoruka(Poruka.PACIJENT_UCITAJ);
            System.out.println("*********************uspesno ucitao pacijenta ");
        } else {
            signal.setUspeh(false);
            signal.setPoruka(Poruka.PACIJENT_UCITAJ_ERR);
            System.out.println("*********************neu"
                    + "uspesno ucitao pacijenta ");
        }
        return signal;
    }
    
    
    public Pacijent ucitajPAcijent(int id) {
        Pacijent p = new Pacijent();
        try {
            p=   (Pacijent) dbb.ucitaj(Pacijent.class, id);
        } catch (Exception e) {
        e.printStackTrace();
        }
        return p;
    }

    @Override
    public Signal<Pacijent> sacuvaj(Pacijent pacijent) {
        Signal<Pacijent> signal = new Signal<Pacijent>();
        try {
            dbb.sacuvaj(pacijent);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.PACIJENT_SACUVAJ);
        } else {
            signal.setPoruka(Poruka.PACIJENT_SACUVAJ_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Pacijent> izmeni(Pacijent pacijent) {
        Signal<Pacijent> signal = new Signal<Pacijent>();
        try {
            dbb.izmeni(pacijent);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.PACIJENT_IZMENI);
        } else {
            signal.setPoruka(Poruka.PACIJENT_IZMENI_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Pacijent> obrisi(int id) {
        Pacijent pacijent = (Pacijent) dbb.ucitaj(Pacijent.class, id);
        Signal<Pacijent> signal = new Signal<Pacijent>();
        try {
            dbb.obrisi(pacijent);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.PACIJENT_OBRISI);
        } else {
            signal.setPoruka(Poruka.PACIJENT_OBRISI_ERR);
        }
        return signal;
    }

}
