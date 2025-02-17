package com.example.codicefiscale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;
import java.util.Set;


public class CodiceFiscaleController {
    @FXML
    public TextField txtCognome;
    @FXML
    public Label lblCognome;
    @FXML
    public TextField txtNome;
    @FXML
    public Label lblNome;
    @FXML
    public DatePicker calNascita;
    @FXML
    public Label lblNascita;
    @FXML
    public ComboBox cmbComune;
    @FXML
    public Label lblComune;
    @FXML
    public CheckBox ckbEstero;
    @FXML
    public RadioButton rbtnMaschio;
    @FXML
    public RadioButton rbtnFemmina;
    @FXML
    public Label lblSesso;
    @FXML
    public ToggleGroup sesso;
    @FXML
    public Label lblCodiceFiscale;

    private CodiceFiscale codice;
    private boolean estero = false;



    public void initialize() {
        filtraCmb(cmbComune, CodiceLuogo.comuniCodici.keySet());
    }


    @FXML
    private void filtraCmb(ComboBox<String> cmb, Set<String> elenco) {
        ObservableList<String> osservabile = FXCollections.observableArrayList(elenco);

        FilteredList<String> filtrata = new FilteredList<>(osservabile, p -> true);

        cmb.getEditor().clear();

        cmb.setItems(filtrata);

        cmb.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            final TextField editor = cmb.getEditor();
            final String selected = cmb.getSelectionModel().getSelectedItem();

            if (selected == null || !selected.equals(editor.getText())) {
                filtrata.setPredicate(comune -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    return comune.toLowerCase().contains(lowerCaseFilter);
                });

                cmb.show();
            }
        });

    }



    @FXML
    protected void onEsteroButtonClick() {
        estero = !estero;
        filtraCmb(cmbComune, estero ? CodiceLuogo.esteroCodici.keySet() : CodiceLuogo.comuniCodici.keySet());
    }

    @FXML
    protected void onCalcolaButtonClick() {

        boolean ready = true;


        String cognome;

        if((cognome = txtCognome.getText().trim()).isEmpty()){
            lblCognome.setText("Cognome non valido");
            ready = false;
        }else lblCognome.setText("");


        String nome;

        if ((nome = txtNome.getText().trim()).isEmpty()){
            lblNome.setText("Nome non valido");
            ready = false;
        }else lblNome.setText("");


        LocalDate data;

        if ((data = calNascita.getValue()) == null){
            lblNascita.setText("Data non valida");
            ready = false;
        }else lblNascita.setText("");


        String comune;

        if ((comune = (String)cmbComune.getSelectionModel().getSelectedItem()) == null || comune.isEmpty()){
            lblComune.setText("Seleziona il comune");
            ready = false;
        } else lblComune.setText("");


        boolean maschio = true;

        if (sesso.getSelectedToggle() == null){
            lblSesso.setText("Seleziona \nil sesso");
            ready = false;
        }else{
            maschio = rbtnMaschio.isSelected();
            lblSesso.setText("");
        }


        if (ready){
            codice = new CodiceFiscale(cognome, nome, data, comune, maschio, estero);
            lblCodiceFiscale.setText(codice.calcolaCodiceFiscale());
        }else{
            lblCodiceFiscale.setText("");
        }

    }

}