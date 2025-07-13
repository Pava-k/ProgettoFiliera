package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.ApprovazioneProdottiRepository;
import unicam.progettofiliera.infrastructure.CuratoreRepository;
import unicam.progettofiliera.infrastructure.ProdottoRepository;
import unicam.progettofiliera.models.prodotti.Prodotto;

import java.util.List;

@Service
public class CuratoreHandler {

    private final CuratoreRepository curatoreRepository;
    private final ApprovazioneProdottiRepository appProdRepository;
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public CuratoreHandler(CuratoreRepository curatoreRepository,
                           ApprovazioneProdottiRepository appProdRepository,
                           ProdottoRepository prodottoRepository) {
        this.curatoreRepository = curatoreRepository;
        this.appProdRepository = appProdRepository;
        this.prodottoRepository = prodottoRepository;
    }

    public List<Prodotto> mostraProdotti() {
        return appProdRepository.findAll();
    }

    public void approvaProdotto(Long idCuratore, Long idProdotto) {
        if (curatoreRepository.existsById(idCuratore)) {
            Prodotto prodotto = appProdRepository.findById(idProdotto).
                    orElseThrow(() -> new RuntimeException("Prodotto non trovato"));
            prodotto.getVenditore().addProdotto(prodotto);
            prodottoRepository.save(prodotto);
            appProdRepository.delete(prodotto);
        } else throw new RuntimeException("Curatore non trovato");
    }

    public void rifiutaProodotto(Long idCuratore, Long idProdotto) {
        if (curatoreRepository.existsById(idCuratore)) {
            Prodotto prodotto = appProdRepository.findById(idProdotto).
                    orElseThrow(() -> new RuntimeException("Prodotto non trovato"));
            appProdRepository.delete(prodotto);
        } else throw new RuntimeException("Curatore non trovato");
    }
}
