package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.Charite;

public class DonsDeChariteGraphicalController {

   // Charite donCharite = new Charite();
    @FXML
    private TextField afficheDonsDeCharite = new TextField();

    @FXML
    private Button boutonOK;

    @FXML
    void revenirALaPageAccueil(ActionEvent event) {
        TP2Controller.getEcran().setChangerVersAfficherLesDons(false);
        Scene sceneAccueil = TP2Controller.getMainScene();
        Stage revenirAccueil = TP2Controller.getMainStage();
        revenirAccueil.setScene(sceneAccueil);
    }


    public TextField getAfficheDonsDeCharite() {
        return afficheDonsDeCharite;
    }

    public void setAfficheDonsDeCharite(TextField afficheDonsDeCharite) {
        this.afficheDonsDeCharite = afficheDonsDeCharite;
    }

//    public void afficherLesDonsDeCharite(){
//        afficheDonsDeCharite.setText(String.valueOf(donCharite.getTotalDesDons()));
//    }
//
//    public Charite getDonCharite() {
//        return donCharite;
//    }
//
//    public void setDonCharite(Charite donCharite) {
//        this.donCharite = donCharite;
//    }
//
//    public void initialize(){
//        //afficherLesDonsDeCharite();
//    }
}
