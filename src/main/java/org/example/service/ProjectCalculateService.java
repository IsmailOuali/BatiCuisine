package org.example.service;

import org.example.models.Materiau;
import org.example.models.MainDOeuvre;

import java.util.List;

public class ProjectCalculateService {

    public double calculateTotalMaterialCost(List<Materiau> materiaux, double tva) {
        double totalCost = 0;
        for (Materiau materiau : materiaux) {
            double costWithTransport = (materiau.getCoutUnitaire() * materiau.getQuantite() * materiau.getCoefficientQualite()) + materiau.getCoutTransport();
            totalCost += costWithTransport;
        }
        return totalCost * (1 + tva / 100);
    }

    public double calculateTotalLaborCost(List<MainDOeuvre> labor, double tva) {
        double totalCost = 0;
        for (MainDOeuvre mainDOeuvre : labor) {
            double laborCost = mainDOeuvre.getTauxHoraire() * mainDOeuvre.getHeursTravail() * mainDOeuvre.getProductiviteOuvrier();
            totalCost += laborCost;
        }
        return totalCost * (1 + tva / 100);
    }

    public double calculateFinalProjectCost(double totalMaterialCost, double totalLaborCost, double margeBeneficiaire) {
        double totalCost = totalMaterialCost + totalLaborCost;
        return totalCost + (totalCost * margeBeneficiaire / 100);
    }
}
