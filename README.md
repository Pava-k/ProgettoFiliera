# Piattaforma di Digitalizzazione e Valorizzazione della Filiera Agricola Locale

## Descrizione del Progetto

La piattaforma permette la gestione, valorizzazione e tracciabilità dei prodotti agricoli di un territorio comunale, facilitando la condivisione di informazioni tra tutti gli attori della filiera: produttori, trasformatori, distributori, curatori, animatori, acquirenti e utenti generici. Il sistema supporta inoltre l'organizzazione di eventi locali come fiere e tour guidati alle aziende.
                                               
---

## Tecnologie Utilizzate

* **Linguaggio:** Java 17
* **Framework:** Spring Boot
* **Database:** H2 
* **Persistenza:** Spring Data JPA
* **Architettura:** RESTful API

---

## Attori del Sistema

* **Produttore:** carica prodotti, metodi di coltivazione e vende nel marketplace.
* **Trasformatore:** carica prodotti, aggiunge informazioni sulle fasi di trasformazione, collega i prodotti ai produttori.
* **Distributore:** vende prodotti singoli e crea pacchetti gastronomici da vendere nel marketplace.
* **Curatore:** verifica e approva i contenuti caricati dai venditori.
* **Animatore della Filiera:** organizza fiere, eventi e visite presso le aziende locali.
* **Acquirente:** consulta informazioni e acquista prodotti o prenota eventi.
* **Utente Generico:** esplora i contenuti pubblici della piattaforma.
* **Gestore della Piattaforma:** gestisce gli aspetti amministrativi e autorizzazioni.

---


## Design Patterns Utilizzati

* **Factory Method:** per permettere ad ogni tipo di `Venditore` di creare i propri prodotti in modo specializzato.
* **State Pattern:** per rappresentare e gestire gli stati di approvazione di un `Prodotto` (`Approvato`, `InAttesa`, `Rifiutato`)

---

## Struttura del Progetto (Principali Package)

* `model` - Contiene le entità principali (Prodotto, Venditore, Pacchetto, Carrello, ecc.)
* `controller` - Espone le API REST
* `service` - Contiene la logica di business
* `repository` - Interfacce per la persistenza dei dati tramite JPA

---

## Istruzioni per l'uso

1. **Registrazione dell'utente** -> Richiesta creazione account tramite il controller per la registrazione, inserendo il ruolo richiesto
2. **Approvazione account** -> Approvazione richiesta creazione account attraverso il controller del gestore, inserendo l'id della richiesta
3. **Creazione prodotti** -> Creazione prodotto/evento tramite i controller venditore/animatore
4. **Approvazione prodotti** -> Approvazione dei prodotti tramite il controller del curatore, caricando i prodotti sul marketplace
5. **Visualizzazione/Acquisto prodotti** -> Aggiunta dei prodotti al carrello/prenotazione degli eventi tramite il controller dell'acquirente
