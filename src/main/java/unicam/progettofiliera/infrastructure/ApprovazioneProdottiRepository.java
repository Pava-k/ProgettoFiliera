package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.prodotti.Prodotto;

public interface ApprovazioneProdottiRepository extends JpaRepository<Prodotto, Integer> {
}
