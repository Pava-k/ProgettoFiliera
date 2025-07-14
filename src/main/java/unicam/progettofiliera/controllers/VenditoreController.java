package unicam.progettofiliera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.models.prodotti.PacchettoRequestDTO;
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
    public ResponseEntity<String> caricaProdottoProduttore(@PathVariable Long idVenditore,
                                                           @RequestBody ProdottoProduttore prodotto) {
        venditoreHandler.caricaProdottoProduttore(
                idVenditore,
                prodotto.getNome(),
                prodotto.getDescrizione(),
                prodotto.getPrezzo(),
                prodotto.getProcessiProduttivi()
        );
        return ResponseEntity.ok("Prodotto in attesa di verifica, puoi chiudere questa pagina");
    }

    @PostMapping("/trasformatore/{idVenditore}/add")
    public ResponseEntity<String> caricaProdottoTrasformatore(@PathVariable Long idVenditore,
                                                              @RequestBody ProdottoTrasformatore prodotto) {
        venditoreHandler.caricaProdottoTrasformatore(
                idVenditore,
                prodotto.getNome(),
                prodotto.getDescrizione(),
                prodotto.getPrezzo(),
                prodotto.getProcessiTrasformativi(),
                prodotto.getCollaboratori()
        );
        return ResponseEntity.ok("Prodotto in attesa di verifica, puoi chiudere questa pagina");
    }

    @PostMapping("/distributore/{idVenditore}/add")
    public ResponseEntity<String> caricaProdottoDistributore(@PathVariable Long idVenditore,
                                                             @RequestBody ProdottoDistributore prodotto) {
        venditoreHandler.caricaProdottoDistributore(
                prodotto.getNome(),
                idVenditore,
                prodotto.getDescrizione(),
                prodotto.getPrezzo()
        );
        return ResponseEntity.ok("Prodotto in attesa di verifica, puoi chiudere questa pagina");
    }

    @PostMapping("/distributore/{idVenditore}/addPacchetto")
    public ResponseEntity<String> caricaPacchetto(@PathVariable Long idVenditore,
                                                  @RequestBody PacchettoRequestDTO dto) {
        venditoreHandler.caricaPacchetto(
                idVenditore,
                dto.getNome(),
                dto.getDescrizione(),
                dto.getSconto(),
                dto.getProdottiId());

        return ResponseEntity.ok("Pacchetto caricato con successo.");
    }

    @DeleteMapping("{idVenditore}/delete/{idProdotto}")
    public ResponseEntity<String> eliminaProdotto(@PathVariable Long idVenditore,
                                                  @PathVariable Long idProdotto) {
        venditoreHandler.deleteProdotto(idVenditore, idProdotto);
        return ResponseEntity.ok("Prodotto eliminato");
    }

    //TODO aggiungere controllo prezzo negativo

}

