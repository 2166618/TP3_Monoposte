package org.example.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

/**
 * Ecran: classe permettant la gestion de changement de fenêtre dans l'application
 */
public class Ecran {

    private final BooleanProperty changerVersCreerUneFacture = new SimpleBooleanProperty();
    private final BooleanProperty changerVersAfficherLesDons = new SimpleBooleanProperty();

    public void setChangerVersCreerUneFacture(boolean changerVersCreerUneFacture) {
        this.changerVersCreerUneFacture.set(changerVersCreerUneFacture);
        //this.changerVersCreerUneFacture.set(false);
    }

    public void setChangerVersAfficherLesDons(boolean changerVersAfficherLesDons) {
        this.changerVersAfficherLesDons.set(changerVersAfficherLesDons);
    }

    public void ouvrirLaPageDeCreationDeFacture(ChangeListener<Boolean> ouvrirCreerFacture){
        changerVersCreerUneFacture.addListener(ouvrirCreerFacture);
    }

    public void ouvrirLaPageDeDons(ChangeListener<Boolean> ouvrirPageDeDons){
        changerVersAfficherLesDons.addListener(ouvrirPageDeDons);
    }
}
