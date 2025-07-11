package unicam.progettofiliera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.service.GestoreHandler;

@RestController
@RequestMapping("/gestorePiattaforma")
public class GestoreController {

    private final GestoreHandler gestoreHandler;
    public GestoreController(GestoreHandler gestoreHandler) {
        this.gestoreHandler = gestoreHandler;

    }

    @PostMapping("/approva/{id}")
    public ResponseEntity<String> approvaRichiesta(@PathVariable Long id) {
        gestoreHandler.approvaRichiesta(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Utente creato");
    }

    @DeleteMapping("/rifiuta/{id}")
    public ResponseEntity<String> rifiutaRichiesta(@PathVariable Long id) {
        gestoreHandler.rifiutaRichiesta(id);
        return ResponseEntity.ok("Richiesta rifiutata");
    }

}
