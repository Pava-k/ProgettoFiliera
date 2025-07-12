package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.prodotti.Prodotto;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findByNomeContainingIgnoreCase(String keyword);

    Long id(Long id);
}
