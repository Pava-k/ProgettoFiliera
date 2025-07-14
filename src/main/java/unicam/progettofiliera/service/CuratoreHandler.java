package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.CuratoreRepository;
import unicam.progettofiliera.infrastructure.ProdottoRepository;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.state.StatoProdottoEnum;

import java.util.List;

@Service
public class CuratoreHandler {

    private final CuratoreRepository curatoreRepository;
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public CuratoreHandler(CuratoreRepository curatoreRepository,
                           ProdottoRepository prodottoRepository) {

        this.curatoreRepository = curatoreRepository;
        this.prodottoRepository = prodottoRepository;
    }

    /**
     * mostra solo e unicamente i prodotti in stato di approvazione
     */

    public List<Prodotto> mostraProdotti() {

        return prodottoRepository.findByStatoEnum(StatoProdottoEnum.INATTESADIAPPROVAZIONE);
    }

    /**
     * il curatore approva il prodotto fornito tramite id caricandolo nel marketplace
     * @param idCuratore
     * @param idProdotto
     */
    public void approvaProdotto(Long idCuratore, Long idProdotto) {

        if (curatoreRepository.existsById(idCuratore)) {
            Prodotto prodotto = prodottoRepository.findById(idProdotto).
                    orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

            prodotto.approva();
            prodottoRepository.save(prodotto);
            prodotto.getVenditore().addProdotto(prodotto);
        } else throw new RuntimeException("Curatore non trovato");
    }

    /**
     * il curatore rifiuta il prodotto modificandone lo stato in rifiutato,
     * non visualizzabile nel marketplace
     * @param idCuratore
     * @param idProdotto
     */
    public void rifiutaProdotto(Long idCuratore, Long idProdotto) {

        if (curatoreRepository.existsById(idCuratore)) {

            Prodotto prodotto = prodottoRepository.findById(idProdotto).
                    orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

            prodotto.rifiuta();
            prodottoRepository.save(prodotto);
        } else throw new RuntimeException("Curatore non trovato");
    }
}
