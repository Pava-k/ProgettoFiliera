package unicam.progettofiliera.models.prodotti;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("distributore")
public class ProdottoDistributore extends Prodotto {

    public ProdottoDistributore() {}

    public ProdottoDistributore(String nome, String descrizione, double prezzo) {
        super(nome, descrizione, prezzo);
    }

}
