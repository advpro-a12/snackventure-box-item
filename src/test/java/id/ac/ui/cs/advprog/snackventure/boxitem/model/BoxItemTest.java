package id.ac.ui.cs.advprog.snackventure.boxitem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BoxItemTest {

    private final UUID boxItemId = UUID.randomUUID();
    private BoxItem boxItem;
    private Box box_a;
    private Box box_b;
    private Item item_a;
    private Item item_b;
    private final int item_a_quantity = 10;
    private final int item_b_quantity = 8;

    @BeforeEach
    public void setUp() {
        item_a = new Item(
                UUID.randomUUID(),
                "SugarSnap Chocolate Bar",
                "A sweet and delicious chocolate bar",
                "/Chocolate.png"
        );
        item_b = new Item(
                UUID.randomUUID(),
                "Banana Blaster",
                "Banana flavored ice cream",
                "/banana-blaster.png"
        );
        box_a = new Box(
                UUID.randomUUID(),
                "Special Box",
                "A limited edition box filled with syrups",
                "/Box.png",
                50_000,
                "Indonesia"
        );
        box_b = new Box(
                UUID.randomUUID(),
                "Regular Box",
                "Regular box filled with candies",
                "/RegularBox.png",
                25_000,
                "Malaysia"
        );
        boxItem = new BoxItem(
                boxItemId,
                box_a,
                item_a,
                item_a_quantity
        );
        item_a.getBoxItem().add(boxItem);
        box_a.getBoxItem().add(boxItem);
    }

    @Test
    void testCreateBoxItemSuccessful() {
        assertNotNull(boxItem);
        assertNotNull(boxItem.getId());
        assertNotNull(boxItem.getBox());
        assertNotNull(boxItem.getItem());
        assertNotNull(boxItem.getItemQuantity());

        assertEquals(boxItemId, boxItem.getId());
        assertEquals(box_a.getId(), boxItem.getBox().getId());
        assertEquals(item_a.getId(), boxItem.getItem().getId());
        assertEquals(item_a_quantity, boxItem.getItemQuantity());
    }

    @Test
    void testSetBoxItemId() {
        boxItem.setId(UUID.randomUUID());
        assertNotNull(boxItem);
        assertNotNull(boxItem.getId());
        assertNotEquals(boxItemId, boxItem.getId());
    }

    @Test
    void testSetBoxItemBox() {
        boxItem.setBox(box_b);
        assertNotNull(boxItem);
        assertNotNull(boxItem.getBox());
        assertNotEquals(box_a, boxItem.getBox());
        assertEquals(box_b, boxItem.getBox());
    }

    @Test
    void testSetBoxItemItem() {
        boxItem.setItem(item_b);
        assertNotNull(boxItem);
        assertNotNull(boxItem.getItem());
        assertNotEquals(item_a, boxItem.getItem());
        assertEquals(item_b, boxItem.getItem());
    }

    @Test
    void testSetBoxItemItemQuantity() {
        boxItem.setItemQuantity(100);
        assertNotNull(boxItem);
        assertNotEquals(item_a_quantity, boxItem.getItemQuantity());
        assertEquals(100, boxItem.getItemQuantity());
    }

    @Test
    void testSetBoxAndItemOneToManySetHashSet() {
        item_a.setBoxItem(new HashSet<>());
        item_b.setBoxItem(new HashSet<>());
        box_a.setBoxItem(new HashSet<>());
        box_b.setBoxItem(new HashSet<>());

        assertNotNull(item_a.getBoxItem());
        assertNotNull(item_b.getBoxItem());
        assertNotNull(box_a.getBoxItem());
        assertNotNull(box_b.getBoxItem());
    }

    @Test
    void testSetBoxAndItemOneToManyHashSetIsEmpty() {
        assertFalse(item_a.getBoxItem().isEmpty());
        assertFalse(box_a.getBoxItem().isEmpty());
        assertTrue(item_b.getBoxItem().isEmpty());
        assertTrue(box_b.getBoxItem().isEmpty());
    }

    @Test
    void testSetBoxAndItemOneToManyHashSetContainsBoxItem() {
        assertEquals(1, item_a.getBoxItem().size());
        assertEquals(1, box_a.getBoxItem().size());
        assertTrue(item_a.getBoxItem().contains(boxItem));
        assertTrue(box_a.getBoxItem().contains(boxItem));
    }
}
