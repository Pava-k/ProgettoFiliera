package unicam.progettofiliera.models.prodotti;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("produttore")
public class ProdottoProduttore extends Prodotto {

    private String processiProduttivi;

    public ProdottoProduttore() {}

    public ProdottoProduttore(String nome, String descrizione, double prezzo, String processi) {
        super(nome, descrizione, prezzo);
        this.processiProduttivi = processi;
    }

    public String getProcessiProduttivi() {
        return processiProduttivi;
    }

    public void setProcessiProduttivi(String processiProduttivi) {
        this.processiProduttivi = processiProduttivi;
    }

}
