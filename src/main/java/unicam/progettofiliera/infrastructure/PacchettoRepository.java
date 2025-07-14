package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.prodotti.Pacchetto;
import unicam.progettofiliera.models.utenti.venditori.Distributore;

import java.util.List;

public interface PacchettoRepository extends JpaRepository<Pacchetto, Long> {

    List<Pacchetto> findAllByCreatore(Distributore creatore);
}
