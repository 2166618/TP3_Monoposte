package org.example.model;

import org.example.controller.CreerUneFactureGraphicalController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArchiveTest {

    @Test
    void etantDonneCreerUneFactureAvecEntreeDuTotalSansTaxesNegatif_quandLaFactureEstNonPresenteDansLaListeDeFacturesDesArchives_alorsLePanneErreurAfficheLeMessageDErreur() {
        //ACTEUR
        CreerUneFactureGraphicalController creerUneFactureGraphicalController = new CreerUneFactureGraphicalController();
        Archive archive = new Archive();

        //ACTION


        //ASSERTION

    }

    @Test
    void etantDonneCreerUneFactureAvecEntreeDuTotalSansTaxesNegatif_quandLaFactureEstNonPresenteDansLaListeDeFacturesDesArchives_alorsLesListesFacturesEtFacturesRemboursesResteInchangees() {
        //ACTEUR

        //ACTION

        //ASSERTION
    }

    @Test
    void etantDonneCreerUneFactureAvecEntreeDuTotalSansTaxesNegatif_quandLaFactureEstPresenteDansLaListeDeFacturesDesArchives_alorsLaFactureEstSupprimeDeLaListeDeFactureEtAjouterALaListeDeFactureRemboursees() {
        //ACTEUR

        //ACTION

        //ASSERTION
    }
}