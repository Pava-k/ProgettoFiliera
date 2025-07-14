package unicam.progettofiliera.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.state.StatoProdottoEnum;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    List<Prodotto> findByNomeContainingIgnoreCase(String keyword);
    List<Prodotto> findByStatoEnum(StatoProdottoEnum stato);
    List<Prodotto> findByStatoEnumAndNomeContainingIgnoreCase(StatoProdottoEnum stato, String keyword);


}
