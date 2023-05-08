package org.example.controller;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.model.Charite;
import org.example.model.Ecran;

import java.text.DecimalFormat;

public class TP2Controller  extends Application {
    //DecimalFormat decimalFormat = new DecimalFormat("0.00");
    Charite charite = new Charite();
    private static Ecran ecran = new Ecran();
    private static Scene sceneAccueil;
    private static Stage primaryStagePartagee;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStagePartagee = primaryStage;

        primaryStage.setTitle("TopVentes");
        //instanciation des FXMLLoader

        FXMLLoader fxmlLoaderCreerUneFacture = new FXMLLoader(getClass().getResource("/view/CreerUneFacture.fxml"));
        FXMLLoader fxmlLoaderAfficherLesDons= new FXMLLoader(getClass().getResource("/view/DonsDeCharite.fxml"));

        //creation des Parent et des scènes associées
        Parent rootCreerUneFacture = fxmlLoaderCreerUneFacture.load();
        Scene sceneCreerUneFacture = new Scene(rootCreerUneFacture,600,400);

        Parent rootAfficherLesDons = fxmlLoaderAfficherLesDons.load();
        Scene sceneAfficherLesDons = new Scene(rootAfficherLesDons,600,400);
        DonsDeChariteGraphicalController donsController = fxmlLoaderAfficherLesDons.getController();
        CreerUneFactureGraphicalController factureConroller = fxmlLoaderCreerUneFacture.getController();

        FXMLLoader fxmlLoaderPageAccueil = new FXMLLoader(getClass().getResource("/view/PageAccueil.fxml"));
        fxmlLoaderPageAccueil.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                PageAccueilGraphicalController pageAccueilGraphicalController = new PageAccueilGraphicalController(ecran);
                ChangeListener<Boolean> allerALaCreationDeFacture = new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        primaryStagePartagee.setScene(sceneCreerUneFacture);
                    }
                };
                pageAccueilGraphicalController.pageDeCreationDeFactureALaDemande(allerALaCreationDeFacture);

                ChangeListener<Boolean> allerAuxDons = new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        donsController.getAfficheDonsDeCharite().setText(String.valueOf(factureConroller.decimalFormat.format(factureConroller.getCharite().getTotalDesDons())));
                        primaryStagePartagee.setScene(sceneAfficherLesDons);
                    }
                };
                pageAccueilGraphicalController.pageDeDonsALaDemande(allerAuxDons);
                return pageAccueilGraphicalController;
            }
        });

        Parent rootAccueil= fxmlLoaderPageAccueil.load();
        sceneAccueil = new Scene(rootAccueil, 600,400);
        primaryStage.setScene(sceneAccueil);
        primaryStage.show();

    }
    public static Scene getMainScene(){
        return sceneAccueil;
    }

    public static Stage getMainStage(){
        return primaryStagePartagee;
    }

    public static Ecran getEcran() {
        return ecran;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
