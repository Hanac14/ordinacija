package rs.fon.ordinacija.sistemske_operacije;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import rs.fon.ordinacija.baza.DBBroker;
import rs.fon.ordinacija.baza.Poruka;
import rs.fon.ordinacija.baza.Signal;
import rs.fon.ordinacija.modeli.Stomatolog;

public class StomatologSO {

    @Autowired
    private DBBroker dbb;

    public Signal<Stomatolog> prijava(Stomatolog stomatolog) {
        Signal<Stomatolog> signal = new Signal<Stomatolog>();
        List<Object> list = dbb.pretraga(Stomatolog.class);
        for (Object object : list) {
            Stomatolog s = (Stomatolog) object;
            if (stomatolog.getKorisnickoIme().equals(s.getKorisnickoIme()) && stomatolog.getKorisnickaSifra().equals(s.getKorisnickaSifra())) {
                signal.setRezultat(s);
                signal.setUspeh(true);
                signal.setPoruka(Poruka.STOMATOLOG_PRIJAVA);
                return signal;
            }
        }
        signal.setUspeh(false);
        signal.setPoruka(Poruka.STOMATOLOG_PRIJAVA_ERR);
        return signal;
    }

}
