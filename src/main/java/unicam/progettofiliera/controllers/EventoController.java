package unicam.progettofiliera.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.models.Evento;
import unicam.progettofiliera.service.EventoHandler;

@RestController
@RequestMapping("/bachecaEventi")
public class EventoController {
    private final EventoHandler eventoHandler;

    @Autowired
    public EventoController(EventoHandler eventoHandler) {
        this.eventoHandler = eventoHandler;
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<String> pubblicaEvento(@PathVariable Long id, @RequestBody Evento evento){
        eventoHandler.caricaEvento(id,evento);
        return ResponseEntity.ok("Evento aggiunto");
    }
    @DeleteMapping("/delete/{id}/{idAnimatore}")
    public ResponseEntity<String> eliminaEvento(@PathVariable Long id, @PathVariable Long idAnimatore) {
        eventoHandler.deleteEvento(id,idAnimatore);
        return ResponseEntity.ok("Evento cancellato");
    }
}
