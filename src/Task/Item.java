package Task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Item implements Serializable {
    private int id;
    private String name;
    private ItemTypes type;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemTypes getType() {
        return type;
    }

    public void setType(ItemTypes type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getUseByDate() {
        return useByDate;
    }

    public void setUseByDate(LocalDateTime useByDate) {
        this.useByDate = useByDate;
    }

    private LocalDateTime useByDate;
    public Item(String name, ItemTypes type, double price) {
        this.id = 1;
        this.name = name;
        this.type = type;
        this.price = price;

        if (this.type == ItemTypes.VEGETABLE || this.type == ItemTypes.FRUIT) {
            this.useByDate = LocalDateTime.now().plusDays(5);
        } else if (this.type == ItemTypes.MEAT || this.type == ItemTypes.FISH) {
            this.useByDate = LocalDateTime.now().plusDays(3);
        } else if (this.type == ItemTypes.DAIRY) {
            this.useByDate = LocalDateTime.now().plusDays(7);
        } else if (this.type == ItemTypes.BAKERY) {
            this.useByDate = LocalDateTime.now().plusDays(2);
        } else if (this.type == ItemTypes.SWEETS) {
            this.useByDate = LocalDateTime.now().plusDays(30);
        } else if (this.type == ItemTypes.DRINKS) {
            this.useByDate = LocalDateTime.now().plusDays(30);
        } else {
            this.useByDate = LocalDateTime.now().plusDays(10);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item item = (Item) obj;
            return this.name.equals(item.name) && this.type.equals(item.type) && this.price == item.price;
        }
        return false;
    }

    @Override public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", useByDate=" + useByDate +
                '}';
    }
}
