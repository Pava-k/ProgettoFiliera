package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.utenti.registrazione.AccountRequest;

public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {
}
