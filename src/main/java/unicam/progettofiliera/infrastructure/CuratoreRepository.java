package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.Curatore;

public interface CuratoreRepository extends JpaRepository<Curatore, Long> {
}
