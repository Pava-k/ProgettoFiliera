package unicam.progettofiliera.models.prodotti.state;

import unicam.progettofiliera.models.prodotti.Prodotto;

/**
 * design pattern STATE
 * il prodotto si ritroverà in 3 stati predefiniti(inAttesaDiApprovazione, Approvato, Rifiutato)
 * nel primo stato sarà in grado di esser approvato o rifiutato a seconda della decisione del curatore
 * negli altri 2 si ritroverà già approvato o rifiutato quindi non potrà ricevere ulteriori modifiche
 */
public interface StatoProdotto {

    void approva(Prodotto prodotto);
    void rifiuta(Prodotto prodotto);
}
