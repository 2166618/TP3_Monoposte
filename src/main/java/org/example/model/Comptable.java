package org.example.model;

/**
 *Comptable: Personne dont le travail consiste à organiser la comptabilité d'une organisation et à en assurer le bon fonctionnement.
 *
 * source: https://vitrinelinguistique.oqlf.gouv.qc.ca/fiche-gdt/fiche/8408359/comptable
 *
 * Pour ce cas, le comptable est une personne dont le travail consiste principalement à créer des factures
 */
public class Comptable {

    //attributs d'un comptable
    private String nom;

    private String prenom;

    //Constructeurs

    //constructeur par défaut
    public Comptable() {
    }
    //constructeur avec paramètre
    public Comptable(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    //Accesseurs et Mutateurs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
