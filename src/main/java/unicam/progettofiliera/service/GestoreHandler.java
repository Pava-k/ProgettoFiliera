package unicam.progettofiliera.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.*;
import unicam.progettofiliera.models.AccountRequest;
import unicam.progettofiliera.models.Animatore;
import unicam.progettofiliera.modelsDaImplementare.Acquirente;
import unicam.progettofiliera.modelsDaImplementare.Curatore;
import unicam.progettofiliera.modelsDaImplementare.Venditori.Distributore;
import unicam.progettofiliera.modelsDaImplementare.Venditori.Produttore;
import unicam.progettofiliera.modelsDaImplementare.Venditori.Trasformatore;

import java.util.List;

@Service
public class GestoreHandler {

    private final AccountRequestRepository accountRequestRepository;
    private final AnimatoreRepository animatoreRepository;
    private final VenditoreRepository venditoreRepository;
    private final CuratoreRepository curatoreRepository;
    private final AcquirenteRepository acquirenteRepository;

    public GestoreHandler(AccountRequestRepository accountRequestRepository, AnimatoreRepository animatoreRepository, VenditoreRepository venditoreRepository, CuratoreRepository curatoreRepository, AcquirenteRepository acquirenteRepository) {
        this.accountRequestRepository = accountRequestRepository;
        this.animatoreRepository = animatoreRepository;
        this.venditoreRepository = venditoreRepository;
        this.curatoreRepository = curatoreRepository;
        this.acquirenteRepository = acquirenteRepository;
    }

    public List<AccountRequest> getRichieste(){
        return accountRequestRepository.findAll();
    }

    public void approvaRichiesta(Long id) {
        AccountRequest richiesta = accountRequestRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Richiesta non trovata"));
        String nome = richiesta.getNome();
        String password = richiesta.getPassword();

        switch(richiesta.getRuoloRichiesto()) {

            case CURATORE ->{
                Curatore curatore = new Curatore(nome,password);
                curatoreRepository.save(curatore);
            }
            case ANIMATORE -> {
                Animatore animatore = new Animatore(nome, password);
                animatoreRepository.save(animatore);
            }
            case ACQUIRENTE -> {
                Acquirente acquirente = new Acquirente(nome,password);
                acquirenteRepository.save(acquirente);
            }
            case PRODUTTORE ->{
                Produttore produttore = new Produttore(nome,password);
                venditoreRepository.save(produttore);
            }
            case DISTRIBUTORE -> {
                Distributore distributore = new Distributore(nome,password);
                venditoreRepository.save(distributore);

            }
            case TRASFORMATORE -> {
                Trasformatore trasformatore = new Trasformatore(nome,password);
                venditoreRepository.save(trasformatore);
            }
            default -> throw new RuntimeException("Richiesta non trovata");

        }
        accountRequestRepository.delete(richiesta);
    }

    public ResponseEntity<String> rifiutaRichiesta(Long richiestaId) {
        accountRequestRepository.deleteById(richiestaId);
        return ResponseEntity.ok().body("Richiesta rifiutata");
    }


}
