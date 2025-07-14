package unicam.progettofiliera.models.utenti;

import jakarta.persistence.Entity;
import unicam.progettofiliera.models.utenti.registrazione.UtenteRegistrato;

@Entity
public class Curatore extends UtenteRegistrato {

    public Curatore() {

    }
    public Curatore(String nome, String password) {
        super(nome, password);
    }
}
