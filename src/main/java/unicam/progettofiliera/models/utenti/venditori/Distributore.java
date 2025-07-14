package unicam.progettofiliera.models.utenti.venditori;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import unicam.progettofiliera.models.prodotti.Pacchetto;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.ProdottoDistributore;
import unicam.progettofiliera.models.prodotti.state.Approvato;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Distributore")
public class Distributore extends Venditore {

    @OneToMany(mappedBy = "creatore", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pacchetto> pacchettiCaricati = new ArrayList<>();

    public Distributore() {}

    public Distributore(String nome, String password) {
        super(nome, password);
    }

    public List<Pacchetto> getPacchettiCaricati() {
        return pacchettiCaricati;
    }

    public void setPacchettiCaricati(List<Pacchetto> pacchettiCaricati) {
        this.pacchettiCaricati = pacchettiCaricati;
    }

    @Override
    public ProdottoDistributore creaProdotto(String nome, String descrizione, double prezzo) {
        ProdottoDistributore prodotto = new ProdottoDistributore(nome, descrizione, prezzo);
        prodotto.setVenditore(this);
        return prodotto;
    }

    public Pacchetto creaPacchetto(String nome, String descrizione, double sconto, List<Prodotto> prodotti) {
        //il pacchetto deve contenere al minimo 2 prodotti altrimenti non è considerato tale
        if(prodotti.size() <= 1) {
            throw new IllegalArgumentException("I prodotti in un pacchetto devono essere 2 o più");
        }
        //non si può creare un pacchetto con prodotti non ancora approvati
        if(!prodottiApprovati(prodotti)) {
            throw new IllegalArgumentException("Non tutti i prodotti sono stati approvati");
        }
        // Verifica che tutti i prodotti selezionati siano già stati caricati dal distributore
        if(!this.getProdottiPubblicati().containsAll(prodotti)) {
            throw new RuntimeException("Alcuni prodotti non appartengono al distributore");
        }

        Pacchetto pacchetto = new Pacchetto(nome, descrizione, sconto);
        pacchetto.setCreatore(this);
        pacchetto.getProdotti().addAll(prodotti);
        pacchettiCaricati.add(pacchetto);
        return pacchetto;

    }

    /**
     * controlla se tutta la lista di prodotti sia effettivamente
     * composta da prodotti approvati
     *
     * @param prodotti
     * @return true se i tutti i prodotti sono approvati
     */
    private boolean prodottiApprovati(List<Prodotto> prodotti) {
        for(Prodotto prodotto : prodotti) {
            if(!(prodotto.getStato() instanceof Approvato))
                return false;
        } return true;
    }

}
