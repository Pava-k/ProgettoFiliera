package unicam.progettofiliera.models.venditori;

import jakarta.persistence.*;
import unicam.progettofiliera.models.UtenteRegistrato;
import unicam.progettofiliera.models.prodotti.Prodotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Venditore extends UtenteRegistrato {

    @OneToMany(mappedBy = "venditore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prodotto> prodottiPubblicati = new ArrayList<>();

    public Venditore() {}

    public Venditore(String nome, String password) {
        super(nome, password);
    }

    public List<Prodotto> getProdottiPubblicati() {
        return prodottiPubblicati;
    }

    public void addProdotto(Prodotto p) {
        prodottiPubblicati.add(p);
    }

    //FACTORY METHOD
    public abstract Prodotto creaProdotto(String nome, String descrizione, double prezzo);
}
