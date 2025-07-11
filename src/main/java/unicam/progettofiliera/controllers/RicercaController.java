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
    private RicercaHandler ricercaHandler;


    @Autowired
    public RicercaController(RicercaHandler ricercaHandler) {
        this.ricercaHandler = ricercaHandler;
    }

    @GetMapping("/prodotti/{keyword}")
    public ResponseEntity<Object> ricercaProdotto(@PathVariable String keyword) {
        List<Prodotto> prodotti = ricercaHandler.ricercaProdotto(keyword);
        if (!prodotti.isEmpty())
            return ResponseEntity.ok(prodotti);
        return ResponseEntity.ok("Prodotto non trovato");
    }

    @GetMapping("/eventi/{keyword}")
    public ResponseEntity<Object> ricercaEventi(@PathVariable String keyword) {
        List<Evento> eventi = ricercaHandler.ricercaEvento(keyword);
        if (!eventi.isEmpty())
            return ResponseEntity.ok(eventi);
        return ResponseEntity.ok("Evento non trovato");
    }



}