package unicam.progettofiliera.models.prodotti;


public class InAttesaDiApprovazione implements StatoProdotto{

    public InAttesaDiApprovazione(){}

    @Override
    public void approva(Prodotto prodotto) {
        prodotto.setStato(new Approvato());
        prodotto.setStatoEnum(StatoProdottoEnum.APPROVATO);
    }

    @Override
    public void rifiuta(Prodotto prodotto) {
        prodotto.setStato(new Rifiutato());
        prodotto.setStatoEnum(StatoProdottoEnum.RIFIUTATO);
    }
}
