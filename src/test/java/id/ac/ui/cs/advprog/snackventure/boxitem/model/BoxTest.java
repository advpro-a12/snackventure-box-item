package id.ac.ui.cs.advprog.snackventure.boxitem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {

    private Box box;
    private final UUID boxId = UUID.randomUUID();
    private final String boxName = "Special Box";
    private final String boxDescription = "A limited edition box filled with syrups";
    private final String boxImageUrl = "/Box.png";
    private final int boxPrice = 50_000;
    private final String boxCountry = "Indonesia";
    private final float boxAvgRating = 0.0f;

    @BeforeEach
    public void setUp() {
        box = new Box(boxId, boxName, boxDescription, boxImageUrl, boxPrice, boxCountry, boxAvgRating);
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

        assertEquals(boxId, box.getId());
        assertEquals(boxName, box.getName());
        assertEquals(boxDescription, box.getDescription());
        assertEquals(boxImageUrl, box.getImageUrl());
        assertEquals(boxPrice, box.getPrice());
        assertEquals(boxCountry, box.getCountry());
        assertEquals(boxAvgRating, box.getAvgRating());
    }

    @Test
    void testSetBoxId() {
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
        box.setImageUrl("Malaysia");
        assertNotNull(box);
        assertNotNull(box.getCountry());
        assertNotEquals(boxCountry, box.getCountry());
    }

    @Test
    void testSetBoxAvgRating() {
        box.setAvgRating(2.5f);
        assertNotNull(box);
        assertNotNull(box.getAvgRating());
        assertNotEquals(boxAvgRating, box.getAvgRating());
    }
}
