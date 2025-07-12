package unicam.progettofiliera.models.prodotti;

import jakarta.persistence.Entity;

@Entity
public class ProdottoDistributore extends Prodotto {

    public ProdottoDistributore() {}

    public ProdottoDistributore(String nome, String descrizione, double prezzo) {
        super(nome, descrizione, prezzo);
    }
}
