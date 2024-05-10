package id.ac.ui.cs.advprog.snackventure.boxitem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "box")
    @JsonIgnore
    Set<BoxItem> boxItem = new HashSet<>();

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