package org.example.model;

public interface Facture {

    String getNomDeLAcheteur();
    void setNomDeLAcheteur(String nomDeLAcheteur);

    double getTotalSansTaxes();

    void setTotalSansTaxes(double totalSansTaxes);

    double getTaxesApplicablesAuMomentDeLAchat();

    void setTaxesApplicablesAuMomentDeLAchat(double taxesApplicablesAuMomentDeLAchat);

    ModeDePaiement getModeDePaiement();

    void setModeDePaiement(ModeDePaiement modeDePaiement);

    double getTotalAvecTaxes();

    void setTotalAvecTaxes(double totalAvecTaxes);

}
