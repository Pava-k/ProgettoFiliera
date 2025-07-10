package unicam.progettofiliera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unicam.progettofiliera.infrastructure.AnimatoreRepository;
import unicam.progettofiliera.infrastructure.EventoRepository;
import unicam.progettofiliera.models.Animatore;
import unicam.progettofiliera.models.Evento;
import unicam.progettofiliera.service.EventoHandler;

@RestController
public class EventoController {
    private final EventoHandler eventoHandler;
    private final AnimatoreRepository animatoreRepository;

    public EventoController(EventoRepository eventoRepository, AnimatoreRepository animatoreRepository, EventoHandler eventoHandler) {
        this.eventoHandler = eventoHandler;
        this.animatoreRepository = animatoreRepository;
    }
    @PostMapping("/add/{id}")
    public ResponseEntity<Object> creaEvento(@PathVariable Long id, @RequestBody Evento evento){
        eventoHandler.caricaEvento(id,evento);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
