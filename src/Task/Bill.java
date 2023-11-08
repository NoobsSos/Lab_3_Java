package Task;

import java.util.ArrayList;
import java.util.UUID;

public class Bill {
    public UUID id;
    public ArrayList<Item> items = new ArrayList<>();

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    private boolean isPaid;

    public Bill() {
        this.id = UUID.randomUUID();
        this.isPaid = false;
    }

    public void addItemToBill(Item item) {
        this.items.add(item);
    }

    public void generateBill() {
        String billName = String.format("Bill_%10s.txt", this.id.toString().substring(0, 10));
        final String[] bill = {String.format("Bill ID: %s\n", this.id)};

        bill[0] += "Items:\n";
        this.items.forEach(p -> bill[0] += p.getName() + " " + p.getPrice() + "\n");

        double total = this.items.stream().mapToDouble(Item::getPrice).sum();
        bill[0] += "Total price " + total + "\n";


        if (this.items.stream().anyMatch(p -> p.getType().equals(ItemTypes.MEAT) || p.getType().equals(ItemTypes.FISH))) {
            bill[0] += "Don`t forget to put following items in the fridge!:\n";
            this.items.stream().filter(p -> p.getType().equals(ItemTypes.MEAT) || p.getType().equals(ItemTypes.FISH)).forEach(p -> bill[0] += p.getName() + "\n");
        }

        FileService.saveTextToFile(billName, bill[0]);
        this.isPaid = true;
    }
}
