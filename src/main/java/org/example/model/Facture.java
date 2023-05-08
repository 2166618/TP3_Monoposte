package org.example.model;

public interface Facture {

    public void setNomDeLAcheteur(String nomDeLAcheteur);

    public double getTotalSansTaxes();

    public void setTotalSansTaxes(double totalSansTaxes);

    public double getTaxesApplicablesAuMomentDeLAchat();

    public void setTaxesApplicablesAuMomentDeLAchat(double taxesApplicablesAuMomentDeLAchat);

    public ModeDePaiement getModeDePaiement();

    public void setModeDePaiement(ModeDePaiement modeDePaiement);

    public double getTotalAvecTaxes();

    public void setTotalAvecTaxes(double totalAvecTaxes);

}
