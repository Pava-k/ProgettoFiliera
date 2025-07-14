package unicam.progettofiliera.models;

import java.util.ArrayList;
import java.util.List;

public class PacchettoRequestDTO {

    private String nome;
    private String descrizione;
    private double sconto;
    private List<Long> prodottiId = new ArrayList<>();

    public PacchettoRequestDTO() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    public List<Long> getProdottiId() {
        return prodottiId;
    }

    public void setProdottiId(List<Long> prodottiId) {
        this.prodottiId = prodottiId;
    }
}

