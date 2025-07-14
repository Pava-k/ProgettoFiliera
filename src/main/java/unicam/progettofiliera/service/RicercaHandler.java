package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.EventoRepository;
import unicam.progettofiliera.infrastructure.ProdottoRepository;
import unicam.progettofiliera.models.eventi.Evento;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.prodotti.state.StatoProdottoEnum;

import java.util.List;

@Service
public class RicercaHandler {
    EventoRepository eventoRepository;
    ProdottoRepository prodottoRepository;

    @Autowired
    public RicercaHandler(EventoRepository eventoRepository, ProdottoRepository prodottoRepository) {
        this.eventoRepository = eventoRepository;
        this.prodottoRepository = prodottoRepository;
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

}
