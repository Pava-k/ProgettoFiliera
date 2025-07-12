package unicam.progettofiliera.models.venditori;

import jakarta.persistence.Entity;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.ProdottoProduttore;

@Entity
public class Produttore extends Venditore {

    public Produttore() {}

    public Produttore(String nome, String password) {
        super(nome, password);
    }

    @Override
    public Prodotto creaProdotto(String nome, String descrizione, double prezzo) {
        return null;
    }

    //Overload del factory method per aggiungere un parametro processi
    public ProdottoProduttore creaProdotto(String nome, String descrizione, double prezzo, String processi) {
        ProdottoProduttore prodotto = new ProdottoProduttore(nome, descrizione, prezzo, processi);
        prodotto.setVenditore(this);
        this.addProdotto(prodotto);
        return prodotto;
    }

}
