package rs.fon.ordinacija.sistemske_operacije;

import org.springframework.beans.factory.annotation.Autowired;
import rs.fon.ordinacija.baza.DBBroker;
import rs.fon.ordinacija.baza.Poruka;
import rs.fon.ordinacija.baza.Signal;
import rs.fon.ordinacija.modeli.Grad;

public class GradSO implements OpstaSO<Grad> {

    @Autowired
    private DBBroker dbb;

    @Override
    public Signal<Grad> pretraga() {
        Signal<Grad> signal = new Signal<Grad>();
        signal.setRezultat(dbb.pretraga(Grad.class));
        if (signal.getRezultat() != null) {
            signal.setUspeh(true);
            signal.setPoruka(Poruka.GRAD_PRETRAGA);
        } else {
            signal.setUspeh(false);
            signal.setPoruka(Poruka.GRAD_PRETRAGA_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Grad> ucitaj(int id) {
        Signal<Grad> signal = new Signal<Grad>();
        signal.setRezultat(dbb.ucitaj(Grad.class, id));
        if (signal.getRezultat() != null) {
            signal.setUspeh(true);
            signal.setPoruka(Poruka.GRAD_UCITAJ);
        } else {
            signal.setUspeh(false);
            signal.setPoruka(Poruka.GRAD_UCITAJ_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Grad> sacuvaj(Grad grad) {
        Signal<Grad> signal = new Signal<Grad>();
        try {
            dbb.sacuvaj(grad);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.GRAD_SACUVAJ);
        } else {
            signal.setPoruka(Poruka.GRAD_SACUVAJ_ERR);
        }
        return signal;
    }
    
    @Override
    public Signal<Grad> izmeni(Grad grad) {
        Signal<Grad> signal = new Signal<Grad>();
        try {
            dbb.izmeni(grad);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.GRAD_IZMENI);
        } else {
            signal.setPoruka(Poruka.GRAD_IZMENI_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Grad> obrisi(int id) {
        Grad grad = (Grad) dbb.ucitaj(Grad.class, id);
        Signal<Grad> signal = new Signal<Grad>();
        try {
            dbb.obrisi(grad);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.GRAD_OBRISI);
        } else {
            signal.setPoruka(Poruka.GRAD_OBRISI_ERR);
        }
        return signal;
    }

}
