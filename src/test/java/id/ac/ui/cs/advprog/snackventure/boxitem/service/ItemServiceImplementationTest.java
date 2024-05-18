package id.ac.ui.cs.advprog.snackventure.boxitem.service;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import id.ac.ui.cs.advprog.snackventure.boxitem.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplementationTest {

    @InjectMocks
    private ItemServiceImplementation itemService;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateItem(){
        String name = "SugarSnap Chocolate Bar";
        String description = "A sweet and delicious chocolate bar";
        String image_url = "/Chocolate.png";
        Item item = new Item(name, description, image_url);
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        Item savedItem = itemService.createItem(name, description, image_url);

        verify(itemRepository, times(1)).save(any(Item.class));
        assertEquals(item, savedItem);
    }

    @Test
    public void testGetAllItems(){
        Item item1 = new Item();
        Item item2 = new Item();
        when(itemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<Item> items = itemService.getAllItems();

        verify(itemRepository, times(1)).findAll();
        assertEquals(2, items.size());
    }

    @Test
    public void testGetItemById(){
        Item item = new Item();
        UUID id = UUID.randomUUID();
        item.setId(id);
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        Item foundItem = itemService.getItemById(id.toString());

        verify(itemRepository, times(1)).findById(id);
        assertEquals(item, foundItem);
    }

    @Test
    public void testGetItemByInvalidId(){
        Item item = new Item();
        UUID id = UUID.randomUUID();
        item.setId(id);

        UUID invalidId = UUID.randomUUID();
        when(itemRepository.findById(invalidId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> this.itemService.getItemById(invalidId.toString())
        );

        verify(itemRepository, times(1)).findById(invalidId);
        assertEquals("Invalid item Id: " + invalidId, exception.getMessage());
    }

    @Test
    public void testUpdateItem(){
        UUID id = UUID.randomUUID();
        String name = "SugarSnap Chocolate Bar";
        String description = "A sweet and delicious chocolate bar";
        String imageUrl = "/Chocolate.png";
        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setDescription(description);
        item.setImageUrl(imageUrl);

        String updatedName = "Banana Blaster";
        String updatedDescription = "A sweet and delicious chocolate bar";
        String updatedImageUrl = "/Chocolate.png";
        Item updatedItem = new Item();
        updatedItem.setName(updatedName);
        updatedItem.setDescription(updatedDescription);
        updatedItem.setImageUrl(updatedImageUrl);

        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenReturn(updatedItem);

        Item returnedItem = itemService.updateItem(id.toString(), updatedName, updatedDescription, updatedImageUrl);

        verify(itemRepository, times(1)).save(any(Item.class));
        assertEquals(returnedItem.getName(), updatedName);
        assertEquals(returnedItem.getDescription(), updatedDescription);
        assertEquals(returnedItem.getImageUrl(), updatedImageUrl);
    }

    @Test
    public void testDeleteItem(){
        UUID id = UUID.randomUUID();
        Item item = new Item();
        item.setId(id);

        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        itemService.deleteItem(id.toString());

        verify(itemRepository, times(1)).delete(any(Item.class));
        assertEquals(id, item.getId());
    }
}
