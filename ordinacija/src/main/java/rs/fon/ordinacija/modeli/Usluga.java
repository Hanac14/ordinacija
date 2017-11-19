package rs.fon.ordinacija.modeli;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usluga {

    @Id
    @GeneratedValue
    private int uslugaID;
    private String naziv;
    private double cena;

    public int getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(int uslugaID) {
        this.uslugaID = uslugaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
}
