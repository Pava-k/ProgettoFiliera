package unicam.progettofiliera.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Animatore extends UtenteRegistrato{

    @JsonIgnore
    @OneToMany(mappedBy = "animatore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventiPubblicati = new ArrayList<>();

    public Animatore() { }

    public Animatore(String nome, String password) {
        super(nome, password);
    }

    public List<Evento> getEventiPubblicati() {
        return eventiPubblicati;
    }
}
