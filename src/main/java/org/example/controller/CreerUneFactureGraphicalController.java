package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.model.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controlleur de l'interface de la création d'une facture. Elle permet d'entrer manuellement le nom de l'acheteur, le total
 * sans taxe, les taxes applicables et le mode de paiement. Lorsque le bouton confirmer est cliqué, elle affiche le total
 * avec taxes et les dons de la facture. Finalement le bouton OK permet de revenir l'interface de la page d'accueil.
 */
public class CreerUneFactureGraphicalController {
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    Archive archive = new Archive();
    Charite charite = new Charite();
    @FXML
    private Label afficheChariteDUneFacture;

    @FXML
    private Label afficheTotalFacture;

    @FXML
    private Button boutonConfirmerLaCreationDeLaFacture;

    @FXML
    private Button boutonOK;

    @FXML
    private ChoiceBox<ModeDePaiement> choisirUnModeDePaiement = new ChoiceBox<>();

    @FXML
    private AnchorPane detailsFacture;

    @FXML
    private TextField nomDeLAcheteur = new TextField();

    @FXML
    private TextField taxesApplicables = new TextField();

    @FXML
    private TextField totalSansTaxes = new TextField();

    @FXML
    public void OK(ActionEvent actionEvent) {
        paneErreur.setVisible(false);
        detailsFacture.setVisible(false);
    }

    @FXML
    private Button boutonOKErreur;

    @FXML
    private AnchorPane paneErreur;

    @FXML
    private Text messageErreur;

    public Label getAfficheChariteDUneFacture() {
        return afficheChariteDUneFacture;
    }

    public void setAfficheChariteDUneFacture(Label afficheChariteDUneFacture) {
        this.afficheChariteDUneFacture = afficheChariteDUneFacture;
    }

    public Label getAfficheTotalFacture() {
        return afficheTotalFacture;
    }

    public void setAfficheTotalFacture(Label afficheTotalFacture) {
        this.afficheTotalFacture = afficheTotalFacture;
    }

    public Button getBoutonConfirmerLaCreationDeLaFacture() {
        return boutonConfirmerLaCreationDeLaFacture;
    }

    public void setBoutonConfirmerLaCreationDeLaFacture(Button boutonConfirmerLaCreationDeLaFacture) {
        this.boutonConfirmerLaCreationDeLaFacture = boutonConfirmerLaCreationDeLaFacture;
    }

    public Button getBoutonOK() {
        return boutonOK;
    }

    public void setBoutonOK(Button boutonOK) {
        this.boutonOK = boutonOK;
    }

    public ChoiceBox<ModeDePaiement> getChoisirUnModeDePaiement() {
        return choisirUnModeDePaiement;
    }

    public void setChoisirUnModeDePaiement(ChoiceBox<ModeDePaiement> choisirUnModeDePaiement) {
        this.choisirUnModeDePaiement = choisirUnModeDePaiement;
    }

    public AnchorPane getDetailsFacture() {
        return detailsFacture;
    }

    public void setDetailsFacture(AnchorPane detailsFacture) {
        this.detailsFacture = detailsFacture;
    }

    public TextField getNomDeLAcheteur() {
        return nomDeLAcheteur;
    }

    public void setNomDeLAcheteur(TextField nomDeLAcheteur) {
        this.nomDeLAcheteur = nomDeLAcheteur;
    }

    public TextField getTaxesApplicables() {
        return taxesApplicables;
    }

    public void setTaxesApplicables(TextField taxesApplicables) {
        this.taxesApplicables = taxesApplicables;
    }

    public TextField getTotalSansTaxes() {
        return totalSansTaxes;
    }

    public void setTotalSansTaxes(TextField totalSansTaxes) {
        this.totalSansTaxes = totalSansTaxes;
    }

    public Button getBoutonOKErreur() {
        return boutonOKErreur;
    }

    public void setBoutonOKErreur(Button boutonOKErreur) {
        this.boutonOKErreur = boutonOKErreur;
    }

    public AnchorPane getPaneErreur() {
        return paneErreur;
    }

    public void setPaneErreur(AnchorPane paneErreur) {
        this.paneErreur = paneErreur;
    }

    public Text getMessageErreur() {
        return messageErreur;
    }

    public void setMessageErreur(Text messageErreur) {
        this.messageErreur = messageErreur;
    }

    public Charite getCharite() {
        return charite;
    }

    public Archive getArchive() {
        return archive;
    }

    /**
     * Les informations entrees lors de la création de la facture sont récupérées et un objet FactureAvecDons et créer à
     * partir de ces informations. Ensuite le traitement approprié s'en suit dépendamment si le totalSansTaxes est positif,
     * négatif(remboursement) ou égale à zéro.
     */
    public void recupererLesInformationsEntreesDansLaCreationDeLaFacture() {
        double totalSansTaxesTemp = 0.00;
        double taxesApplicablesTemps = 0.00;
        String nomDeLAcheteurTemp = "";

        if (validerLeNomDeLAcheteur() && validerLesMontants()) {
            nomDeLAcheteurTemp = getNomDeLAcheteur().getText();
            try {
                totalSansTaxesTemp = Double.parseDouble(getTotalSansTaxes().getText());
                taxesApplicablesTemps = Double.parseDouble(getTaxesApplicables().getText());
            } catch (NumberFormatException e) {
                getMessageErreur().setText(e.getMessage());
            }
            ModeDePaiement modeDePaiementTemp = getChoisirUnModeDePaiement().getValue();

            FactureAvecDons facture = new FactureAvecDons(nomDeLAcheteurTemp, totalSansTaxesTemp, taxesApplicablesTemps, modeDePaiementTemp);
            afficherLesDetailsDeLaFactureFinale(facture);
            charite.setFactures(archive.getFactures());
            if (totalSansTaxesTemp > 0) {
                archive.ajouterUneFacture(facture);
                charite.calculDuTotalDesDons();
            } else if (totalSansTaxesTemp < 0) {
                if (!procederAuRemboursement(facture)){
                    try {
                        throw new FactureException("Cette facture n'a pas été repéré dans les archives. Veuillez entrer le nom de l'acheteur exacte, " +
                                "les montants exactes du total sans taxes et des taxes applicable avec un signe négatif et assurer vous " +
                                "que le mode de paiment est identique.");
                    } catch (FactureException e) {
                        getMessageErreur().setText(e.getMessage());
                        paneErreur.setVisible(true);
                    }
                }
            } else {
                if (!archive.getFactures().contains(facture)) {
                    archive.ajouterUneFacture(facture);
                }
            }
        } else {
            paneErreur.setVisible(true);
        }

    }

