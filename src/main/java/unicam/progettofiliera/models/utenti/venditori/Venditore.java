package unicam.progettofiliera.models.utenti.venditori;

import jakarta.persistence.*;
import unicam.progettofiliera.models.utenti.registrazione.UtenteRegistrato;
import unicam.progettofiliera.models.prodotti.Prodotto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_venditore")
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

    /**
     * il FACTORY METHOD permette alle sottoclassi
     * di creare il prodotto
     * @param nome
     * @param descrizione
     * @param prezzo
     */
    public abstract Prodotto creaProdotto(String nome, String descrizione, double prezzo);

}
