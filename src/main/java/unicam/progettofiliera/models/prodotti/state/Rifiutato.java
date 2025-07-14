package unicam.progettofiliera.models.prodotti.state;

import unicam.progettofiliera.models.prodotti.Prodotto;

public class Rifiutato implements StatoProdotto{

    public Rifiutato(){}

    @Override
    public void approva(Prodotto prodotto) {
        throw new IllegalStateException("un prodotto rifiutato non può più esser approvato.");
    }

    @Override
    public void rifiuta(Prodotto prodotto) {
        throw new IllegalStateException("Il prodotto è già state rifiutato.");
    }
}
