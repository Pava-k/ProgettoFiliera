package unicam.progettofiliera.models.prodotti.state;


import unicam.progettofiliera.models.prodotti.Prodotto;

public class InAttesaDiApprovazione implements StatoProdotto{

    public InAttesaDiApprovazione(){}

    @Override
    public void approva(Prodotto prodotto) {
        prodotto.setStatoEnum(StatoProdottoEnum.APPROVATO);
    }

    @Override
    public void rifiuta(Prodotto prodotto) {
        prodotto.setStatoEnum(StatoProdottoEnum.RIFIUTATO);
    }
}
