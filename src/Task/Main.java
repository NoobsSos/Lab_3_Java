package Task;

import Task.Exception.BillAlreadyPaid;
import Task.Exception.ItemIsNotAvailable;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, BillAlreadyPaid, ItemIsNotAvailable {
        // Створення п'яти продуктів
        Item[] items = new Item[5];
        items[0] = new Item("Tomato", ItemTypes.VEGETABLE, 1.99);
        items[1] = new Item("Apple", ItemTypes.FRUIT, 0.99);
        items[2] = new Item("Chicken", ItemTypes.MEAT, 4.99);
        items[3] = new Item("Salmon", ItemTypes.FISH, 6.99);
        items[4] = new Item("Milk", ItemTypes.DAIRY, 2.49);

        FileService fileService = new FileService();
//        files.saveItemsToFile("items.txt", items[2]);
//        files.saveItemsToFile("items.txt", items[2]);
//        files.saveItemsToFile("items.txt", items[2]);
        Bill bill = new Bill();
        Shop shop = new Shop();
        shop.sellItem(items[2], bill);
        shop.sellItem(items[0], bill);
        shop.sellItem(items[4], bill);
        shop.sellItem(items[1], bill);
        shop.addItem(items[0]);
        shop.addItem(items[1]);
        shop.addItem(items[2]);
        shop.addItem(items[4]);
        shop.sortAvailableItemsByPrice();
        System.out.println(shop.getAveragePrice());
        shop.printItem();
        System.out.println("--------------------");
        System.out.println(shop.getMostPopularSoldItem());

        bill.generateBill();
//        shop.sellItem(items[4], bill);

    }

}
