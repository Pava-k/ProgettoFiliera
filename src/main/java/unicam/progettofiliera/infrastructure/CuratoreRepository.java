package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.modelsDaImplementare.Curatore;

public interface CuratoreRepository extends JpaRepository<Curatore, Long> {
}
