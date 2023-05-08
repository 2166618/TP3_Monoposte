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
import org.example.model.Charite;
import org.example.model.FactureAvecDons;
import org.example.model.FactureException;
import org.example.model.ModeDePaiement;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controlleur de l'interface de la création d'une facture. Elle permet d'entrer manuellement le nom de l'acheteur, le total
 * sans taxe, les taxes applicables et le mode de paiement. Lorsque le bouton confirmer est cliqué, elle affiche le total
 * avec taxes et les dons de la facture. Finalement le bouton OK permet de revenir l'interface de la page d'accueil.
 */
public class CreerUneFactureGraphicalController {
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
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
            try {
                if (taxesApplicablesTemps >= totalSansTaxesTemp){
                    throw new FactureException("Le montant doit être supérieur aux taxes");
                }
            }catch (FactureException e){
                getMessageErreur().setText(e.getMessage());
                paneErreur.setVisible(true);
            }

            ModeDePaiement modeDePaiementTemp = getChoisirUnModeDePaiement().getValue();
            FactureAvecDons facture = new FactureAvecDons(nomDeLAcheteurTemp, totalSansTaxesTemp, taxesApplicablesTemps, modeDePaiementTemp);
            charite.ajouterUneFacture(facture);
            afficherLesDetailsDeLaFactureFinale(facture);
            charite.calculDuTotalDesDons();

        } else {
            paneErreur.setVisible(true);
        }

    }

    public boolean validerLeNomDeLAcheteur() {
        boolean nomValide = false;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(getNomDeLAcheteur().getText());
        try {
            //validation sur le nom
            if (getNomDeLAcheteur().getText() == "") {
                throw new FactureException("Veuillez rentrer le nom de l'acheteur");
            } else if ((getNomDeLAcheteur().getText()).length() < 3 || matcher.matches()) {
                throw new FactureException("Le nom de l'acheteur est invalide");
            } else {
                nomValide = true;
            }
        } catch (FactureException e) {
            getMessageErreur().setText(e.getMessage());
        }

        return nomValide;
    }

    public boolean validerLesMontants(){
        boolean montantsValides = false;
        try {
            if (getTotalSansTaxes().getText() == "" || getTaxesApplicables().getText() == ""){
                throw new NumberFormatException("Veuillez remplir tous les champs");
            }else if (!getTotalSansTaxes().getText().matches("-?\\d+(\\.\\d+)?") || !getTaxesApplicables().getText().matches("-?\\d+(\\.\\d+)?")
                        || getTotalSansTaxes().getText().matches("^[a-zA-Z]+$") || getTaxesApplicables().getText().matches("^[a-zA-Z]+$")){
                throw new FactureException("Les montants et les taxes doivent contenir uniquement des chiffres.");
            }else if (Double.parseDouble(getTotalSansTaxes().getText()) < 0 || Double.parseDouble(getTaxesApplicables().getText() )< 0){
                throw new FactureException("Les montants et les taxes doivent être positifs");
            }else{
                montantsValides = true;
            }
        } catch (NumberFormatException e) {
            getMessageErreur().setText(e.getMessage());
        } catch (FactureException e) {
            getMessageErreur().setText(e.getMessage());
        }

        return montantsValides;
    }


    public void afficherLesDetailsDeLaFactureFinale(FactureAvecDons facture) {
        getAfficheTotalFacture().setText(String.valueOf(decimalFormat.format((facture.getTotalAvecTaxes()))));
        getAfficheChariteDUneFacture().setText(String.valueOf(decimalFormat.format(facture.getDonDeLaFacture())));
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
