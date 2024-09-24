package org.example.utils;

import org.example.models.MainDOeuvre;
import org.example.models.Materiau;

import java.util.List;

public class CostCalculator {


    public double calculateComponentCost(List<Materiau> materiaux, List<MainDOeuvre> mainDOeuvre) {
        double total = 0;


        for (Materiau materiau : materiaux) {
            total += (materiau.getCoutUnitaire() * materiau.getQuantite()) + materiau.getCoutTransport();
        }


        for (MainDOeuvre travail : mainDOeuvre) {
            total += travail.getTauxHoraire() * travail.getHeursTravail() * travail.getProductiviteOuvrier();
        }

        return total;
    }


    public double applyBeneficiaryMargin(double totalCost, double marginPercentage) {
        return totalCost + (totalCost * marginPercentage);
    }


    public double applyTaxes(double totalCost, double tvaPercentage) {
        return totalCost + (totalCost * tvaPercentage);
    }


    public double calculateFinalCost(double totalCost, double marginPercentage, double tvaPercentage) {
        double costWithMargin = applyBeneficiaryMargin(totalCost, marginPercentage);
        return applyTaxes(costWithMargin, tvaPercentage);
    }


    public double adjustCostForMaterialQuality(double totalCost, double coefficientQualite) {
        return totalCost * coefficientQualite;
    }

    public double adjustCostForLaborProductivity(double totalCost, double productivityFactor) {
        return totalCost * productivityFactor;
    }
}
