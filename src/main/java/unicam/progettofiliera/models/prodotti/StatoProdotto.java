package unicam.progettofiliera.models.prodotti;

public interface StatoProdotto {
    void approva(Prodotto prodotto);
    void rifiuta(Prodotto prodotto);
}
