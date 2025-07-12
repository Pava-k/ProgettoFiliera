package unicam.progettofiliera.models.venditori;

import jakarta.persistence.Entity;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.ProdottoTrasformatore;

import java.util.List;

@Entity
public class Trasformatore extends Venditore {

    public Trasformatore() {}

    public Trasformatore(String nome, String password) {
        super(nome, password);
    }

    @Override
    public Prodotto creaProdotto(String nome, String descrizione, double prezzo) {
        return null;
    }

    //Overload del factoryMethod per aggiungere i processi come parametro
    public ProdottoTrasformatore creaProdotto(String nome, String descrizione, double prezzo,
                                              String processi, List<Produttore> collaboratori) {
        ProdottoTrasformatore prodotto =
                new ProdottoTrasformatore(nome, descrizione, prezzo, processi, collaboratori);
        prodotto.setVenditore(this);
        this.addProdotto(prodotto);
        return prodotto;
    }

}
