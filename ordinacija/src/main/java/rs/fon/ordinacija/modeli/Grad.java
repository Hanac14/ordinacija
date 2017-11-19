package rs.fon.ordinacija.modeli;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grad{

    @Id
    @GeneratedValue
    private int gradID;
    private String ptt;
    private String naziv;

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
