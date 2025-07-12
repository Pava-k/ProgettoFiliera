package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.venditori.Venditore;

public interface VenditoreRepository extends JpaRepository<Venditore, Long> {

    Long id(Long id);
}
