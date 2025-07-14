package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.utenti.venditori.Venditore;

public interface VenditoreRepository extends JpaRepository<Venditore, Long> {
}
