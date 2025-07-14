package unicam.progettofiliera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.progettofiliera.infrastructure.AcquirenteRepository;
import unicam.progettofiliera.infrastructure.EventoRepository;
import unicam.progettofiliera.models.eventi.Evento;
import unicam.progettofiliera.models.prodotti.Prodotto;
import unicam.progettofiliera.models.utenti.acquirenti.Acquirente;
import unicam.progettofiliera.models.utenti.acquirenti.Carrello;


@Service
public class AcquirenteHandler {

    private final AcquirenteRepository acquirenteRepository;
    private final EventoRepository eventoRepository;


    @Autowired
    public AcquirenteHandler(AcquirenteRepository acquirenteRepository,
                             EventoRepository eventoRepository) {

        this.acquirenteRepository = acquirenteRepository;
        this.eventoRepository = eventoRepository;

    }

    /**
     * permette all'utente di effettuare il pagamento, svuota il carrello
     * ed emette una ricevuta sottofroma di String
     * @param acquirenteId
     * @return
     */
    public String checkout(Long acquirenteId){
        Acquirente acquirente = acquirenteRepository.findById(acquirenteId)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));

        Carrello carrello = acquirente.getCarrello();

        StringBuilder prodotti = new StringBuilder();
        if (!carrello.getListaProdotti().isEmpty()) {
            for (Prodotto p : carrello.getListaProdotti()) {
                prodotti.append("- ").append(p.getNome()).append(" (€").append(p.getPrezzo()).append(")\n");
            }
            String ricevuta = """
                    Acquirente: %s
                    Prodotti: %s
                    Totale: €%.2f
                    """.formatted(
                    acquirente.getNome(),
                    prodotti.toString().trim(),
                    acquirente.getCarrello().sommaPrezzo()
            );
            carrello.svuota();
            acquirenteRepository.save(acquirente);
            return ricevuta;
        } throw new RuntimeException("il carrello è vuoto");
    }

    /**
     * questo metodo permette all'acquirente di prenotare un evento tramite
     * l'inserimento dell'id corrispondente per un numero di partecipanti a sua scelta
     *
     * @param eventoId
     * @param partecipanti
     *
     */
    public String prenotaEvento(Long eventoId, int partecipanti){

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("evento non trovato"));

        if(evento.getPostiDisponibili() > 0 && evento.getPostiDisponibili() - partecipanti >= 0) {
            evento.setPostiDisponibili(evento.getPostiDisponibili() - partecipanti);
            eventoRepository.save(evento);
            return evento.getNome();
            } throw new  IllegalArgumentException("Posti disponibili non sufficienti");
        }

}



