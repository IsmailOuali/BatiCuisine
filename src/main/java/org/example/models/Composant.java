package org.example.models;

public class Composant {
    protected int id;
    protected String nom;
    protected String typeComposant;
    protected double tauxTVA;
    protected Projet projet;

    public Composant(String nom, String typeComposant, double tauxTVA)
    {
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
    }


    public Composant(){

    }
    public int getId()
    {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getTypeComposant()
    {
        return typeComposant;
    }

    public void setTypeComposant(String typeComposant){
        this.typeComposant = typeComposant;
    }

    public double getTauxTVA(){
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA){
        this.tauxTVA = tauxTVA;
    }

    public Projet getProjet(){
        return projet;
    }
    public void setProjet(Projet projet){
        this.projet = projet;
    }
}
