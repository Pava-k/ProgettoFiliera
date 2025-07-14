package unicam.progettofiliera.service;

import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.AcquirenteRepository;
import unicam.progettofiliera.infrastructure.PacchettoRepository;
import unicam.progettofiliera.infrastructure.ProdottoRepository;
import unicam.progettofiliera.infrastructure.VenditoreRepository;
import unicam.progettofiliera.models.prodotti.Pacchetto;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.state.Approvato;
import unicam.progettofiliera.models.utenti.acquirenti.Acquirente;
import unicam.progettofiliera.models.utenti.acquirenti.Carrello;
import unicam.progettofiliera.models.utenti.venditori.Venditore;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrelloHandler {
    private final AcquirenteRepository acquirenteRepository;
    private final ProdottoRepository prodottoRepository;
    private final VenditoreRepository venditoreRepository;
    private final PacchettoRepository pacchettoRepository;

    public CarrelloHandler(AcquirenteRepository acquirenteRepository,
                           ProdottoRepository prodottoRepository,
                           VenditoreRepository venditoreRepository, PacchettoRepository pacchettoRepository) {
        this.acquirenteRepository = acquirenteRepository;
        this.prodottoRepository = prodottoRepository;
        this.venditoreRepository = venditoreRepository;
        this.pacchettoRepository = pacchettoRepository;
    }

    public List<Prodotto> mostraCarrello(Long acquirenteId) {
        Acquirente acquirente = acquirenteRepository.findById(acquirenteId)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));

        return acquirente.getCarrello().getListaProdotti();
    }

    public void aggiungiProdottoAlCarrello(Long acquirenteId, Long prodottoId) {
        Acquirente acquirente = acquirenteRepository.findById(acquirenteId)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));

        Prodotto prodotto = prodottoRepository.findById(prodottoId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

        if(prodotto.getStato() instanceof Approvato) {

            if (acquirente.getCarrello().getListaProdotti().contains(prodotto)) {
                throw new RuntimeException("Il prodotto è già presente nel carrello");
            }

            acquirente.getCarrello().addProdotto(prodotto);
            acquirenteRepository.save(acquirente);

        } else throw new RuntimeException("Il proddotto non è approvato");
    }

    public void rimuoviProdottoDaCarrello(Long acquirenteId, Long prodottoId) {
        Acquirente acquirente = acquirenteRepository.findById(acquirenteId)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));

        Carrello carrello = acquirente.getCarrello();

        Prodotto prodotto = carrello.getListaProdotti().stream()
                .filter(p -> p.getId().equals(prodottoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Prodotto non presente nel carrello"));

        carrello.removeProdotto(prodotto);
        acquirenteRepository.save(acquirente);
    }

    public void svuotaCarrello(Long acquirenteId) {
        Acquirente acquirente = acquirenteRepository.findById(acquirenteId)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));

        acquirente.getCarrello().svuota();
        acquirenteRepository.save(acquirente);
    }

    public double calcolaTotaleCarrello(Long idAcquirente) {
        Acquirente acquirente = acquirenteRepository.findById(idAcquirente)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));
        return acquirente.getCarrello().sommaPrezzo();
    }

    public void addPacchettoAlCarrello(Long idAcquirente, Long pacchettoId) {
        Acquirente acquirente = acquirenteRepository.findById(idAcquirente)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));
        Pacchetto pacchetto = pacchettoRepository.findById(pacchettoId).orElseThrow(()
                -> new RuntimeException("Pacchetto non trovato"));

        List<Prodotto> prodottiDaAggiungere = new ArrayList<>();

        for(Prodotto prodotto : pacchetto.getProdotti()) {
            if (!acquirente.getCarrello().getListaProdotti().contains(prodotto)) {
                prodottiDaAggiungere.add(prodotto);
            }
        }
        if(!prodottiDaAggiungere.isEmpty()) {
            acquirente.getCarrello().getListaProdotti().addAll(prodottiDaAggiungere);
            acquirenteRepository.save(acquirente);
        } else
            throw new RuntimeException("Il pacchetto è gia presente nel carrello");
    }
}
