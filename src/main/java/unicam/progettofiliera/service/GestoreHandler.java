package unicam.progettofiliera.service;

import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.AccountRequestRepository;
import unicam.progettofiliera.models.AccountRequest;

import java.util.List;

@Service
public class GestoreHandler {

    private final AccountRequestRepository accountRequestRepository;

    public GestoreHandler(AccountRequestRepository accountRequestRepository) {
        this.accountRequestRepository = accountRequestRepository;
    }

    public List<AccountRequest> getRichieste(){
        return accountRequestRepository.findAll();
    }

    public void approvaRichiesta(Long id) {
        AccountRequest richiesta = accountRequestRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Richiesta non trovata"));

        switch(richiesta.getRuoloRichiesto()) {
            //TODO
        }
    }

}
