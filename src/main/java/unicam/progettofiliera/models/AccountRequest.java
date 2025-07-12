package unicam.progettofiliera.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AccountRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long idRichiesta;
    private String nome;
    private String password;
    private Ruolo ruoloRichiesto;

    public AccountRequest() {}


    public AccountRequest(String nome, String password, Ruolo ruoloRichiesto) {
        this.nome = nome;
        this.password = password;
        this.ruoloRichiesto = ruoloRichiesto;
    }

    public Long getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(Long idRichiesta) {
        this.idRichiesta = idRichiesta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuoloRichiesto() {
        return ruoloRichiesto;
    }

    public void setRuoloRichiesto(Ruolo ruoloRichiesto) {
        this.ruoloRichiesto = ruoloRichiesto;
    }
}
