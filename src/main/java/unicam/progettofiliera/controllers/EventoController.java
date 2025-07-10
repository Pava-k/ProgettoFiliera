package unicam.progettofiliera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.infrastructure.AnimatoreRepository;
import unicam.progettofiliera.infrastructure.EventoRepository;
import unicam.progettofiliera.models.Animatore;
import unicam.progettofiliera.models.Evento;
import unicam.progettofiliera.service.EventoHandler;

@RestController
@RequestMapping("/BachecaEventi")
public class EventoController {
    private final EventoHandler eventoHandler;

    @Autowired
    public EventoController(EventoHandler eventoHandler) {
        this.eventoHandler = eventoHandler;
    }
    @PostMapping("/add/{id}")
    public ResponseEntity<Evento> pubblicaEvento(@PathVariable Long id, @RequestBody Evento evento){
        eventoHandler.caricaEvento(id,evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);

    }
}
