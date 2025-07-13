package unicam.progettofiliera.modelsDaImplementare;

import jakarta.persistence.*;
import unicam.progettofiliera.models.UtenteRegistrato;

@Entity
public class Acquirente extends UtenteRegistrato {
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Carrello carrello;

    public Acquirente() {}

    public Acquirente(String nome, String password) {
        super(nome, password);
        this.carrello = new Carrello();
    }
    public Carrello getCarrello() {
        return carrello;
    }

}
