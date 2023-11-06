package Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Shop {
    private ArrayList<Item> availableItems;
    private ArrayList<Item> soldItems;

    public Shop() {
        this.availableItems = Files.loadItemsFromFile("items.txt");
        this.soldItems = Files.loadItemsFromFile("sold.txt");
    }

    public void addItem (Item item) {
        this.availableItems.add(item);

        updateFile("items.txt",true);
    }
    public void sellItem(Item item, Bill bill) {
        if (this.availableItems.stream().anyMatch(i -> i.equals(item))) {
            bill.addItemToBill(item);
            if (item.getType().equals(ItemTypes.FRUIT) || item.getType().equals(ItemTypes.VEGETABLE)) {
                bill.addItemToBill(new Item("Pocket", ItemTypes.OTHER, 0.05));
            }
        }
        for (int i = 0; i < availableItems.size(); i++) {
            Item currentItem = availableItems.get(i);
            if (currentItem.equals(item)) {
                availableItems.remove(i);
                break;
            }
        }
        updateFile("items.txt", true);
    }

    public void updateFile(String filePath, boolean shouldBeDeleted) {
        for (Item item : availableItems) {
            Files.saveItemsToFile(filePath, item, shouldBeDeleted);
            shouldBeDeleted = false;
        }
    }

    public void printItem() {
        for (Item item : this.availableItems) {
            System.out.println(item);
        }
    }


}
