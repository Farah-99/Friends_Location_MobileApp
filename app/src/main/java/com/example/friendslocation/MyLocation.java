package com.example.friendslocation;

public class MyLocation {
    String nom;
    String numero;
    String longitude;
    String latitude;

    public MyLocation(String nom, String numero, String longitude, String latitude) {
        this.nom = nom;
        this.numero = numero;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "MyLocation{" +
                "nom='" + nom + '\'' +
                ", numero='" + numero + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
