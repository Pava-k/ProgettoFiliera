package unicam.progettofiliera.modelsDaImplementare;

import jakarta.persistence.Entity;
import unicam.progettofiliera.models.UtenteRegistrato;

@Entity
public class Acquirente extends UtenteRegistrato {

    public Acquirente() {}

    public Acquirente(String nome, String password) {
        super(nome, password);
    }
}
