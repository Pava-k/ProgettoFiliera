package unicam.progettofiliera.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.service.AcquirenteHandler;

@RestController
@RequestMapping("/acquirente")
public class AcquirenteController {
    private final AcquirenteHandler acquirenteHandler;

    public AcquirenteController(AcquirenteHandler acquirenteHandler) {
        this.acquirenteHandler = acquirenteHandler;
    }

    @GetMapping("/checkout/{idAcquirente}")
    public ResponseEntity<String> checkout(@PathVariable Long idAcquirente) {
        String ricevuta = acquirenteHandler.checkout(idAcquirente);
        return ResponseEntity.ok(ricevuta);
    }

    @GetMapping("/{idAcquirente}/prenota/{idEvento}/{nPartecipanti}")
    public ResponseEntity<String> prenotaEvento(@PathVariable Long idAcquirente, @PathVariable Long idEvento, @PathVariable Integer nPartecipanti) {
        String responso = acquirenteHandler.prenotaEvento(idEvento, nPartecipanti);
        return ResponseEntity.ok("prenotazione effettuata per " + responso);
    }
}
