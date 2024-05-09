package id.ac.ui.cs.advprog.snackventure.boxitem.model;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "box")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "box_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="box_name", updatable = false, nullable = false)
    private String name;

    @Column(name="box_description", nullable = false)
    private String description;

    @Column(name="box_image_url", nullable = false)
    private String imageUrl;

    @Column(name="box_price", nullable = false)
    private int price;

    @Column(name="box_country", nullable = false)
    private String country;

    @Column(name="box_avg_rating", nullable = false)
    private float avgRating;

    public Box() { }

    public Box(UUID boxId, String boxName, String boxDescription, String boxImageUrl,
               int boxPrice, String boxCountry, float boxAvgRating) {
        this.id = boxId;
        this.name = boxName;
        this.description = boxDescription;
        this.imageUrl = boxImageUrl;
        this.price = boxPrice;
        this.country = boxCountry;
        this.avgRating = boxAvgRating;
    }
}