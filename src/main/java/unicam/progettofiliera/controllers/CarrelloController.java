package unicam.progettofiliera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.service.CarrelloHandler;

import java.util.List;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {
    private final CarrelloHandler carrelloHandler;

    @Autowired
    public CarrelloController(CarrelloHandler carrelloHandler) {
        this.carrelloHandler = carrelloHandler;
    }

    @GetMapping("{idAcquirente}")
    public ResponseEntity<Object> mostraCarrello(@PathVariable Long idAcquirente) {
        List<Prodotto> prodotti = carrelloHandler.mostraCarrello(idAcquirente);
        if(prodotti.isEmpty()) return ResponseEntity.ok("Il carrelo è vuoto!");
        return ResponseEntity.ok(prodotti);
    }

    @PostMapping("/{idAcquirente}/addProdotto/{idProdotto}")
    public ResponseEntity<String> aggiungiProdotto(@PathVariable Long idAcquirente,
                                                   @PathVariable Long idProdotto) {
        carrelloHandler.aggiungiProdottoAlCarrello(idAcquirente, idProdotto);
        return ResponseEntity.ok("Prodotto aggiunto al carrello");
    }

    @DeleteMapping("/{idAcquirente}/rimuovi/{idProdotto}")
    public ResponseEntity<String> rimuoviProdotto(@PathVariable Long idAcquirente,
                                                  @PathVariable Long idProdotto) {
        carrelloHandler.rimuoviProdottoDaCarrello(idAcquirente, idProdotto);
        return ResponseEntity.ok("Prodotto rimosso dal carrello");
    }

    @DeleteMapping("/{idAcquirente}/svuota")
    public ResponseEntity<String> svuotaCarrello(@PathVariable Long idAcquirente) {
        carrelloHandler.svuotaCarrello(idAcquirente);
        return ResponseEntity.ok("Carrello svuotato");
    }

    @GetMapping("/{idAcquirente}/totale")
    public ResponseEntity<Object> sommaPrezzo(@PathVariable Long idAcquirente) {
        double totale = carrelloHandler.calcolaTotaleCarrello(idAcquirente);
        return ResponseEntity.ok("totale carrello: "+totale+ "€");
    }

    @PostMapping("/{idAcquirente}/addPacchetto/{idPacchetto}")
    public ResponseEntity<Object> aggiungiPacchetto(@PathVariable Long idAcquirente,
                                                    @PathVariable Long idPacchetto) {
        carrelloHandler.addPacchettoAlCarrello(idAcquirente, idPacchetto);
        return ResponseEntity.ok("Pacchetto aggiunto al carrello");
    }

}
