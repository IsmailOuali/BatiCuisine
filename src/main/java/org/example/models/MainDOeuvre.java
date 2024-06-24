package org.example.models;

public class MainDOeuvre extends Composant{

    private double tauxHoraire;
    private double heursTravail;
    private double productiviteOuvrier;
    private Projet projet;

    public MainDOeuvre(String nom, double tauxTVA, double tauxHoraire, double heursTravail, double productiviteOuvrier, Projet projet) {
        super(nom, "MainDOeuvre", tauxTVA);
        this.tauxHoraire = tauxHoraire;
        this.heursTravail = heursTravail;
        this.productiviteOuvrier = productiviteOuvrier;
        this.projet = projet;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }
    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeursTravail() {
        return heursTravail;
    }
    public void setHeursTravail(double heursTravail) {
        this.heursTravail = heursTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }
    public void setProductiviteOuvrier(double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public Projet getProjet(){
        return projet;
    }

    public void setProjet(Projet projet){
        this.projet = projet;
    }

    public int getProjetId(){
        return projet.getId();
    }

    public void setProjetId(int projetId){
        this.projet.setId(projetId);
    }


}
