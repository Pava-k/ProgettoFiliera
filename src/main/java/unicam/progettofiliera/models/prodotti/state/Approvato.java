package unicam.progettofiliera.models.prodotti.state;

import unicam.progettofiliera.models.prodotti.Prodotto;

public class Approvato implements StatoProdotto{

    public Approvato(){}

    @Override
    public void approva(Prodotto prodotto) {
        throw new IllegalStateException("Il prodotto è già state approvato.");
    }

    @Override
    public void rifiuta(Prodotto prodotto) {
        throw new IllegalStateException("un prodotto approvato non più esser rifiutato.");
    }
}
