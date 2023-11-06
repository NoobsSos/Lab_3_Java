package Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Створення п'яти продуктів
        Item[] items = new Item[5];
        items[0] = new Item("Tomato", ItemTypes.VEGETABLE, 1.99);
        items[1] = new Item("Apple", ItemTypes.FRUIT, 0.99);
        items[2] = new Item("Chicken", ItemTypes.MEAT, 4.99);
        items[3] = new Item("Salmon", ItemTypes.FISH, 6.99);
        items[4] = new Item("Milk", ItemTypes.DAIRY, 2.49);

        Files files = new Files();
//        files.saveItemsToFile("items.txt", items[2]);
//        files.saveItemsToFile("items.txt", items[2]);
//        files.saveItemsToFile("items.txt", items[2]);
        Shop shop = new Shop();
        shop.sellItem(items[2], new Bill());
        shop.addItem(items[4]);
//        shop.sellItem(items[2], new Bill());
        shop.printItem();
//        shop.sellItem(items[2], new Bill());
//        shop.sellItem(items[2], new Bill());
        ArrayList<Item> data = Files.loadItemsFromFile("items.txt");

    }

}
