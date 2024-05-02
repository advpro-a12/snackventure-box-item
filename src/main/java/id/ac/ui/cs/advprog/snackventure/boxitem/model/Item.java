package id.ac.ui.cs.advprog.snackventure.boxitem.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="item_name", updatable = false, nullable = false)
    private String name;

    @Column(name="item_description", nullable = false)
    private String description;

    @Column(name="item_image_url", nullable = false)
    private String imageUrl;

    public Item() { }

    public Item(UUID itemId, String itemName, String itemDescription, String itemImageUrl) {
        this.id = itemId;
        this.name = itemName;
        this.description = itemDescription;
        this.imageUrl = itemImageUrl;
    }
}