package rs.fon.ordinacija.modeli;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class StavkaIntervencijePK implements Serializable {

    private int redniBrojStavke;
    private int intervencijaID;

    public int getIntervencijaID() {
        return intervencijaID;
    }

    public void setIntervencijaID(int intervencijaID) {
        this.intervencijaID = intervencijaID;
    }

    public int getRedniBrojStavke() {
        return redniBrojStavke;
    }

    public void setRedniBrojStavke(int redniBrojStavke) {
        this.redniBrojStavke = redniBrojStavke;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.intervencijaID;
        hash = 71 * hash + this.redniBrojStavke;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaIntervencijePK other = (StavkaIntervencijePK) obj;
        if (this.intervencijaID != other.intervencijaID) {
            return false;
        }
        if (this.redniBrojStavke != other.redniBrojStavke) {
            return false;
        }
        return true;
    }
}
