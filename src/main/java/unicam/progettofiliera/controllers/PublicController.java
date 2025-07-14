package unicam.progettofiliera.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicam.progettofiliera.models.utenti.registrazione.AccountRequest;
import unicam.progettofiliera.service.RegistrazioneHandler;

/**
 * Controller che rappresenta l'utente non registrato.
 * Permette di creare account e visualizzare prodotti ed eventi
 */

@RestController
@RequestMapping("/public")
public class PublicController {

    private final RegistrazioneHandler registrazioneHandler;

    public PublicController(RegistrazioneHandler registrazioneHandler) {
        this.registrazioneHandler = registrazioneHandler;
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signInRequest(@RequestBody AccountRequest accountRequest) {
        registrazioneHandler.richiestaRegistrazione(accountRequest);
        return ResponseEntity.ok("Richiesta inviata con successo");
    }

}
