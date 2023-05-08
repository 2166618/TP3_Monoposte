package org.example.model;

public class FactureFabrique implements Facture {

    //Attributs
    private String nomDeLAcheteur;
    private double totalSansTaxes;
    private double taxesApplicablesAuMomentDeLAchat;
    private double totalAvecTaxes;
    private ModeDePaiement modeDePaiement;


    //Constructeur par défaut
    public FactureFabrique() {
    }

    //Constructeur de la facture. Le total avec taxes est automatiquement calculé à la création de la facture.
    public FactureFabrique(String nomDeLAcheteur, double totalSansTaxes, double taxesApplicablesAuMomentDeLAchat, ModeDePaiement modeDePaiement) {
        this.nomDeLAcheteur = nomDeLAcheteur;
        this.totalSansTaxes = totalSansTaxes;
        this.taxesApplicablesAuMomentDeLAchat = taxesApplicablesAuMomentDeLAchat;
        this.modeDePaiement = modeDePaiement;
        this.totalAvecTaxes = (totalSansTaxes + taxesApplicablesAuMomentDeLAchat);
    }

    //Accesseurs et Mutateurs
    public String getNomDeLAcheteur() {
        return nomDeLAcheteur;

    }

    public void setNomDeLAcheteur(String nomDeLAcheteur) {
        this.nomDeLAcheteur = nomDeLAcheteur;
    }

    public double getTotalSansTaxes() {
        return totalSansTaxes;
    }

    public void setTotalSansTaxes(double totalSansTaxes) {
        this.totalSansTaxes = totalSansTaxes;
    }

    public double getTaxesApplicablesAuMomentDeLAchat() {
        return taxesApplicablesAuMomentDeLAchat;
    }

    public void setTaxesApplicablesAuMomentDeLAchat(double taxesApplicablesAuMomentDeLAchat) {
        this.taxesApplicablesAuMomentDeLAchat = taxesApplicablesAuMomentDeLAchat;
    }

    public ModeDePaiement getModeDePaiement() {
        return modeDePaiement;
    }

    public void setModeDePaiement(ModeDePaiement modeDePaiement) {
        this.modeDePaiement = modeDePaiement;
    }

    public double getTotalAvecTaxes() {
        return totalAvecTaxes;
    }

    public void setTotalAvecTaxes(double totalAvecTaxes) {
        this.totalAvecTaxes = totalAvecTaxes;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "nomDeLAcheteur='" + nomDeLAcheteur + '\'' +
                ", totalSansTaxes=" + totalSansTaxes +
                ", taxesApplicablesAuMomentDeLAchat=" + taxesApplicablesAuMomentDeLAchat +
                ", totalAvecTaxes=" + totalAvecTaxes +
                ", modeDePaiement=" + modeDePaiement +
                '}';
    }
}
