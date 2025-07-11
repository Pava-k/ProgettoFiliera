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

    public List<Evento> ricercaEvento(String keyword) {
       return eventoRepository.findByNomeContainingIgnoreCase(keyword);
    }

    public List<Prodotto> ricercaProdotto(String keyword) {
        return prodottoRepository.findByNomeContainingIgnoreCase(keyword);
    }

}
