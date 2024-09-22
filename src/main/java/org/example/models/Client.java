package org.example.models;

public class Client {
    private int id;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean estprofessionnel;

    public Client(String nom, String adresse, String telephone, boolean estprofessionnel) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estprofessionnel = estprofessionnel;
    }

    public Client(int id, String nom, String adresse, String telephone, boolean estprofessionnel) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estprofessionnel = estprofessionnel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isEstprofessionnel() {
        return estprofessionnel;
    }

    public void setEstprofessionnel(boolean estprofessionnel) {
        this.estprofessionnel = estprofessionnel;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + ' ' +
                ", adresse='" + adresse + ' ' +
                ", telephone='" + telephone + ' ' +
                ", estprofessionnel=" + estprofessionnel +
                '}';
    }
}
