package unicam.progettofiliera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.progettofiliera.models.Evento;
import unicam.progettofiliera.modelsDaImplementare.Prodotto;
import unicam.progettofiliera.service.RicercaHandler;

import java.util.List;

@RestController
@RequestMapping("/ricerca")
public class RicercaController {
    private final RicercaHandler ricercaHandler;


    @Autowired
    public RicercaController(RicercaHandler ricercaHandler) {
        this.ricercaHandler = ricercaHandler;
    }

    @GetMapping("/prodotti")
    public ResponseEntity<Object> ricercaProdotti() {
        List<Prodotto> prodotti = ricercaHandler.ricercaProdotti();
        if (!prodotti.isEmpty())
            return ResponseEntity.ok(prodotti);
        return ResponseEntity.ok("Il marketplace è vuoto!");
    }

    @GetMapping("/prodotti/{keyword}")
    public ResponseEntity<Object> ricercaProdottoByName(@PathVariable String keyword) {
        List<Prodotto> prodotti = ricercaHandler.ricercaProdottoByName(keyword);
        if (!prodotti.isEmpty())
            return ResponseEntity.ok(prodotti);
        return ResponseEntity.ok("Prodotto non trovato");
    }

    @GetMapping("/eventi")
    public ResponseEntity<Object> ricercaEventi() {
        List<Evento> eventi = ricercaHandler.ricercaEventi();
        if (!eventi.isEmpty())
            return ResponseEntity.ok(eventi);
        return ResponseEntity.ok("La bacheca eventi è vuota!");
    }

    @GetMapping("/eventi/{keyword}")
    public ResponseEntity<Object> ricercaEventiByName(@PathVariable String keyword) {
        List<Evento> eventi = ricercaHandler.ricercaEventoByName(keyword);
        if (!eventi.isEmpty())
            return ResponseEntity.ok(eventi);
        return ResponseEntity.ok("Evento non trovato");
    }
}