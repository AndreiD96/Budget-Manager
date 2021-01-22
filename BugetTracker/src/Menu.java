
import java.util.*;

public class Menu {
    Scanner input = new Scanner(System.in);
    Analyzer analyzer = new Analyzer();
    Sorting sorting = new Sorting();
    boolean stateLoad = false;

    public void showMenu() {
        System.out.println();
        System.out.println("Choose your action");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
    }

    public void showCategoryMenu() {
        System.out.println();
        System.out.println("Choose the type of purchase:");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
    }

    public void showAnalyzeSortMenu() {
        System.out.println();
        System.out.println("How do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) Back");
    }

    public void showSortMenuType() {
        System.out.println();
        System.out.println("Choose the type of purchases:");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
    }

    public void showPurchasesByCategoryMenu() {
        System.out.println();
        System.out.println("Choose the type of purchases:");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
    }

    public void addItemPurchase(int categoryIndex) {
        System.out.println("Enter purchase name:");
        String item = input.nextLine();
        System.out.println("Enter it's price:");
        double price = Double.parseDouble(input.nextLine());
        analyzer.addPurchase(categoryIndex, item, price);
        System.out.println("Purchase was added!");
    }

    public void menuInput(int n) {
        switch (n) {
            case 1:
                System.out.println();
                System.out.println("Enter income:");
                analyzer.addIncome(Double.parseDouble(input.nextLine()));
                System.out.println("Income was added!");

                break;
            case 2:
                showCategoryMenu();
                int x;
                do {
                    x = Integer.parseInt(input.nextLine());
                    System.out.println();
                    switch (x) {
                        case 1:
                            addItemPurchase(0);
                            System.out.println();
                            showCategoryMenu();
                            break;
                        case 2:
                            addItemPurchase(1);
                            System.out.println();
                            showCategoryMenu();
                            break;
                        case 3:
                            addItemPurchase(2);
                            System.out.println();
                            showCategoryMenu();
                            break;
                        case 4:
                            addItemPurchase(3);
                            System.out.println();
                            showCategoryMenu();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                } while (x != 5);

                break;
            case 3:
                showPurchasesByCategoryMenu();
                int y;
                do {
                    y = Integer.parseInt(input.nextLine());
                    System.out.println();
                    switch (y) {
                        case 1:
                            analyzer.showItemListByCategory(0);
                            System.out.println("Total: $" + String.format("%.2f",
                                    analyzer.getTotalByCategory(0)));
                            showPurchasesByCategoryMenu();
                            break;
                        case 2:
                            analyzer.showItemListByCategory(1);
                            System.out.println("Total: $" + String.format("%.2f",
                                    analyzer.getTotalByCategory(1)));
                            showPurchasesByCategoryMenu();
                            break;
                        case 3:
                            analyzer.showItemListByCategory(2);
                            System.out.println("Total: $" + String.format("%.2f",
                                    analyzer.getTotalByCategory(2)));
                            System.out.println();
                            showPurchasesByCategoryMenu();
                            break;
                        case 4:
                            analyzer.showItemListByCategory(3);
                            System.out.println("Total: $" + String.format("%.2f",
                                    analyzer.getTotalByCategory(3)));
                            System.out.println();
                            showPurchasesByCategoryMenu();
                            break;
                        case 5:
                            analyzer.showAllItemsList();
                            System.out.println("Total: $" + String.format("%.2f",
                                    analyzer.getTotal()));
                            System.out.println();
                            showPurchasesByCategoryMenu();
                            break;
                        case 6:
                            break;
                    }
                } while (y != 6);

                break;
            case 4:
                double balance = analyzer.getBalance();
                System.out.println();
                System.out.println("Balance: $" + String.format("%.2f", balance));
                break;
            case 5:
                System.out.println();
                analyzer.saveFunction();
                System.out.println("Saved");
                break;
            case 6:
                if (!stateLoad) {
                    analyzer.loadFile();
                    System.out.println();
                    System.out.println("Loaded");
                    stateLoad = true;
                } else {
                    System.out.println();
                    System.out.println("File already loaded");
                }
                break;
            case 7:
                showAnalyzeSortMenu();
                int z;
                do {
                    z = Integer.parseInt(input.nextLine());
                    System.out.println();
                    switch (z) {
                        case 1:
                            List<Item> itemsAllSorted
                                    = new ArrayList<>(sorting.sortItems(analyzer.getItemsAll()));
                            sorting.showSortedItems(itemsAllSorted, "All:");
                            if (!itemsAllSorted.isEmpty()) {
                                System.out.println("Total: $"
                                        + String.format("%.2f", analyzer.getTotal()));
                            }
                            System.out.println();
                            showAnalyzeSortMenu();
                            break;
                        case 2:
                            LinkedHashMap<String, Double> sortedCategories
                                    = sorting.sortCategories(analyzer.getCategorisedItems());
                            sorting.showSortedCategories(sortedCategories);
                            System.out.println();
                            showAnalyzeSortMenu();
                            break;
                        case 3:
                            showSortMenuType();
                            int xy = Integer.parseInt(input.nextLine());
                            System.out.println();
                            switch (xy) {
                                case 1:
                                    List<Item> itemsFoodSorted
                                            = new ArrayList<>(sorting.sortItems(analyzer.getItemsFood()));
                                    sorting.showSortedItems(itemsFoodSorted, "Food:");
                                    if (!itemsFoodSorted.isEmpty()) {
                                        System.out.println("Total: $"
                                                + String.format("%.2f",
                                                analyzer.getTotalByCategory(0)));
                                    }
                                    System.out.println();
                                    showAnalyzeSortMenu();
                                    break;
                                case 2:
                                    List<Item> itemsClothesSorted
                                            = new ArrayList<>(sorting.sortItems(analyzer.getItemsClothes()));
                                    sorting.showSortedItems(itemsClothesSorted, "Clothes:");
                                    if (!itemsClothesSorted.isEmpty()) {
                                        System.out.println("Total: $"
                                                + String.format("%.2f",
                                                analyzer.getTotalByCategory(1)));
                                    }
                                    System.out.println();
                                    showAnalyzeSortMenu();
                                    break;
                                case 3:
                                    List<Item> itemsEntertainmentSorted
                                            = new ArrayList<>(sorting.sortItems(analyzer.getItemsEntertainment()));
                                    sorting.showSortedItems(itemsEntertainmentSorted, "Entertainment:");
                                    if (!itemsEntertainmentSorted.isEmpty()) {
                                        System.out.println("Total: $"
                                                + String.format("%.2f",
                                                analyzer.getTotalByCategory(2)));
                                    }
                                    System.out.println();
                                    showAnalyzeSortMenu();
                                    break;
                                case 4:
                                    List<Item> itemsOtherSorted
                                            = new ArrayList<>(sorting.sortItems(analyzer.getItemsOther()));
                                    sorting.showSortedItems(itemsOtherSorted, "Other:");
                                    if (!itemsOtherSorted.isEmpty()) {
                                        System.out.println("Total: $"
                                                + String.format("%.2f",
                                                analyzer.getTotalByCategory(3)));
                                    }
                                    System.out.println();
                                    showAnalyzeSortMenu();
                                    break;
                                default:
                                    System.out.println("Invalid input!");
                                    System.out.println();
                                    showAnalyzeSortMenu();
                                    break;
                            }
                        case 4:
                            break;
                        default:
                            System.out.println("Invalid input!");
                            System.out.println();
                            showAnalyzeSortMenu();
                            break;

                    }
                } while (z != 4);
                break;
            case 0:
                System.out.println();
                System.out.println("Bye!");
                break;
            default:
                System.out.println();
                System.out.println("Invalid input!");

                break;
        }

    }
}
