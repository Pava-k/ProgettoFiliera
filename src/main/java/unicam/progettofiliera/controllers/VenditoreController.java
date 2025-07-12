package unicam.progettofiliera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.ProdottoDistributore;
import unicam.progettofiliera.models.prodotti.ProdottoProduttore;
import unicam.progettofiliera.models.prodotti.ProdottoTrasformatore;
import unicam.progettofiliera.service.VenditoreHandler;


@RestController
@RequestMapping("/marketplace")
public class VenditoreController {
    private final VenditoreHandler venditoreHandler;

    @Autowired
    public VenditoreController(VenditoreHandler venditoreHandler) {
        this.venditoreHandler = venditoreHandler;
    }

    @PostMapping("/produttore/{idVenditore}/add")
    public ResponseEntity<String> caricaProdottoProduttore(@PathVariable Long idVenditore, @RequestBody ProdottoProduttore prodottoProduttore) {
        venditoreHandler.caricaProdottoProduttore(
                prodottoProduttore.getNome(),
                idVenditore,
                prodottoProduttore.getDescrizione(),
                prodottoProduttore.getPrezzo(),
                prodottoProduttore.getProcessiProduttivi()
        );
        return ResponseEntity.ok("Prodotto in attesa di verifica da parte del curatore, puoi chiudere questa pagina");
    }

    @PostMapping("/trasformatore/{idVenditore}/add")
    public ResponseEntity<String> caricaProdottoTrasformatore(@PathVariable Long idVenditore, @RequestBody ProdottoTrasformatore prodotto) {
        venditoreHandler.caricaProdottoTrasformatore(
                prodotto.getNome(),
                idVenditore,
                prodotto.getDescrizione(),
                prodotto.getPrezzo(),
                prodotto.getProcessiTrasformativi(),
                prodotto.getCollaboratori()
        );
        return ResponseEntity.ok("Prodotto in attesa di verifica da parte del curatore, puoi chiudere questa pagina");
    }

    @PostMapping("/distributore/{idVenditore}/add")
    public ResponseEntity<String> caricaProdottoDistributore(@PathVariable Long idVenditore, @RequestBody ProdottoDistributore prodotto) {
        venditoreHandler.caricaProdottoDistributore(
                prodotto.getNome(),
                idVenditore,
                prodotto.getDescrizione(),
                prodotto.getPrezzo()
        );
        return ResponseEntity.ok("Prodotto in attesa di verifica da parte del curatore, puoi chiudere questa pagina");
    }

    @PostMapping("/deleteProduct/{id}")
    public ResponseEntity<String> eliminaProdotto(@PathVariable Long id){
        venditoreHandler.deleteProdotto(id);
        return ResponseEntity.ok("Prodotto eliminato");
    }



}

