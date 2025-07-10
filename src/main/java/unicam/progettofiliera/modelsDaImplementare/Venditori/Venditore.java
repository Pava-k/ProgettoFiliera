package unicam.progettofiliera.modelsDaImplementare.Venditori;

import jakarta.persistence.Entity;
import unicam.progettofiliera.models.UtenteRegistrato;
@Entity
public abstract class Venditore extends UtenteRegistrato {

    public Venditore() {}

    public Venditore(String nome, String password) {
       super(nome, password);
   }

}
