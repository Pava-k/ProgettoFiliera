package unicam.progettofiliera.models.prodotti;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import unicam.progettofiliera.models.utenti.venditori.Produttore;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("trasformatore")
public class ProdottoTrasformatore extends Prodotto {

    private String processiTrasformativi;

    //i collaboratori (produttori) sono coloro che hanno contribuito
    // al processo di produzione del prodotto fornendone le materie prime
    @Transient
    private List<Produttore> collaboratori = new ArrayList<>();

    public ProdottoTrasformatore() {}

    public ProdottoTrasformatore(String nome, String descrizione,
                                 double prezzo, String processi,
                                 List<Produttore> collaboratori) {

        super(nome, descrizione, prezzo);
        this.processiTrasformativi = processi;
        this.collaboratori = collaboratori;
    }

    public String getProcessiTrasformativi() {
        return processiTrasformativi;
    }

    public void setProcessiTrasformativi(String processiTrasformativi) {
        this.processiTrasformativi = processiTrasformativi;
    }

    public List<Produttore> getCollaboratori() {
        return collaboratori;
    }

    public void setCollaboratori(List<Produttore> collaboratori) {
        this.collaboratori = collaboratori;
    }
}
