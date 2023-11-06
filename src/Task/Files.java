package Task;

import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Files {
    public static void saveItemsToFile(String filePath, Item item, boolean shouldBeDeleted) {
        if (shouldBeDeleted) {
            File file = new File(filePath);
            file.delete();
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String line = item.getName() + "," + item.getType() + "," + item.getPrice();
            writer.write(line);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> loadItemsFromFile(String filePath) {
        ArrayList<Item> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    ItemTypes type = ItemTypes.valueOf(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    items.add(new Item(name, type, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }


    public static void removeItemFromFile(String filePath, Item itemToRemove) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            boolean removed = false;

            System.out.println("1 - " + itemToRemove);
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    ItemTypes type = ItemTypes.valueOf(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    Item currentItem = new Item(name, type, price);

                    System.out.println("2 - ");
                    if (!currentItem.equals(itemToRemove) || removed) {
                        lines.add(line);
                        System.out.println("writted");
                    }

                    if (currentItem.equals(itemToRemove)) {
                        removed = true;
                        System.out.println("status changed");
                    }
                }
            }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                for (String updatedLine : lines) {
                    writer.println(updatedLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
