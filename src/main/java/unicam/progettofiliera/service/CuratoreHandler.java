package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.ApprovazioneProdottiRepository;
import unicam.progettofiliera.infrastructure.CuratoreRepository;
import unicam.progettofiliera.infrastructure.ProdottoRepository;

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

    public void approvaProdotto(Long idCuratore, Long idProdotto) {
        return; //TODO
    }
}
