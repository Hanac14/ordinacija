package rs.fon.ordinacija.baza;

public class Signal<T> {
    
    private boolean uspeh;
    private Object rezultat;
    private String poruka;

    public boolean getUspeh() {
        return uspeh;
    }

    public void setUspeh(boolean success) {
        this.uspeh = success;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

}
