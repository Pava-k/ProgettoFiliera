package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.utenti.Animatore;

public interface AnimatoreRepository extends JpaRepository<Animatore, Long> {
}
