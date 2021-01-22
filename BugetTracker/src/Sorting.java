import java.util.*;

public class Sorting {

    private final String[] categories = {"Food", "Clothes", "Entertainment", "Other"};

    public List<Item> sortItems(List<Item> items) {

        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = 0; j < items.size() - i - 1; j++) {
                if (items.get(j).getPrice() < items.get(j + 1).getPrice()) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
        return items;
    }

    public void showSortedItems(List<Item> items, String type) {
        if (items.isEmpty()) {

            System.out.println("Purchase list is empty");
        } else {
            System.out.println(type);
            for (Item item : items) {
                System.out.print(item.getName() + " $");
                System.out.printf("%.2f%n", item.getPrice());
            }
        }
    }

    public LinkedHashMap<String, Double> sortCategories(Map<String, List<Item>> categorisedItems) {
        Map<Double, String> categoriesWithTotal = new HashMap<>();
        LinkedHashMap<String, Double> sortedCategories = new LinkedHashMap<>();
        List<Double> totals = new ArrayList<>();


        for (String category : categories) {
            List<Item> temp = categorisedItems.get(category);
            double total = 0;
            if (temp != null) {
                for (Item item : temp) {
                    total += item.getPrice();
                }
                totals.add(total);
            }
            categoriesWithTotal.put(total, category);
        }

        for (int i = 0; i < totals.size() - 1; i++) {
            for (int j = 0; j < totals.size() - i - 1; j++) {
                if (totals.get(j) < totals.get(j + 1)) {
                    double temp = totals.get(j);
                    totals.set(j, totals.get(j + 1));
                    totals.set(j + 1, temp);
                }
            }
        }

        for (Double total : totals) {
            String category = categoriesWithTotal.get(total);
            sortedCategories.put(category, total);
        }

        return sortedCategories;
    }

    public void showSortedCategories(LinkedHashMap<String, Double> sortedCategories) {
        if (sortedCategories.isEmpty()) {
            System.out.println("Types: ");
            for (String category : this.categories) {
                System.out.println(category + " - $0");
            }
        } else {
            System.out.println("Types: ");
            Set<String> categoriesKey = sortedCategories.keySet();
            for (String category : categoriesKey) {
                System.out.println(category + " - $" + String.format("%.2f", sortedCategories.get(category)));
            }
        }
    }
}
