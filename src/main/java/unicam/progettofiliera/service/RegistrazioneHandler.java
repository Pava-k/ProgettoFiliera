package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.AccountRequestRepository;
import unicam.progettofiliera.models.AccountRequest;

@Service
public class RegistrazioneHandler {

    private AccountRequestRepository accountRequestRepository;

    @Autowired
    public RegistrazioneHandler(AccountRequestRepository accountRequestRepository) {
        this.accountRequestRepository = accountRequestRepository;
    }

    public void richiestaRegistrazione(AccountRequest accountRequest) {
        if(accountRequest.getNome().trim().isEmpty() || accountRequest.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome o password non possono essere vuoti");
        }
        accountRequestRepository.save(accountRequest);
    }
}
