package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.EventoRepository;
import unicam.progettofiliera.infrastructure.ProdottoRepository;
import unicam.progettofiliera.models.Evento;
import unicam.progettofiliera.modelsDaImplementare.Prodotto;

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
        return prodottoRepository.findAll();
    }

    public List<Prodotto> ricercaProdottoByName(String keyword) {
        return prodottoRepository.findByNomeContainingIgnoreCase(keyword);
    }

    public List<Evento> ricercaEventi() {
        return eventoRepository.findAll();
    }

    public List<Evento> ricercaEventoByName(String keyword) {
        return eventoRepository.findByNomeContainingIgnoreCase(keyword);
    }

}
