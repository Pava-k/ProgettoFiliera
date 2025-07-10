package unicam.progettofiliera.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Animatore extends UtenteRegistrato{

    @OneToMany(mappedBy = "animatore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventiPubblicati = new ArrayList<>();
    //TODO private CasellaPostale casellaPostale

    public Animatore() {
    }


    public Animatore(String nome, String password) {
        super(nome, password);
    }

    public List<Evento> getEventiPubblicati() {
        return eventiPubblicati;
    }
}
