package unicam.progettofiliera.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.ProdottoRepository;
import unicam.progettofiliera.infrastructure.VenditoreRepository;
import unicam.progettofiliera.models.prodotti.Approvato;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.venditori.Distributore;
import unicam.progettofiliera.models.venditori.Produttore;
import unicam.progettofiliera.models.venditori.Trasformatore;
import unicam.progettofiliera.models.venditori.Venditore;

import java.util.List;

@Service
public class VenditoreHandler {
    ProdottoRepository prodottoRepository;
    VenditoreRepository venditoreRepository;

    public VenditoreHandler() {}

    @Autowired
    public VenditoreHandler(ProdottoRepository prodottoRepository,
                            VenditoreRepository venditoreRepository) {
        this.prodottoRepository = prodottoRepository;
        this.venditoreRepository = venditoreRepository;
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
        } else throw new IllegalArgumentException("il venditore non è un Trasformatore");
    }

    public void deleteProdotto(Long idVenditore, Long idProdotto) {
        Venditore venditore = venditoreRepository.findById(idVenditore).
                orElseThrow(() -> new RuntimeException("Venditore non trovato"));
        Prodotto prodotto = prodottoRepository.findById(idProdotto).
                orElseThrow(() -> new RuntimeException("Prodotto non trovato"));
        if(venditore.getProdottiPubblicati().contains(prodotto) && prodotto.getStato() instanceof Approvato){
            venditore.getProdottiPubblicati().remove(prodotto);
            prodottoRepository.delete(prodotto);
        } else
            throw new RuntimeException("Il prodotto non appartiene al venditore selezionato o non è stato ancora approvato.");
    }

}
