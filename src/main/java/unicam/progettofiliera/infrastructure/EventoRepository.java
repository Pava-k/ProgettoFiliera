package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
