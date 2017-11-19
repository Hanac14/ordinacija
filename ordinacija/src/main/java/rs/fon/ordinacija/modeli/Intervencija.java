package rs.fon.ordinacija.modeli;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Intervencija {

    @Id
    @GeneratedValue
    private int intervencijaID;
    private Date datumVreme;
    private String opis;
    private double cena;
    private int status;
    @Column(updatable = false)
    private int pacijentID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pacijentID", insertable = false, updatable = false)
    @JsonBackReference
    private Pacijent pacijent;
    @Column(updatable = false)
    private int stomatologID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stomatologID", insertable = false, updatable = false)
    @JsonBackReference
    private Stomatolog stomatolog;
    @OneToMany(mappedBy = "intervencija", fetch = FetchType.EAGER)
    @Column(nullable = true)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    public List<StavkaIntervencije> stavkaIntervencijeList;

    public int getIntervencijaID() {
        return intervencijaID;
    }

    public void setIntervencijaID(int intervencijaID) {
        this.intervencijaID = intervencijaID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getPacijentID() {
        return pacijentID;
    }

    public void setPacijentID(int pacijentID) {
        this.pacijentID = pacijentID;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public int getStomatologID() {
        return stomatologID;
    }

    public void setStomatologID(int stomatologID) {
        this.stomatologID = stomatologID;
    }

    public Stomatolog getStomatolog() {
        return stomatolog;
    }

    public void setStomatolog(Stomatolog stomatolog) {
        this.stomatolog = stomatolog;
    }

    public List<StavkaIntervencije> getStavkaIntervencijeList() {
        if(stavkaIntervencijeList == null){
            stavkaIntervencijeList = new ArrayList<StavkaIntervencije>();
        }
        return stavkaIntervencijeList;
    }

    public void setStavkaIntervencijeList(List<StavkaIntervencije> stavkaIntervencijeList) {
        this.stavkaIntervencijeList = stavkaIntervencijeList;
    }

    @Override
    public String toString() {
        return "Intervencija{" + "intervencijaID=" + intervencijaID + ", datumVreme=" + datumVreme + ", opis=" + opis + ", cena=" + cena + ", status=" + status + ", pacijentID=" + pacijentID + '}';
    }

  
    
}
