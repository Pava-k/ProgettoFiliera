package unicam.progettofiliera.models.prodotti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import unicam.progettofiliera.models.venditori.Venditore;

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

    public void approva(){
        this.stato.approva(this);
    }

    public void rifiuta(){
        this.stato.rifiuta(this);
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

    public StatoProdotto getStato() {
        return stato;
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
