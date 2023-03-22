package no.ntnu.idatt1002.demo.data;

import java.util.ArrayList;

public class FutureBudgeting {
  ArrayList<String> unusedCategories = new ArrayList<>();//Disse to kategoriene beskriver valgene for første kolonne
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
