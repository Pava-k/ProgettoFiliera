package unicam.progettofiliera.models.utenti.venditori;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.ProdottoTrasformatore;

import java.util.List;

@Entity
@DiscriminatorValue("Trasformatore")
public class Trasformatore extends Venditore {

    public Trasformatore() {}

    public Trasformatore(String nome, String password) {
        super(nome, password);
    }

    @Override
    public Prodotto creaProdotto(String nome, String descrizione, double prezzo) {
        return null;
    }

    //Overload del factoryMethod per aggiungere i parametri di prodottoTrasformatore
    public ProdottoTrasformatore creaProdotto(String nome, String descrizione, double prezzo,
                                              String processi, List<Produttore> collaboratori) {
        ProdottoTrasformatore prodotto =
                new ProdottoTrasformatore(nome, descrizione, prezzo, processi, collaboratori);
        prodotto.setVenditore(this);
        return prodotto;
    }

}
