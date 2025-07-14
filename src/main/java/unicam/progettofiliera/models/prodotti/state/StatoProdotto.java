package unicam.progettofiliera.models.prodotti.state;

import unicam.progettofiliera.models.prodotti.Prodotto;

public interface StatoProdotto {
    void approva(Prodotto prodotto);
    void rifiuta(Prodotto prodotto);
}
