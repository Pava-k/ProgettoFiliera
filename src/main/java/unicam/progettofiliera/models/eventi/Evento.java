package unicam.progettofiliera.models.eventi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import unicam.progettofiliera.models.utenti.Animatore;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String nome;

    private String luogo;

    private String descrizione;

    private int maxPartecipanti;

    @JsonIgnore
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "animatore_id", nullable = false)
    @JsonIgnore
    private Animatore animatore;

    public Evento() {}

    public Evento(String nome, String luogo, String descrizione, int maxP) {
        this.nome = nome;
        this.luogo = luogo;
        this.descrizione = descrizione;
        this.maxPartecipanti = maxP;
        this.postiDisponibili = maxP;
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

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getMaxPartecipanti() {
        return maxPartecipanti;
    }

    public void setMaxPartecipanti(int maxPartecipanti) {
        this.maxPartecipanti = maxPartecipanti;
    }

    public int getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setPostiDisponibili(int postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    public Animatore getAnimatore() {
        return animatore;
    }

    public void setAnimatore(Animatore animatore) {
        this.animatore = animatore;
    }
}
