package unicam.progettofiliera.models.utenti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import unicam.progettofiliera.models.eventi.Evento;
import unicam.progettofiliera.models.utenti.registrazione.UtenteRegistrato;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Animatore extends UtenteRegistrato {

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

    public Evento creaEvento(String nome,String luogo,String descrizione,int maxP){
        return new Evento(nome,luogo,descrizione,maxP);
    }
}
