package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Archive {

    private List<FactureAvecDons> factures = new ArrayList<>();

    private List<FactureAvecDons> facturesRemboursees = new ArrayList<>();

    public Archive() {
    }

    public List<FactureAvecDons> getFactures() {
        return factures;
    }

    public List<FactureAvecDons> getFacturesRemboursees() {
        return facturesRemboursees;
    }

    /**
     * Méthode permettant d'ajouter une facture à la liste de factures
     * @param facture
     */
    public void ajouterUneFacture(FactureAvecDons facture){
        this.factures.add(facture);
    }

    public void ajouterUneFactureRemboursee(FactureAvecDons facture){
        this.facturesRemboursees.add(facture);
    }
}
