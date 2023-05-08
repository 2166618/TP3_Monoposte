package org.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Don de charité: Somme d'argent versée à un organisme de charité ou à une association canadienne de sport amateur enregistrés auprès du Ministre.
 * Source: https://vitrinelinguistique.oqlf.gouv.qc.ca/fiche-gdt/fiche/2085827/don-de-charite?utm_campaign=Redirection%20des%20anciens%20outils&utm_content=id_fiche%3D2085827&utm_source=GDT
 */
public class Charite {


    //Attributs
    private double totalDesDons;
    private List<FactureAvecDons> factures = new ArrayList<>();

    //Constructeur par défaut
    public Charite() {
    }

    /**
     * Méthode permettant de cumuler le total des dons de charité pour l'ensemble des factures crées
     */
    public void calculDuTotalDesDons(){
        double total=0;
        for (FactureAvecDons facture: factures) {
            total += facture.getDonDeLaFacture();
        }
        this.totalDesDons = total;
    }

    //Accesseurs et mutateurs
    public double getTotalDesDons() {
        return totalDesDons;
    }

    public void setTotalDesDons(double totalDesDons) {
        this.totalDesDons = totalDesDons;
    }

    public List<FactureAvecDons> getFactures() {
        return factures;
    }

    public void setFactures(List<FactureAvecDons> factures) {
        this.factures = factures;
    }

    /**
     * Méthode permettant d'ajouter une facture à la liste de factures
     * @param facture
     */
    public void ajouterUneFacture(FactureAvecDons facture){
        this.factures.add(facture);
    }
}
