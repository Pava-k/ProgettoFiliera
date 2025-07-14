package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.AnimatoreRepository;
import unicam.progettofiliera.infrastructure.EventoRepository;
import unicam.progettofiliera.models.utenti.Animatore;
import unicam.progettofiliera.models.eventi.Evento;

@Service
public class AnimatoreHandler {

    private final EventoRepository eventoRepository;
    private final AnimatoreRepository animatoreRepository;

    @Autowired
    public AnimatoreHandler(EventoRepository eventoRepository, AnimatoreRepository animatoreRepository) {
        this.eventoRepository = eventoRepository;
        this.animatoreRepository = animatoreRepository;
    }

    public void caricaEvento(Long animatoreId, String nome, String luogo, String descrizione, int maxP) {
        Animatore animatore = animatoreRepository.findById(animatoreId).
                orElseThrow(() -> new RuntimeException("Animatore non trovato"));

        Evento nuovoEvento = animatore.creaEvento(nome, luogo, descrizione, maxP);
        nuovoEvento.setAnimatore(animatore);
        animatore.getEventiPubblicati().add(nuovoEvento);
        eventoRepository.save(nuovoEvento);
    }

    public void deleteEvento(Long animatoreId, Long eventoId) {
        Animatore animatore = animatoreRepository.findById(animatoreId).
                orElseThrow(() -> new RuntimeException("Animatore non trovato"));
        Evento evento = eventoRepository.findById(eventoId).
                orElseThrow(() -> new RuntimeException("Evento non trovato"));
        if (animatore.getEventiPubblicati().contains(evento)) {
            animatore.getEventiPubblicati().remove(evento);
            eventoRepository.delete(evento);
        } else
            throw new RuntimeException("L'evento non appartiene all'animatore selezionato");
    }
}
