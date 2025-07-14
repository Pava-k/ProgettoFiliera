package unicam.progettofiliera.models.utenti.venditori;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.ProdottoProduttore;

@Entity
@DiscriminatorValue("Produttore")
public class Produttore extends Venditore {

    public Produttore() {}

    public Produttore(String nome, String password) {
        super(nome, password);
    }

    @Override
    public Prodotto creaProdotto(String nome, String descrizione, double prezzo) {
        return null;
    }

    /**
     * Overdload del factory method per permettere al produttore
     * di aggiungere il parametro processi
     * @param nome
     * @param descrizione
     * @param prezzo
     * @param processi
     * @return
     */
    public ProdottoProduttore creaProdotto(String nome, String descrizione, double prezzo, String processi) {

        ProdottoProduttore prodotto = new ProdottoProduttore(nome, descrizione, prezzo, processi);
        prodotto.setVenditore(this);
        return prodotto;
    }

}
