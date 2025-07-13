package unicam.progettofiliera.models.prodotti;

public class Approvato implements StatoProdotto{

    public Approvato(){}

    @Override
    public void approva(Prodotto prodotto) {
        throw new IllegalStateException("Il prodotto è già stato approvato.");
    }

    @Override
    public void rifiuta(Prodotto prodotto) {
        throw new IllegalStateException("un prodotto approvato non più esser rifiutato.");
    }
}
