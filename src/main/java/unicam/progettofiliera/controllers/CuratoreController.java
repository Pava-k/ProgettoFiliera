package unicam.progettofiliera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.service.CuratoreHandler;

import java.util.List;

@RestController
@RequestMapping("/curatore")
public class CuratoreController {

    private CuratoreHandler curatoreHandler;

    @Autowired
    public CuratoreController(CuratoreHandler curatoreHandler) {
        this.curatoreHandler = curatoreHandler;
    }

    @GetMapping("/prodottiDaApprovare")
    public ResponseEntity<Object> getProdotti() {
        List<Prodotto> prodotti = curatoreHandler.mostraProdotti();
        if (!prodotti.isEmpty())
            return ResponseEntity.ok(prodotti);
        return ResponseEntity.ok("Non c'è nessuna richiesta di approvazione!");
    }

    @PostMapping("/{idCuratore}/approva/{idProdotto}")
    public ResponseEntity<String> approvaProdotto(@PathVariable Long idCuratore,
                                                  @PathVariable Long idProdotto) {
        curatoreHandler.approvaProdotto(idCuratore, idProdotto);
        return ResponseEntity.ok("Prodotto approvato!");
    }

    @DeleteMapping("/{idCuratore}/rifiuta/{idProdotto}")
    public ResponseEntity<String> rifiutaProdotto(@PathVariable Long idCuratore,
                                                  @PathVariable Long idProdotto) {
        curatoreHandler.rifiutaProodotto(idCuratore, idProdotto);
        return ResponseEntity.ok("Prodotto rifiutato!");
    }
}
