package rs.fon.ordinacija.modeli;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stomatolog implements Serializable{

    @Id
    @GeneratedValue
    private int stomatologID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String korisnickaSifra;

    public int getStomatologID() {
        return stomatologID;
    }

    public void setStomatologID(int stomatologID) {
        this.stomatologID = stomatologID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
    }

}
