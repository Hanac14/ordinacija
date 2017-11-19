package rs.fon.ordinacija.sistemske_operacije;

import org.springframework.beans.factory.annotation.Autowired;
import rs.fon.ordinacija.baza.DBBroker;
import rs.fon.ordinacija.baza.Poruka;
import rs.fon.ordinacija.baza.Signal;
import rs.fon.ordinacija.modeli.Intervencija;
import rs.fon.ordinacija.modeli.StavkaIntervencije;

public class IntervencijaSO implements OpstaSO<Intervencija> {

    @Autowired
    private DBBroker dbb;

    @Override
    public Signal<Intervencija> pretraga() {
        Signal<Intervencija> signal = new Signal<Intervencija>();
        signal.setRezultat(dbb.pretraga(Intervencija.class));
        if (signal.getRezultat() != null) {
            signal.setUspeh(true);
            signal.setPoruka(Poruka.INTERVENCIJA_PRETRAGA);
        } else {
            signal.setUspeh(false);
            signal.setPoruka(Poruka.INTERVENCIJA_PRETRAGA_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Intervencija> ucitaj(int id) {
        Signal<Intervencija> signal = new Signal<Intervencija>();
        signal.setRezultat(dbb.ucitaj(Intervencija.class, id));
        if (signal.getRezultat() != null) {
            signal.setUspeh(true);
            signal.setPoruka(Poruka.INTERVENCIJA_UCITAJ);
        } else {
            signal.setUspeh(false);
            signal.setPoruka(Poruka.INTERVENCIJA_UCITAJ_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Intervencija> sacuvaj(Intervencija intervencija) {
        Signal<Intervencija> signal = new Signal<Intervencija>();
        try {
            dbb.sacuvaj(intervencija);
            System.out.println(intervencija+"*/*/*/*/*/");
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.INTERVENCIJA_SACUVAJ);
        } else {
            signal.setPoruka(Poruka.INTERVENCIJA_SACUVAJ_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Intervencija> izmeni(Intervencija intervencija) {
        Signal<Intervencija> signal = new Signal<Intervencija>();
        try {
            if (intervencija.getStavkaIntervencijeList() != null) {
                for (StavkaIntervencije stavkaIntervencije : intervencija.getStavkaIntervencijeList()) {
                    dbb.sacuvaj(stavkaIntervencije);
                }
            }
            dbb.izmeni(intervencija);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.INTERVENCIJA_IZMENI);
        } else {
            signal.setPoruka(Poruka.INTERVENCIJA_IZMENI_ERR);
        }
        return signal;
    }

    @Override
    public Signal<Intervencija> obrisi(int id) {
        Intervencija intervencija = (Intervencija) dbb.ucitaj(Intervencija.class, id);
        Signal<Intervencija> signal = new Signal<Intervencija>();
        try {
            dbb.obrisi(intervencija);
            signal.setUspeh(true);
        } catch (Exception e) {
        }
        if (signal.getUspeh()) {
            signal.setPoruka(Poruka.INTERVENCIJA_OBRISI);
        } else {
            signal.setPoruka(Poruka.INTERVENCIJA_OBRISI_ERR);
        }
        return signal;
    }

}
