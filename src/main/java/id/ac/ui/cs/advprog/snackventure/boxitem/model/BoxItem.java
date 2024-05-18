package id.ac.ui.cs.advprog.snackventure.boxitem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Table(name = "boxitem")
@Entity
public class BoxItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "boxitem_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "box_id", nullable = false)
    private Box box;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name="item_quantity", nullable = false)
    private int itemQuantity;

    public BoxItem() { }

    public BoxItem(Box box, Item item, int itemQuantity) {
        this.id = UUID.randomUUID();
        this.box = box;
        this.item = item;
        this.itemQuantity = validateQuantity(itemQuantity);
    }

    public int validateQuantity(int quantity){
        if (quantity < 0){
            throw new IllegalArgumentException("Invalid item Quantity, Quantity can't be negative: " + quantity);
        }
        return quantity;
    }

    public void setItemQuantity(int quantity){
        this.itemQuantity = validateQuantity(quantity);
    }
}
