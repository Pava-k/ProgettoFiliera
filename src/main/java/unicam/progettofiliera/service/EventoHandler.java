package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import unicam.progettofiliera.infrastructure.AnimatoreRepository;
import unicam.progettofiliera.infrastructure.EventoRepository;
import unicam.progettofiliera.models.Animatore;
import unicam.progettofiliera.models.Evento;

@Service
public class EventoHandler {

    private final EventoRepository eventoRepository;
    private final AnimatoreRepository animatoreRepository;

    @Autowired
    public EventoHandler(EventoRepository eventoRepository, AnimatoreRepository animeRepository) {
        this.eventoRepository = eventoRepository;
        this.animatoreRepository = animeRepository;
    }

    public Evento caricaEvento(Long animatoreId, Evento nuovoEvento) {
        Animatore animatore = animatoreRepository.findById(animatoreId).orElseThrow(() -> new RuntimeException("Animatore non trovato"));
        nuovoEvento.setAnimatore(animatore);
        animatore.getEventiPubblicati().add(nuovoEvento);
        return eventoRepository.save(nuovoEvento);
    }

}
