package unicam.progettofiliera.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.progettofiliera.infrastructure.AccountRequestRepository;
import unicam.progettofiliera.models.AccountRequest;
import unicam.progettofiliera.models.Ruolo;
import unicam.progettofiliera.service.GestoreHandler;

@RestController
@RequestMapping("/gestorePiattaforma")
public class GestoreController {

    private GestoreHandler gestoreHandler;
    private AccountRequestRepository accountRequestRepository;

    public GestoreController(GestoreHandler gestoreHandler, AccountRequestRepository accountRequestRepository) {
        this.gestoreHandler = gestoreHandler;
        this.accountRequestRepository = accountRequestRepository;
    }

    @PostMapping("/approvaRichiesta/{id}")
    public ResponseEntity<String> approvaRichiesta(@PathVariable Long id) {
        AccountRequest accountRequest = new AccountRequest("Giacomo", "password", Ruolo.ANIMATORE);
        //Da sostituire con il metodo di utente generico poi
        accountRequestRepository.save(accountRequest);
        gestoreHandler.approvaRichiesta(id);
        return ResponseEntity.ok("Approvata");
    }

    @PostMapping("/rifiutaRichiesta/{id}")
    public ResponseEntity<String> rifiutaRichiesta(@PathVariable Long id) {
        AccountRequest accountRequest = new AccountRequest("Giacomo", "password", Ruolo.ANIMATORE);
        gestoreHandler.rifiutaRichiesta(id);
        return ResponseEntity.ok("Richiesta rifiutata");
    }

}
