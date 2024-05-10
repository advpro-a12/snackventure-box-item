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

    public BoxItem(UUID id, Box box, Item item, int itemQuantity) {
        this.id = id;
        this.box = box;
        this.item = item;
        this.itemQuantity = itemQuantity;
    }
}
