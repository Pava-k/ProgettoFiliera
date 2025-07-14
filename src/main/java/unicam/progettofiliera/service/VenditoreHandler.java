package unicam.progettofiliera.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.AcquirenteRepository;
import unicam.progettofiliera.infrastructure.ProdottoRepository;
import unicam.progettofiliera.infrastructure.VenditoreRepository;
import unicam.progettofiliera.models.prodotti.Pacchetto;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.state.Approvato;
import unicam.progettofiliera.models.utenti.acquirenti.Acquirente;
import unicam.progettofiliera.models.utenti.acquirenti.Carrello;
import unicam.progettofiliera.models.utenti.venditori.Distributore;
import unicam.progettofiliera.models.utenti.venditori.Produttore;
import unicam.progettofiliera.models.utenti.venditori.Trasformatore;
import unicam.progettofiliera.models.utenti.venditori.Venditore;

import java.util.List;

@Service
public class VenditoreHandler {

    private final ProdottoRepository prodottoRepository;

    private final VenditoreRepository venditoreRepository;

    private final AcquirenteRepository acquirenteRepository;

    @Autowired
    public VenditoreHandler(ProdottoRepository prodottoRepository,
                            VenditoreRepository venditoreRepository,
                            AcquirenteRepository acquirenteRepository) {

        this.prodottoRepository = prodottoRepository;
        this.venditoreRepository = venditoreRepository;
        this.acquirenteRepository = acquirenteRepository;
    }

    public void caricaProdottoProduttore(Long idVenditore, String nome, String descrizione,
                                         double prezzo, String processiProduttivi) {

        Venditore venditore = venditoreRepository.findById(idVenditore).
                orElseThrow(() -> new RuntimeException("Venditore non trovato"));

        if (venditore instanceof Produttore produttore){
            Prodotto prodotto = produttore.creaProdotto(nome, descrizione, prezzo, processiProduttivi);
            prodottoRepository.save(prodotto);
        } else throw new IllegalArgumentException("il venditore non è un Produttore");
    }

    public void caricaProdottoTrasformatore(Long idVenditore, String nome, String descrizione, double prezzo,
                                            String processiTraformativi, List<Produttore> collaboratori) {

        Venditore venditore = venditoreRepository.findById(idVenditore).
                orElseThrow(() -> new RuntimeException("Venditore non trovato"));

        if (venditore instanceof Trasformatore trasformatore){
            Prodotto prodotto = trasformatore.creaProdotto(nome, descrizione, prezzo, processiTraformativi, collaboratori);
            prodottoRepository.save(prodotto);
        } else throw new IllegalArgumentException("il venditore non è un Trasformatore");
    }

    public void caricaProdottoDistributore(String nome, Long idVenditore, String descrizione, double prezzo){

        Venditore venditore = venditoreRepository.findById(idVenditore).
                orElseThrow(() -> new RuntimeException("Venditore non trovato"));

        if (venditore instanceof Distributore distributore){
            Prodotto prodotto = distributore.creaProdotto(nome, descrizione, prezzo);
            prodottoRepository.save(prodotto);
        } else throw new IllegalArgumentException("il venditore non è un Distributore");
    }

    public void caricaPacchetto(Long idDistributore, String nome, String descrizione,
                                double prezzo, List<Long> prodottiId) {

        Venditore venditore = venditoreRepository.findById(idDistributore).
                orElseThrow(() -> new RuntimeException("Venditore non trovato"));

        List<Prodotto> prodotti = prodottoRepository.findAllById(prodottiId);

        if (venditore instanceof Distributore distributore){
            Pacchetto pacchetto = distributore.creaPacchetto(nome, descrizione, prezzo, prodotti);
        venditoreRepository.save(venditore); //aggiorna anche la persistenza dei pacchetti
        } else throw new IllegalArgumentException("il venditore non è un distributore");
    }

    /**
     * rimuove attraverso l'id fornito il prodotto corrispondente dal marketplace
     * e dal carrello di ogni acquirente che lo possiede
     * @param idVenditore
     * @param idProdotto
     */
    public void deleteProdotto(Long idVenditore, Long idProdotto) {
        Venditore venditore = venditoreRepository.findById(idVenditore).
                orElseThrow(() -> new RuntimeException("Venditore non trovato"));
        Prodotto prodotto = prodottoRepository.findById(idProdotto).
                orElseThrow(() -> new RuntimeException("Prodotto non trovato"));
        if(venditore.getProdottiPubblicati().contains(prodotto) && prodotto.getStato() instanceof Approvato){

            //rimuove ogni pacchetto contenente quel prodotto dal marketplace
            if(venditore instanceof Distributore distributore) {
                removePacchettiContaining(distributore, prodotto);
            }
            // Rimuovi il prodotto da tutti i carrelli degli acquirenti
            removeProdottoDaiCarrelli(prodotto);
            // Rimuove il prodotto dalla lista dei prodotti del venditore e dalla repository
            venditore.getProdottiPubblicati().remove(prodotto);
            prodottoRepository.delete(prodotto);

        } else
            throw new RuntimeException("Il prodotto non appartiene al venditore selezionato o non è state ancora approvato.");
    }

    /**
     * rimuove il prodotto dato in input da tutti i carrelli degli acquirenti
     * @param prodotto
     */
    private void removeProdottoDaiCarrelli(Prodotto prodotto) {
        List<Acquirente> listaAcquirenti = acquirenteRepository.findAll();
        for (Acquirente acquirente : listaAcquirenti) {
            Carrello carrello = acquirente.getCarrello();
            if (carrello.getListaProdotti().contains(prodotto)) {
                carrello.removeProdotto(prodotto);
                acquirenteRepository.save(acquirente); // salva il carrello aggiornato
            }
        }
    }

    /**
     * rimuove  dal marketplace ogni pacchetto che contiene il prodotto dato in input
     * @param distributore
     * @param prodotto
     */
    private void removePacchettiContaining(Distributore distributore, Prodotto prodotto) {

        List<Pacchetto> pacchettiDaRimuovere = distributore.getPacchettiCaricati().stream()
                .filter(pacchetto -> pacchetto.getProdotti().contains(prodotto))
                .toList();

        // rimuove il pacchetto dalla lista del distributore
        distributore.getPacchettiCaricati().removeAll(pacchettiDaRimuovere);

        venditoreRepository.save(distributore);
    }
}
