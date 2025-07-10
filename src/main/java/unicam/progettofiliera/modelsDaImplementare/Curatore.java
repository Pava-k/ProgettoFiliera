package unicam.progettofiliera.modelsDaImplementare;

import jakarta.persistence.Entity;
import unicam.progettofiliera.models.UtenteRegistrato;

@Entity
public class Curatore extends UtenteRegistrato {

    public Curatore() {

    }
    public Curatore(String nome, String password) {
        super(nome, password);
    }
}
