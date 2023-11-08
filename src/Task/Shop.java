package Task;

import Task.Exception.BillAlreadyPaid;
import Task.Exception.ItemIsNotAvailable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Shop {
    private ArrayList<Item> availableItems;
    private ArrayList<Item> soldItems;

    public Shop() {
        this.availableItems = FileService.loadItemsFromFile("items.txt");
        this.soldItems = FileService.loadItemsFromFile("sold.txt");
    }

    public void addItem (Item item) {
        this.availableItems.add(item);

        updateFile("items.txt",true);
    }
    public void sellItem(Item item, Bill bill) throws BillAlreadyPaid, ItemIsNotAvailable {
        if (bill.isPaid()) {
            throw new BillAlreadyPaid();
        }
        if (this.availableItems.stream().anyMatch(i -> i.equals(item))) {
            bill.addItemToBill(item);
            if (item.getType().equals(ItemTypes.FRUIT) || item.getType().equals(ItemTypes.VEGETABLE)) {
                bill.addItemToBill(new Item("Pocket", ItemTypes.OTHER, 0.05));
            }
        } else {
            throw new ItemIsNotAvailable();
        }
        for (int i = 0; i < availableItems.size(); i++) {
            Item currentItem = availableItems.get(i);
            if (currentItem.equals(item)) {
                availableItems.remove(i);
                break;
            }
        }
        updateFile("items.txt", true);
        updateHistoryFile("sold.txt", item);
    }

    public void updateHistoryFile(String filePath, Item item) {
        FileService.saveItemsToFile(filePath, item, false);
    }
    public void updateFile(String filePath, boolean shouldBeDeleted) {
        for (Item item : availableItems) {
            FileService.saveItemsToFile(filePath, item, shouldBeDeleted);
            shouldBeDeleted = false;
        }
    }

    public void printItem() {
        for (Item item : this.availableItems) {
            System.out.println(item);
        }
    }

    // use stream only
    public void sortAvailableItemsByPrice() {
        // use stream only
        ArrayList<Item> sortedItems = (ArrayList<Item>) this.availableItems.stream()
                .sorted(Comparator.comparingDouble(Item::getPrice))
                .collect(Collectors.toList());

        this.availableItems = sortedItems;
    }

    public double getAveragePrice() {
        return this.availableItems.stream().mapToDouble(Item::getPrice).average().orElse(0);
    }

    public Item getMostPopularSoldItem() {
        return this.soldItems.stream()
                .collect(Collectors.groupingBy(Item::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .max((e1, e2) -> (int) (e1.getValue() - e2.getValue()))
                .map(e -> e.getKey())
                .map(name -> this.soldItems.stream().filter(i -> i.getName().equals(name)).findFirst().get())
                .orElse(null);
    }
}
