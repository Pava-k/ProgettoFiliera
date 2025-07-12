package unicam.progettofiliera.models;

import jakarta.persistence.Entity;

@Entity
public class Curatore extends UtenteRegistrato {

    public Curatore() {

    }
    public Curatore(String nome, String password) {
        super(nome, password);
    }
}
