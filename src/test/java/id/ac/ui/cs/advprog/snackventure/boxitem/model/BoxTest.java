package id.ac.ui.cs.advprog.snackventure.boxitem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {

    private Box box;
    private final String boxName = "Special Box";
    private final String boxDescription = "A limited edition box filled with syrups";
    private final String boxImageUrl = "/Box.png";
    private final int boxPrice = 50_000;
    private final String boxCountry = "Indonesia";

    @BeforeEach
    public void setUp() {
        box = new Box(boxName, boxDescription, boxImageUrl, boxPrice, boxCountry);
    }

    @Test
    void testCreateBoxSuccessful() {
        assertNotNull(box);
        assertNotNull(box.getId());
        assertNotNull(box.getName());
        assertNotNull(box.getDescription());
        assertNotNull(box.getImageUrl());
        assertNotNull(box.getPrice());
        assertNotNull(box.getCountry());
        assertNotNull(box.getAvgRating());

        assertEquals(boxName, box.getName());
        assertEquals(boxDescription, box.getDescription());
        assertEquals(boxImageUrl, box.getImageUrl());
        assertEquals(boxPrice, box.getPrice());
        assertEquals(boxCountry, box.getCountry());
        assertEquals(0.0f, box.getAvgRating());
    }

    @Test
    void testSetBoxId() {
        UUID boxId = box.getId();
        box.setId(UUID.randomUUID());
        assertNotNull(box);
        assertNotNull(box.getId());
        assertNotEquals(boxId, box.getId());
    }

    @Test
    void testSetBoxName() {
        box.setName("Regular Box");
        assertNotNull(box);
        assertNotNull(box.getName());
        assertNotEquals(boxName, box.getName());
    }

    @Test
    void testSetBoxDescription() {
        box.setDescription("Regular box filled with candies");
        assertNotNull(box);
        assertNotNull(box.getDescription());
        assertNotEquals(boxDescription, box.getDescription());
    }

    @Test
    void testSetBoxImageUrl() {
        box.setImageUrl("/RegularBox.png");
        assertNotNull(box);
        assertNotNull(box.getImageUrl());
        assertNotEquals(boxImageUrl, box.getImageUrl());
    }

    @Test
    void testSetBoxPrice() {
        box.setPrice(100_000);
        assertNotNull(box);
        assertNotNull(box.getPrice());
        assertNotEquals(boxPrice, box.getPrice());
    }

    @Test
    void testSetBoxCountry() {
        box.setCountry("Malaysia");
        assertNotNull(box);
        assertNotNull(box.getCountry());
        assertNotEquals(boxCountry, box.getCountry());
    }

    @Test
    void testSetBoxAvgRating() {
        box.setAvgRating(2.5f);
        assertNotNull(box);
        assertNotNull(box.getAvgRating());
        assertNotEquals(0.0f, box.getAvgRating());
    }

    @Test
    void testPriceValidation() {
        int price = 5_000;
        box.setPrice(price);
        assertEquals(price, box.validatePrice(price));
        assertEquals(price, box.getPrice());
    }

    @Test
    void testInvalidPriceValidation() {
        int price = -10_000;
        assertThrows(IllegalArgumentException.class, () -> {
            box.setPrice(price);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            box.validatePrice(price);
        });
        assertEquals(boxPrice, box.getPrice());
    }

    @Test
    void testRatingValidation() {
        float rating = 1.0f;
        box.setAvgRating(rating);
        assertEquals(rating, box.validateRating(rating));
        assertEquals(rating, box.getAvgRating());
    }

    @Test
    void testInvalidRatingValidationTooHigh() {
        float rating = 6.0f;
        assertThrows(IllegalArgumentException.class, () -> {
            box.setAvgRating(rating);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            box.validateRating(rating);
        });
        assertEquals(0.0f, box.getAvgRating());
    }

    @Test
    void testInvalidRatingValidationTooLow() {
        float rating = -1.0f;
        assertThrows(IllegalArgumentException.class, () -> {
            box.setAvgRating(rating);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            box.validateRating(rating);
        });
        assertEquals(0.0f, box.getAvgRating());
    }
}
