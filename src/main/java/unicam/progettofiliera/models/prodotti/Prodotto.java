package unicam.progettofiliera.models.prodotti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import unicam.progettofiliera.models.prodotti.state.*;
import unicam.progettofiliera.models.utenti.venditori.Venditore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_venditore")
public abstract class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String nome;

    private String descrizione;

    private double prezzo;

    @Transient
    @JsonIgnore
    private StatoProdotto stato = new InAttesaDiApprovazione();

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    @JsonIgnore
    private StatoProdottoEnum statoEnum = StatoProdottoEnum.INATTESADIAPPROVAZIONE;

    @ManyToOne
    @JoinColumn(name = "venditore_id", nullable = false)
    @JsonIgnore
    private Venditore venditore;

    public Prodotto() {}

    public Prodotto(String nome, String descrizione, double prezzo) {

        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    /**
     * il metodo modifica lo stato attuale del prodotto rendendolo
     * Approvato nel caso in cui sia InAttesaDiApprovazione, altrimenti lancia un'eccezione
     */

    public void approva() {

        getStato().approva(this);
    }

    /**
     * il metodo modifica lo stato attuale del prodotto rendendolo
     * Rifiutato nel caso in cui sia InAttesaDiApprovazione, altrimenti lancia un'eccezione
     */

    public void rifiuta() {

        getStato().rifiuta(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Venditore getVenditore() {
        return venditore;
    }

    public void setVenditore(Venditore venditore) {
        this.venditore = venditore;
    }

    /**
     * verifica lo stato del prodotto dall'enumerazione
     * andando a runtime a modificarne lo stato effettivo
     */
    public StatoProdotto getStato() {

        return switch (statoEnum) {
            case INATTESADIAPPROVAZIONE -> new InAttesaDiApprovazione();
            case APPROVATO -> new Approvato();
            case RIFIUTATO -> new Rifiutato();
        };
    }


    public void setStato(StatoProdotto stato) {
        this.stato = stato;
    }

    public StatoProdottoEnum getStatoEnum(StatoProdottoEnum statoEnum) {
        return statoEnum;
    }

    public void setStatoEnum(StatoProdottoEnum statoEnum) {
        this.statoEnum = statoEnum;
    }

}
