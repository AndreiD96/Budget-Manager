import java.io.*;
import java.util.*;

public class Analyzer {
    private Double balance = 0d;
    File file = new File(".//src//purchases.txt");
    FileWriter fWriter;
    Scanner scanner;


    private final String[] categories = {"Food", "Clothes", "Entertainment", "Other"};
    protected List<Item> itemsAll = new ArrayList<>();
    protected List<Item> itemsFood = new ArrayList<>();
    protected List<Item> itemsClothes = new ArrayList<>();
    protected List<Item> itemsEntertainment = new ArrayList<>();
    protected List<Item> itemsOther = new ArrayList<>();
    protected Map<String, List<Item>> categorisedItems = new LinkedHashMap<>();

    public void loadFile() {
        try {
            scanner = new Scanner(file);
            if (file.length() != 0) {
                this.balance = Double.parseDouble(scanner.nextLine());
            }
            while (scanner.hasNext()) {
                String item = scanner.nextLine();
                double price = Double.parseDouble(scanner.nextLine());
                int categoryIndex = Integer.parseInt(scanner.nextLine());

                itemsAll.add(new Item(item, price));
                if (categoryIndex == 0) {
                    itemsFood.add(new Item(item, price));
                    categorisedItems.put(categories[categoryIndex], itemsFood);
                } else if (categoryIndex == 1) {
                    itemsClothes.add(new Item(item, price));
                    categorisedItems.put(categories[categoryIndex], itemsClothes);
                } else if (categoryIndex == 2) {
                    itemsEntertainment.add(new Item(item, price));
                    categorisedItems.put(categories[categoryIndex], itemsEntertainment);
                } else if (categoryIndex == 3) {
                    itemsOther.add(new Item(item, price));
                    categorisedItems.put(categories[categoryIndex], itemsOther);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.close();
    }


    public void showItemListByCategory(int categoryIndex) {
        List<Item> temp = categorisedItems.get(categories[categoryIndex]);
        if (temp == null) {
            System.out.println("Purchase list is empty!");
        } else {
            System.out.println(categories[categoryIndex] + ":");
            for (Item item : temp) {
                System.out.println(item.getName() + " $" + String.format("%.2f", item.getPrice()));
            }
        }
    }

    public void showAllItemsList() {
        System.out.println("All:");
        if (categorisedItems.isEmpty()) {
            System.out.println("Purchase list is empty!");
        } else {
            for (String category : categories) {
                List<Item> temp = categorisedItems.get(category);
                if (temp != null) {
                    for (Item item : temp) {
                        System.out.println(item.getName() + " $" + String.format("%.2f", item.getPrice()));
                    }
                }
            }
        }
    }

    public void addIncome(double income) {
        this.balance += income;
    }

    public void addPurchase(int categoryIndex, String item, double price) {

        itemsAll.add(new Item(item, price));

        if (categoryIndex == 0) {
            itemsFood.add(new Item(item, price));
            categorisedItems.put(categories[categoryIndex], itemsFood);
        } else if (categoryIndex == 1) {
            itemsClothes.add(new Item(item, price));
            categorisedItems.put(categories[categoryIndex], itemsClothes);
        } else if (categoryIndex == 2) {
            itemsEntertainment.add(new Item(item, price));
            categorisedItems.put(categories[categoryIndex], itemsEntertainment);
        } else if (categoryIndex == 3) {
            itemsOther.add(new Item(item, price));
            categorisedItems.put(categories[categoryIndex], itemsOther);
        } else {
            System.out.println("Invalid input!");
        }
        this.balance -= price;
        if (balance < 0) {
            balance = 0d;
        }
    }

    public void saveFunction() {
        if (file.delete()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fWriter = new FileWriter(file, true);
            fWriter.write(String.format("%.2f", balance));
            fWriter.write("\r\n");
            fWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < categories.length; i++) {
            List<Item> temp = categorisedItems.get(categories[i]);
            if (temp == null) {
                continue;
            }
            for (Item item : temp) {

                String itemName = item.getName();
                double itemPrice = item.getPrice();
                try {
                    fWriter = new FileWriter(file, true);
                    fWriter.write(itemName + "\r\n");
                    fWriter.write(String.format("%.2f", itemPrice));
                    fWriter.write("\r\n");
                    fWriter.write(i + "\r\n");
                    fWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public double getTotal() {
        double total = 0;
        for (String category : categories) {
            List<Item> temp = categorisedItems.get(category);
            if (temp != null) {
                for (Item item : temp) {
                    total += item.getPrice();
                }
            }
        }
        return total;
    }

    public double getTotalByCategory(int categoryIndex) {
        List<Item> temp = categorisedItems.get(categories[categoryIndex]);
        double total = 0;
        if (temp != null) {
            for (Item item : temp) {
                total += item.getPrice();
            }
        }
        return total;
    }

    public Double getBalance() {
        return balance;
    }

    public Map<String, List<Item>> getCategorisedItems() {
        return categorisedItems;
    }

    public List<Item> getItemsAll() {
        return itemsAll;
    }

    public List<Item> getItemsFood() {
        return itemsFood;
    }

    public List<Item> getItemsClothes() {
        return itemsClothes;
    }

    public List<Item> getItemsEntertainment() {
        return itemsEntertainment;
    }

    public List<Item> getItemsOther() {
        return itemsOther;
    }
}
