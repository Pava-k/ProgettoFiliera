package unicam.progettofiliera.modelsDaImplementare.Venditori;

import jakarta.persistence.Entity;

@Entity
public class Trasformatore extends Venditore {

    public Trasformatore() {}
    public Trasformatore(String nome, String password) {
        super(nome, password);
    }
}
