package rs.fon.ordinacija.modeli;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@IdClass(StavkaIntervencijePK.class)
public class StavkaIntervencije implements Serializable {

    @Id
    private int redniBrojStavke;
    @Id
    private int intervencijaID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "intervencijaID", insertable = false, updatable = false)
    @JsonBackReference
    private Intervencija intervencija;
    private int uslugaID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uslugaID", insertable = false, updatable = false)
    private Usluga usluga;
    @Transient
    private int status;

    public int getRedniBrojStavke() {
        return redniBrojStavke;
    }

    public void setRedniBrojStavke(int redniBrojStavke) {
        this.redniBrojStavke = redniBrojStavke;
    }

    public int getIntervencijaID() {
        return intervencijaID;
    }

    public void setIntervencijaID(int intervencijaID) {
        this.intervencijaID = intervencijaID;
    }

    public Intervencija getIntervencija() {
        return intervencija;
    }

    public void setIntervencija(Intervencija intervencija) {
        this.intervencija = intervencija;
    }

    public int getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(int uslugaID) {
        this.uslugaID = uslugaID;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
