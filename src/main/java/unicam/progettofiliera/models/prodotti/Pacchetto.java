package unicam.progettofiliera.models.prodotti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import unicam.progettofiliera.models.utenti.venditori.Distributore;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Pacchetto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String nome;

    private String descrizione;

    @ManyToMany
    private List<Prodotto> prodotti = new ArrayList<>();

    private double sconto;

    @ManyToOne
    @JoinColumn(name = "distributore_id")
    @JsonIgnore
    private Distributore creatore;

    public Pacchetto() {}

    public Pacchetto(String nome, String descrizione, double sconto) {

        this.nome = nome;
        this.descrizione = descrizione;
        this.sconto = sconto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    public Distributore getCreatore() {
        return creatore;
    }

    public void setCreatore(Distributore creatore) {
        this.creatore = creatore;
    }
}
