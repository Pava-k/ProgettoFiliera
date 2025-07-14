package unicam.progettofiliera.models.utenti.acquirenti;

import jakarta.persistence.*;
import unicam.progettofiliera.models.prodotti.Prodotto;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrello {

    @ManyToMany
    @JoinTable(
        name = "prodotti_carrello",
        joinColumns = @JoinColumn(name = "carrello_id"),
        inverseJoinColumns = @JoinColumn(name = "prodotto_id")
    )
    List<Prodotto> listaProdotti;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Carrello() {
        this.listaProdotti = new ArrayList<Prodotto>();
    }

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void addProdotto(Prodotto prodotto) {
        this.listaProdotti.add(prodotto);
    }

    public void removeProdotto(Prodotto prodotto) {
        this.listaProdotti.remove(prodotto);
    }

    public double sommaPrezzo(){
        double totale = 0;
        for(Prodotto p : listaProdotti) {
            totale += p.getPrezzo();
        }
        return totale;
    }

    public void svuota() {
        listaProdotti.clear();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
