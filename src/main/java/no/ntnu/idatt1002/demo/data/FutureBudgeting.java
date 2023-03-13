package no.ntnu.idatt1002.demo.data;

import java.util.ArrayList;

public class FutureBudgeting {
  ArrayList<String> unusedCategories = new ArrayList<>();//Disse to kategoriene beskriver valgene for første kolonne
  ArrayList<String> usedCategories = new ArrayList<>();
  int expectedExpense;//Beskriver andre kolonne, forventet utgift for kategorien
  int actualExpense;//Beskriver tredje kolonne, faktisk utgift for kategorien
  int differenceExpense;//Beskriver fjerde og siste kolonne,
  // differansen mellom forventet og faktisk utgit for kategorien

  public FutureBudgeting() {

  }

  public void initiateUnused () {
    unusedCategories.add("Choose category...");
    unusedCategories.add("Housing");
    unusedCategories.add("Groceries");
    unusedCategories.add("Entertainment");
    unusedCategories.add("Recurring/Bills");
    unusedCategories.add("Other...");
  }

  public String getUnusedCategoriesByEnumeration(int n) {
    return unusedCategories.get(n);
  }
}
