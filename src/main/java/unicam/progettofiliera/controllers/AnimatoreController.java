package unicam.progettofiliera.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.models.Evento;
import unicam.progettofiliera.service.AnimatoreHandler;

@RestController
@RequestMapping("/bachecaEventi")
public class AnimatoreController {
    private final AnimatoreHandler animatoreHandler;

    @Autowired
    public AnimatoreController(AnimatoreHandler animatoreHandler) {
        this.animatoreHandler = animatoreHandler;
    }

    @PostMapping("/{idAnimatore}/add")
    public ResponseEntity<String> pubblicaEvento(@PathVariable Long idAnimatore,
                                                 @RequestBody Evento evento){
        animatoreHandler.caricaEvento(idAnimatore,evento);
        return ResponseEntity.ok("Evento aggiunto");
    }
    @DeleteMapping("/{idAnimatore}/delete/{idEvento}")
    public ResponseEntity<String> eliminaEvento(@PathVariable Long idAnimatore,
                                                @PathVariable Long idEvento) {
        animatoreHandler.deleteEvento(idAnimatore,idEvento);
        return ResponseEntity.ok("Evento cancellato");
    }
}
