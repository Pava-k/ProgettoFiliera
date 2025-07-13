package unicam.progettofiliera.modelsDaImplementare;

import jakarta.persistence.*;
import unicam.progettofiliera.models.prodotti.Prodotto;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrello {

    @OneToMany
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

    public void aggiungiProdotto(Prodotto prodotto) {
        this.listaProdotti.add(prodotto);
    }

    public void rimuoviProdotto(Prodotto prodotto) {
        this.listaProdotti.remove(prodotto);
    }

    public double sommaPrezzo(){
        return listaProdotti.stream().mapToDouble(Prodotto::getPrezzo).sum();
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
