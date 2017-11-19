package rs.fon.ordinacija.sistemske_operacije;

import rs.fon.ordinacija.baza.Signal;

public interface OpstaSO<T> {
    
    public Signal<T> pretraga();
    
    public Signal<T> ucitaj(int id);
    
    public Signal<T> sacuvaj(T object);
    
    public Signal<T> izmeni(T object);
    
    public Signal<T> obrisi(int id);
    
}