    public void afficherLesDetailsDeLaFactureFinale(FactureAvecDons facture) {
        getAfficheTotalFacture().setText(String.valueOf(decimalFormat.format((facture.getTotalAvecTaxes()))));
        getAfficheChariteDUneFacture().setText(String.valueOf(decimalFormat.format(facture.getDonDeLaFacture())));
    }

    /**
     * Compare la factureRemboursee aux factures de la liste de factures des archives sans tenir compte du signe - des montants.
     * Lorsque trouvée, celle-ci est retirée de la la liste de factures, ajoutée à la liste de factureRemboursees et les dons
     * de la facture sont retirés du total des dons de la Charité.
     *
     * @param factureRemboursee
     */
    public boolean procederAuRemboursement(FactureAvecDons factureRemboursee) {
        Iterator<FactureAvecDons> it = archive.getFactures().iterator();
        boolean estRemboursee = false;
        while (it.hasNext()) {
            FactureAvecDons facture = it.next();
            if (facture.getNomDeLAcheteur().equals(factureRemboursee.getNomDeLAcheteur()) &&
                    Math.abs(facture.getTotalSansTaxes()) == Math.abs(factureRemboursee.getTotalSansTaxes()) &&
                    Math.abs(facture.getTaxesApplicablesAuMomentDeLAchat()) == Math.abs(factureRemboursee.getTaxesApplicablesAuMomentDeLAchat()) &&
                    facture.getModeDePaiement() == factureRemboursee.getModeDePaiement()) {
                it.remove();
                archive.ajouterUneFactureRemboursee(facture);
                retirerLeDonDeLaFactureDuTotalDesDons(facture);
                estRemboursee = true;
            }
        }
        return estRemboursee;
    }

    public void retirerLeDonDeLaFactureDuTotalDesDons(FactureAvecDons factureRemboursee) {
        charite.setTotalDesDons(charite.getTotalDesDons() - factureRemboursee.getDonDeLaFacture());
    }

    public boolean validerLeNomDeLAcheteur() {
        boolean nomValide = false;
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(getNomDeLAcheteur().getText());
        try {
            //validation sur le nom
            if (getNomDeLAcheteur().getText() == "") {
                throw new FactureException("Veuillez entrer le nom de l'acheteur");
            } else if ((getNomDeLAcheteur().getText()).length() < 2 || !matcher.matches()) {
                throw new FactureException("Le nom de l'acheteur est invalide");
            } else {
                nomValide = true;
            }
        } catch (FactureException e) {
            getMessageErreur().setText(e.getMessage());
        }

        return nomValide;
    }

    public boolean validerLesMontants() {
        boolean montantsValides = false;
        try {
            if (getTotalSansTaxes().getText() == "") {
                throw new NumberFormatException("Veuillez entrer le total sans taxe");
            } if (getTaxesApplicables().getText() == "") {
                throw new NumberFormatException("Veuillez entrer les taxes applicables");
            }else if (!getTotalSansTaxes().getText().matches("^-?[0-9]+(\\.[0-9]+)?$") || !getTaxesApplicables().getText().matches("^-?[0-9]+(\\.[0-9]+)?$")) {
                throw new FactureException("Le montant total et les taxes applicables doivent contenir uniquement des chiffres, un point et/ou un signe -.");
            } else {
                montantsValides = true;
            }
        } catch (NumberFormatException e) {
            getMessageErreur().setText(e.getMessage());
        } catch (FactureException e) {
            getMessageErreur().setText(e.getMessage());
        }

        return montantsValides;
    }

    @FXML
    void revenirALaPageAccueil(ActionEvent event) {
        //Vider les TextFields
        getNomDeLAcheteur().clear();
        getTotalSansTaxes().clear();
        getTaxesApplicables().clear();

        //Revenir à la page d'accueil
        TP2Controller.getEcran().setChangerVersCreerUneFacture(false);
        detailsFacture.setVisible(false);
        Scene sceneAccueil = TP2Controller.getMainScene();
        Stage revenirAccueil = TP2Controller.getMainStage();
        revenirAccueil.setScene(sceneAccueil);

    }

    @FXML
    void confirmerLaCreationDeLaFacture(ActionEvent event) throws IOException {
        recupererLesInformationsEntreesDansLaCreationDeLaFacture();
        detailsFacture.setVisible(true);
    }

    @FXML
    public void initialize() {
        paneErreur.setVisible(false);
        detailsFacture.setVisible(false);
        choisirUnModeDePaiement.getItems().add(ModeDePaiement.ARGENT_COMPTANT);
        choisirUnModeDePaiement.getItems().add(ModeDePaiement.CARTE_DE_DEBIT);
        choisirUnModeDePaiement.getItems().add(ModeDePaiement.CARTE_DE_CREDIT);
    }


}
