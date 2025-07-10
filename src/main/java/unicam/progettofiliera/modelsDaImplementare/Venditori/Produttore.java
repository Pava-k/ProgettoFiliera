package unicam.progettofiliera.modelsDaImplementare.Venditori;

import jakarta.persistence.Entity;

@Entity
public class Produttore extends Venditore {

    public Produttore() {}

    public Produttore(String nome, String password) {
        super(nome, password);
    }

}
