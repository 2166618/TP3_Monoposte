package org.example.controller;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.model.Ecran;

public class PageAccueilGraphicalController {

    private Ecran ecran;

    @FXML
    private Button boutonCreerUneFacture;

    @FXML
    private Button boutonDonsDeCharite;

    @FXML
    void creerUneFacture(ActionEvent event) {
        ecran.setChangerVersCreerUneFacture(true);
    }

    @FXML
    void donsDeCharite(ActionEvent event) {

        ecran.setChangerVersAfficherLesDons(true);
    }

    public PageAccueilGraphicalController(Ecran ecran) {
        this.ecran = ecran;
    }

    public void pageDeCreationDeFactureALaDemande(ChangeListener<Boolean> creerFacture){
        ecran.ouvrirLaPageDeCreationDeFacture(creerFacture);
    }

    public void pageDeDonsALaDemande(ChangeListener<Boolean> afficherLesDOns){
        ecran.ouvrirLaPageDeDons(afficherLesDOns);
    }

    public PageAccueilGraphicalController() {
    }

}
