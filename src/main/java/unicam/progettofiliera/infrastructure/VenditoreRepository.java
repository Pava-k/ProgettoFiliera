package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.utenti.venditori.Venditore;

import java.util.List;

public interface VenditoreRepository extends JpaRepository<Venditore, Long> {

}
