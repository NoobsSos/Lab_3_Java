package Task;

import java.util.ArrayList;

public class Bill {
    public ArrayList<Item> items = new ArrayList<>();
    private boolean isPaid;

    public void addItemToBill(Item item) {
        this.items.add(item);
    }
}
