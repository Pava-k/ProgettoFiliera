package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.EventoRepository;
import unicam.progettofiliera.infrastructure.ProdottoRepository;
import unicam.progettofiliera.infrastructure.VenditoreRepository;
import unicam.progettofiliera.models.eventi.Evento;
import unicam.progettofiliera.models.prodotti.Pacchetto;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.state.StatoProdottoEnum;
import unicam.progettofiliera.models.utenti.venditori.Distributore;
import unicam.progettofiliera.models.utenti.venditori.Venditore;
import java.util.ArrayList;
import java.util.List;

/**
 * l'handler permette la ricerca di prodotti, eventi o pacchetti generica o via keyword
 */
@Service
public class RicercaHandler {

    private final VenditoreRepository venditoreRepository;

    EventoRepository eventoRepository;

    ProdottoRepository prodottoRepository;

    @Autowired
    public RicercaHandler(EventoRepository eventoRepository,
                          ProdottoRepository prodottoRepository,
                          VenditoreRepository venditoreRepository) {

        this.eventoRepository = eventoRepository;
        this.prodottoRepository = prodottoRepository;
        this.venditoreRepository = venditoreRepository;
    }

    public List<Prodotto> ricercaProdotti() {

        return prodottoRepository.findByStatoEnum(StatoProdottoEnum.APPROVATO);
    }

    public List<Prodotto> ricercaProdottoByName(String keyword) {

        return prodottoRepository.findByStatoEnumAndNomeContainingIgnoreCase(StatoProdottoEnum.APPROVATO, keyword);    }

    public List<Evento> ricercaEventi() {

        return eventoRepository.findAll();
    }

    public List<Evento> ricercaEventoByName(String keyword) {

        return eventoRepository.findByNomeContainingIgnoreCase(keyword);
    }

    public List<Pacchetto> ricercaPacchetti() {

        List<Pacchetto> pacchetti = new ArrayList<>();

        for(Venditore venditore : venditoreRepository.findAll()) {
            if(venditore instanceof Distributore distributore) {
                if(!distributore.getPacchettiCaricati().isEmpty()){
                    pacchetti.addAll(distributore.getPacchettiCaricati());
                }
            }
        } return pacchetti;
    }
}
