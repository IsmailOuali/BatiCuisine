package org.example.models;

public class Materiau extends Composant{

    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;

    public Materiau( String nom, double tauxTVA , double coutUnitaire, double quantite, double coefficientQualite) {
        super(nom, "Material", tauxTVA);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coefficientQualite;
        this.coefficientQualite = coefficientQualite;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }
    public void setCoutUnitaire(double coutUnitaire)
{
        this.coutUnitaire = coutUnitaire;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite){
        this.quantite = quantite;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }
}
