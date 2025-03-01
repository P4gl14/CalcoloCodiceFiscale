# Calcolatore di Codice Fiscale - Progetto JavaFX

## Descrizione del Progetto
Questo progetto è un'applicazione desktop sviluppata in Java utilizzando il framework **JavaFX**. Lo scopo dell'applicazione è calcolare il **Codice Fiscale** italiano in base ai dati anagrafici forniti dall'utente, come nome, cognome, data di nascita, sesso e luogo di nascita. Il codice fiscale è un codice alfanumerico univoco utilizzato in Italia per identificare i cittadini ai fini fiscali e amministrativi.

L'applicazione è progettata per essere intuitiva e facile da usare, con un'interfaccia grafica che guida l'utente attraverso il processo di inserimento dei dati e la visualizzazione del codice fiscale calcolato.

---

## Funzionalità Principali
1. **Inserimento Dati Anagrafici**:
   - Nome e cognome.
   - Data di nascita (con un calendario integrato).
   - Sesso (maschio/femmina).
   - Luogo di nascita (con un menu a tendina per selezionare il comune).

2. **Calcolo del Codice Fiscale**:
   - Implementazione dell'algoritmo di calcolo del codice fiscale secondo le normative italiane.
   - Gestione delle eccezioni per dati mancanti o non validi.

3. **Interfaccia Grafica**:
   - Design moderno e responsive.
   - Messaggi di errore chiari in caso di input non validi.
   - Visualizzazione del codice fiscale calcolato in un campo di testo non modificabile.

4. **Pulsanti di Azione**:
   - **Calcola**: Avvia il calcolo del codice fiscale.
   - **Reset**: Pulisce tutti i campi di input e ripristina l'interfaccia allo stato iniziale.

---

## Struttura del Codice
Il progetto è organizzato in package e classi per garantire modularità e manutenibilità:

### Package Principali
1. **`it.codicefiscale`**: Contiene la classe principale dell'applicazione.
2. **`it.codicefiscale.controller`**: Gestisce la logica dell'interfaccia grafica.
3. **`it.codicefiscale.model`**: Contiene le classi del modello dati (es. `Persona`, `CodiceFiscale`).
4. **`it.codicefiscale.utils`**: Include utility per il calcolo del codice fiscale (es. gestione delle lettere, calcolo del codice catastale, controllo omocodia).

### Classi Principali
- **`MainApp`**: Classe principale che avvia l'applicazione JavaFX.
- **`MainController`**: Gestisce l'interazione tra l'interfaccia grafica e la logica di business.
- **`Persona`**: Modello per rappresentare i dati anagrafici dell'utente.
- **`CodiceFiscaleCalculator`**: Classe che implementa l'algoritmo di calcolo del codice fiscale.

---

## Algoritmo di Calcolo del Codice Fiscale
Il codice fiscale è composto da 16 caratteri alfanumerici, calcolati come segue:

1. **Cognome**:
   - Si prendono le prime tre consonanti (es. ROSSI → RSS).
   - Se le consonanti sono insufficienti, si aggiungono vocali e, in ultima istanza, la lettera "X".

2. **Nome**:
   - Si prendono le prime tre consonanti, escludendo la seconda se ci sono quattro o più consonanti (es. MARIO → MRA).

3. **Data di Nascita e Sesso**:
   - Anno di nascita (ultime due cifre).
   - Mese di nascita (rappresentato da una lettera, es. A = Gennaio, B = Febbraio, ecc.).
   - Giorno di nascita (aggiungendo 40 per le donne, es. 25 → 25, 25 per una donna → 65).

4. **Luogo di Nascita**:
   - Codice catastale del comune (es. H501 per Roma).

5. **Carattere di Controllo**:
   - Un carattere calcolato in base a un algoritmo di controllo sui primi 15 caratteri.

---

## Tecnologie Utilizzate
- **Java**: Linguaggio di programmazione principale.
- **JavaFX**: Framework per la creazione dell'interfaccia grafica.
- **Scene Builder**: Strumento per progettare l'interfaccia grafica in formato FXML.
- **Maven**: Gestione delle dipendenze e build automation.

---

## Requisiti di Sistema
- Java Development Kit (JDK) 11 o superiore.
- JavaFX SDK (incluso nel progetto o installato separatamente).
- Un IDE compatibile con Java e JavaFX (es. IntelliJ IDEA, Eclipse con plugin JavaFX).

---

## Istruzioni per l'Esecuzione
1. Clona il repository del progetto:
   ```bash
   git clone https://github.com/tuo-repository/calcolatore-codice-fiscale.git

2. **Apri il progetto nel tuo IDE**:
   - Importa il progetto come progetto Maven (se utilizzi IntelliJ IDEA o Eclipse).
   - Assicurati che tutte le dipendenze siano correttamente configurate.

3. **Configura JavaFX**:
   - Se JavaFX non è incluso nel progetto, scarica il JavaFX SDK dal [sito ufficiale](https://gluonhq.com/products/javafx/).
   - Configura il tuo IDE per utilizzare JavaFX:
     - **IntelliJ IDEA**: Vai in `File > Project Structure > Libraries` e aggiungi il percorso della libreria JavaFX.
     - **Eclipse**: Aggiungi il percorso della libreria JavaFX nelle impostazioni del progetto.

4. **Esegui l'applicazione**:
   - Trova la classe `MainApp` nel package `it.codicefiscale`.
   - Fai clic con il tasto destro sulla classe e seleziona **Run** (o premi `Shift + F10` in IntelliJ IDEA).
   - Usa l'applicazione per inserire i dati anagrafici e calcolare il codice fiscale.
