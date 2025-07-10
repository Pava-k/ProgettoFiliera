package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.AccountRequest;

public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {
}
