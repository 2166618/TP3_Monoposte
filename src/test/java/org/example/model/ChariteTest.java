package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChariteTest {

    @Test
    void etantDonneLeTotalDesDonsAZeroAuDemarrageDeLApplication_quandDesFactureSontAjoutes_alorsLeTotalDesDonsSuperieurAZeroEstAffiche() {
        //ACTEUR
        Charite charite = new Charite();
        double totalDesDons = 0.00;
        List<FactureAvecDons> factures = new ArrayList<>();
        FactureAvecDons facture1 = new FactureAvecDons("Acheteur1", 5.00, 0.75, ModeDePaiement.CARTE_DE_DEBIT);  // DonsDeLaFacture: 0.11385
        FactureAvecDons facture2 = new FactureAvecDons("Acheteur2", 10.50, 1.58, ModeDePaiement.CARTE_DE_CREDIT);  // DonsDeLaFacture: 0.23435200000000003
        FactureAvecDons facture3 = new FactureAvecDons("Acheteur3", 66.66, 9.98, ModeDePaiement.ARGENT_COMPTANT);  // DonsDeLaFacture: 1.5328

        //ACTION
        factures.add(facture1);
        factures.add(facture2);
        factures.add(facture3);
        charite.setFactures(factures);
        charite.calculDuTotalDesDons();
        totalDesDons = charite.getTotalDesDons();

        //ASSERTION
        assertEquals(1.881002, totalDesDons);

    }

}