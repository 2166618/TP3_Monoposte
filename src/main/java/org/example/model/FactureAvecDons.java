package org.example.model;

/**
 * Facture: Pièce attestant de l'achat ou de la vente d'un bien ou d'un service, et qui en indique la nature et le prix.
 * Source: https://vitrinelinguistique.oqlf.gouv.qc.ca/fiche-gdt/fiche/8381190/facture
 */
public class FactureAvecDons extends FactureFabrique {

    //Attributs
    private double donDeLaFacture;


    //Constructeur par défaut
    public FactureAvecDons() {
    }

    //Constructeur de la facture avec don. Le don est automatiquement calculé à la création de la facture.
    public FactureAvecDons(String nomDeLAcheteur, double totalSansTaxes, double taxesApplicablesAuMomentDeLAchat, ModeDePaiement modeDePaiement) {
        super(nomDeLAcheteur, totalSansTaxes, taxesApplicablesAuMomentDeLAchat, modeDePaiement);
        this.donDeLaFacture = calculDuDon(super.getTotalAvecTaxes());
    }

    //Accesseurs et Mutateurs
    public double getDonDeLaFacture() {
        return donDeLaFacture;
    }

    public void setDonDeLaFacture(double donDeLaFacture) {
        this.donDeLaFacture = donDeLaFacture;
    }

    /**
     * Calcul le don de la facture selon le mode de paiement.
     * @param pTotalAvecTaxes
     * @return le don de charité de la facture
     */
    public double calculDuDon(double pTotalAvecTaxes){
        double don = 0;

        if(super.getModeDePaiement() == ModeDePaiement.ARGENT_COMPTANT){
            don = 0.02 * pTotalAvecTaxes;
        } else if (super.getModeDePaiement() == ModeDePaiement.CARTE_DE_DEBIT){
            don = 0.02 * (pTotalAvecTaxes - (pTotalAvecTaxes * 0.01));
        } else if (super.getModeDePaiement() == ModeDePaiement.CARTE_DE_CREDIT) {
            don = 0.02 * (pTotalAvecTaxes - (pTotalAvecTaxes * 0.03));
        }

        return don;
    }
}
