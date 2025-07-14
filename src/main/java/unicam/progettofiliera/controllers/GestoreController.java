package unicam.progettofiliera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.models.utenti.registrazione.AccountRequest;
import unicam.progettofiliera.service.GestoreHandler;

import java.util.List;

@RestController
@RequestMapping("/richiesteAccount")
public class GestoreController {

    private final GestoreHandler gestoreHandler;

    public GestoreController(GestoreHandler gestoreHandler) {
        this.gestoreHandler = gestoreHandler;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> mostraRichieste(){

        List<AccountRequest> listaRichieste = gestoreHandler.getRichieste();

        if (!listaRichieste.isEmpty())
            return ResponseEntity.ok(listaRichieste);
        return ResponseEntity.ok("Non è presente nessuna richiesta");
    }

    /**
     * il gestore approva la richiesta fornita dall'utente
     * creando l'apposito account
     * @param id
     */

    @PostMapping("/approva/{id}")
    public ResponseEntity<String> approvaRichiesta(@PathVariable Long id) {

        gestoreHandler.approvaRichiesta(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Utente creato");
    }

    /**
     * il gestore della piattaforma rifiuta la richiesta
     * eliminandola dalla lista
     * @param id
     */

    @DeleteMapping("/rifiuta/{id}")
    public ResponseEntity<String> rifiutaRichiesta(@PathVariable Long id) {

        gestoreHandler.rifiutaRichiesta(id);
        return ResponseEntity.ok("Richiesta rifiutata");
    }

}
