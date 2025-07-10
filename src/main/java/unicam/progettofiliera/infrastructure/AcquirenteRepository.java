package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.modelsDaImplementare.Acquirente;

public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {
}
