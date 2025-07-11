package unicam.progettofiliera.models.venditori;

import jakarta.persistence.Entity;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.ProdottoDistributore;

@Entity
public class Distributore extends Venditore {

    //List<Pacchetto> pacchettiCaricati

    public Distributore() {}

    public Distributore(String nome, String password) {
        super(nome, password);
    }

    @Override
    public ProdottoDistributore creaProdotto(String nome, String descrizione, double prezzo) {
        ProdottoDistributore prodotto = new ProdottoDistributore(nome, descrizione, prezzo);
        prodotto.setVenditore(this);
        this.addProdotto(prodotto);
        return prodotto;
    }

}
