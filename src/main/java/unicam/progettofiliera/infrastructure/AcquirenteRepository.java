package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.utenti.acquirenti.Acquirente;

public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {
}
