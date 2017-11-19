package rs.fon.ordinacija.modeli;

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
public class Pacijent{

    @Id
    @GeneratedValue
    private int pacijentID;
    @Column(updatable = false)
    private String jmbg;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String mail;
    private String adresa;
    private String brojTelefona;
    private int gradID;
    @ManyToOne
    @JoinColumn(name = "gradID", insertable = false, updatable = false)
    public Grad grad;
    @OneToMany(mappedBy = "pacijent", fetch = FetchType.EAGER)
    @Column(nullable = true)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    public List<Intervencija> intervencijaList;

    public int getPacijentID() {
        return pacijentID;
    }

    public void setPacijentID(int pacijentID) {
        this.pacijentID = pacijentID;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

   

    public List<Intervencija> getIntervencijaList() {
        if(intervencijaList == null){
            intervencijaList = new ArrayList<Intervencija>();
        }
        return intervencijaList;
    }

    public void setIntervencijaList(List<Intervencija> intervencijaList) {
        this.intervencijaList = intervencijaList;
    }

    @Override
    public String toString() {
        return "Pacijent{" + "pacijentID=" + pacijentID + ", jmbg=" + jmbg + ", ime=" + ime + ", prezime=" + prezime + ", datumRodjenja=" + datumRodjenja + ", mail=" + mail + ", adresa=" + adresa + ", brojTelefona=" + brojTelefona ;
    }

}
