package unicam.progettofiliera.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestoreDellaPiattaforma {
// Sarebbe il caso di rimuoverla e gestirla nei service cambiando
// gestore handler in qualcos'altro non essendo un effettivo utente
// nè avendo bisogno di una lista di richieste ricevute in quanto abbiamo la repository
// non neccessita neanche di una tabella

    @OneToMany
    private List<AccountRequest> richiesteRicevute = new ArrayList<>();







}
