package rs.fon.ordinacija.sistemske_operacije;

import org.springframework.beans.factory.annotation.Autowired;
import rs.fon.ordinacija.baza.DBBroker;
import rs.fon.ordinacija.baza.Poruka;
import rs.fon.ordinacija.baza.Signal;
import rs.fon.ordinacija.modeli.Usluga;

public class UslugaSO implements OpstaSO<Usluga> {

    @Autowired
    private DBBroker dbb;

    @Override
    public Signal<Usluga> pretraga() {
        Signal<Usluga> signal = new Signal<Usluga>();
        signal.setRezultat(dbb.pretraga(Usluga.class));
        if (signal.getRezultat() != null) {
            signal.setUspeh(true);
            signal.setPoruka(Poruka.USLUGA_PRETRAGA);
        } else {
            signal.setUspeh(false);
            signal.setPoruka(Poruka.USLUGA_PRETRAGA_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Usluga> ucitaj(int id) {
        Signal<Usluga> signal = new Signal<Usluga>();
        signal.setRezultat(dbb.ucitaj(Usluga.class, id));
        if (signal.getRezultat() != null) {
            signal.setUspeh(true);
            signal.setPoruka(Poruka.USLUGA_UCITAJ);
        } else {
            signal.setUspeh(false);
            signal.setPoruka(Poruka.USLUGA_UCITAJ_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Usluga> sacuvaj(Usluga usluga) {
        Signal<Usluga> signal = new Signal<Usluga>();
        try {
            dbb.sacuvaj(usluga);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.USLUGA_SACUVAJ);
        } else {
            signal.setPoruka(Poruka.USLUGA_SACUVAJ_ERR);
        }
        return signal;
    }
    
    @Override
    public Signal<Usluga> izmeni(Usluga usluga) {
        Signal<Usluga> signal = new Signal<Usluga>();
        try {
            dbb.izmeni(usluga);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.USLUGA_IZMENI);
        } else {
            signal.setPoruka(Poruka.USLUGA_IZMENI_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Usluga> obrisi(int id) {
        Usluga usluga = (Usluga) dbb.ucitaj(Usluga.class, id);
        Signal<Usluga> signal = new Signal<Usluga>();
        try {
            dbb.obrisi(usluga);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.USLUGA_OBRISI);
        } else {
            signal.setPoruka(Poruka.USLUGA_OBRISI_ERR);
        }
        return signal;
    }

}
