package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FactureAvecDonsTest {

    @Test
    void etantDonneUnTotalAvecTaxesPositifEtUnModeDePaiementArgentComptant_quandCalculDuDon_alorsResultatNombreAVirgule() {
        //ACTEUR
        FactureAvecDons factureAvecDons = new FactureAvecDons();
        ModeDePaiement modeDePaiement = ModeDePaiement.ARGENT_COMPTANT;
        int totalAvecTaxesPositif = 30;
        double precision = 0.000000000000001;

        //ACTION
        factureAvecDons.setModeDePaiement(modeDePaiement);
        double don = factureAvecDons.calculDuDon(totalAvecTaxesPositif);

        //ASSERTION
        assertEquals(0.6,precision,don);
    }

    @Test
    void etantDonneUnTotalAvecTaxesPositifEtUnModeDePaiementCarteDeDebit_quandCalculDuDon_alorsResultatNombreAVirgule() {
        //ACTEUR
        FactureAvecDons factureAvecDons = new FactureAvecDons();
        ModeDePaiement modeDePaiement = ModeDePaiement.CARTE_DE_DEBIT;
        int totalAvecTaxesPositif = 10;
        double delta = 0.000000000000001;

        //ACTION
        factureAvecDons.setModeDePaiement(modeDePaiement);
        double don = factureAvecDons.calculDuDon(totalAvecTaxesPositif);

        //ASSERTION
        assertEquals(0.198,delta,don);
    }

    @Test
    void etantDonneUnTotalAvecTaxesPositifEtUnModeDePaiementCarteDeCredit_quandCalculDuDon_alorsResultatNombreAVirgule() {
        //ACTEUR
        FactureAvecDons factureAvecDons = new FactureAvecDons();
        ModeDePaiement modeDePaiement = ModeDePaiement.CARTE_DE_CREDIT;
        int totalAvecTaxesPositif = 30;
        double delta = 0.000000000000001;

        //ACTION
        factureAvecDons.setModeDePaiement(modeDePaiement);
        double don = factureAvecDons.calculDuDon(totalAvecTaxesPositif);

        //ASSERTION
        assertEquals(  0.582,delta,don);
    }


}