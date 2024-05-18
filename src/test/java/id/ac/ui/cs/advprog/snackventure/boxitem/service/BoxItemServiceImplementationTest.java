package id.ac.ui.cs.advprog.snackventure.boxitem.service;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Box;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.BoxItem;
import id.ac.ui.cs.advprog.snackventure.boxitem.repository.BoxItemRepository;
import id.ac.ui.cs.advprog.snackventure.boxitem.repository.BoxRepository;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BoxItemServiceImplementationTest {

    @Mock
    private BoxRepository boxRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private BoxItemRepository boxItemRepository;

    @InjectMocks
    private BoxItemServiceImplementation boxItemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetItemById(){
        Item item = new Item();
        UUID id = UUID.randomUUID();
        item.setId(id);
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        Item foundItem = boxItemService.getItem(id.toString());

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
                IllegalArgumentException.class, () -> this.boxItemService.getItem(invalidId.toString())
        );

        verify(itemRepository, times(1)).findById(invalidId);
        assertEquals("Invalid item Id: " + invalidId, exception.getMessage());
    }

    @Test
    public void testGetBoxById(){
        Box box = new Box();
        UUID id = UUID.randomUUID();
        box.setId(id);
        when(boxRepository.findById(id)).thenReturn(Optional.of(box));

        Box foundBox = boxItemService.getBox(id.toString());

        verify(boxRepository, times(1)).findById(id);
        assertEquals(box, foundBox);
    }

    @Test
    public void testGetBoxByInvalidId(){
        Box box = new Box();
        UUID id = UUID.randomUUID();
        box.setId(id);

        UUID invalidId = UUID.randomUUID();
        when(boxRepository.findById(invalidId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> this.boxItemService.getBox(invalidId.toString())
        );

        verify(boxRepository, times(1)).findById(invalidId);
        assertEquals("Invalid box Id: " + invalidId, exception.getMessage());
    }

    @Test
    public void testGetBoxItemById(){
        BoxItem boxItem = new BoxItem();
        UUID id = UUID.randomUUID();
        boxItem.setId(id);
        when(boxItemRepository.findById(id)).thenReturn(Optional.of(boxItem));

        BoxItem foundBoxItem = boxItemService.getBoxItem(id.toString());

        verify(boxItemRepository, times(1)).findById(id);
        assertEquals(boxItem, foundBoxItem);
    }

    @Test
    public void testGetBoxItemByInvalidId(){
        UUID item_id = UUID.randomUUID();
        Item item = new Item();
        item.setId(item_id);

        UUID box_id = UUID.randomUUID();
        Box box = new Box();
        box.setId(box_id);

        UUID box_item_id = UUID.randomUUID();
        int item_quantity = 10;
        BoxItem boxItem = new BoxItem(box, item, item_quantity);
        boxItem.setId(box_item_id);

        UUID invalidId = UUID.randomUUID();
        when(boxItemRepository.findById(invalidId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> this.boxItemService.getBoxItem(invalidId.toString())
        );

        verify(boxItemRepository, times(1)).findById(invalidId);
        assertEquals("Invalid box-item Id: " + invalidId, exception.getMessage());
    }

    @Test
    public void testListItemsInBox(){
        Item Item1 = new Item();
        Item Item2 = new Item();
        UUID box_id = UUID.randomUUID();
        when(boxItemRepository.findAllItemsInBox(box_id)).thenReturn(Arrays.asList(Item1, Item2));

        List<Item> items = boxItemService.listItemsInBox(box_id.toString());

        verify(boxItemRepository, times(1)).findAllItemsInBox(box_id);
        assertEquals(2, items.size());
    }

    @Test
    public void testAddItemToBox(){
        UUID item_id = UUID.randomUUID();
        Item item = new Item();
        item.setId(item_id);

        UUID box_id = UUID.randomUUID();
        Box box = new Box();
        box.setId(box_id);

        UUID box_item_id = UUID.randomUUID();
        int item_quantity = 10;
        BoxItem boxItem = new BoxItem(box, item, item_quantity);
        boxItem.setId(box_item_id);

        when(itemRepository.findById(item_id)).thenReturn(Optional.of(item));
        when(boxRepository.findById(box_id)).thenReturn(Optional.of(box));
        when(boxItemRepository.save(any(BoxItem.class))).thenReturn(boxItem);

        BoxItem returnedBoxItem = boxItemService.addItemToBox(box_id.toString(), item_id.toString(), item_quantity);

        verify(itemRepository, times(1)).findById(item_id);
        verify(boxRepository, times(1)).findById(box_id);
        verify(boxItemRepository, times(1)).save(any(BoxItem.class));

        assertEquals(boxItem, returnedBoxItem);
    }

    @Test
    public void testUpdateItemInBox(){
        int item_quantity = 10;
        UUID item_id = UUID.randomUUID();
        Item item = new Item();
        item.setId(item_id);

        int new_item_quantity = 20;
        UUID new_item_id = UUID.randomUUID();
        Item new_item = new Item();
        new_item.setId(new_item_id);

        UUID box_id = UUID.randomUUID();
        Box box = new Box();
        box.setId(box_id);

        UUID box_item_id = UUID.randomUUID();
        BoxItem boxItem = new BoxItem(box, item, item_quantity);
        boxItem.setId(box_item_id);

        BoxItem updatedBoxItem = new BoxItem(box, new_item, new_item_quantity);

        when(itemRepository.findById(new_item_id)).thenReturn(Optional.of(new_item));
        when(boxItemRepository.findById(box_item_id)).thenReturn(Optional.of(boxItem));
        when(boxItemRepository.save(any(BoxItem.class))).thenReturn(updatedBoxItem);

        BoxItem returnedBoxItem = boxItemService.updateItemInBox(box_item_id.toString(), new_item_id.toString(), new_item_quantity);

        verify(itemRepository, times(1)).findById(new_item_id);
        verify(boxItemRepository, times(1)).findById(box_item_id);
        assertEquals(updatedBoxItem, returnedBoxItem);

    }

    @Test
    public void testRemoveItemInBox(){
        UUID item_id = UUID.randomUUID();
        Item item = new Item();
        item.setId(item_id);

        UUID box_id = UUID.randomUUID();
        Box box = new Box();
        box.setId(box_id);

        UUID box_item_id = UUID.randomUUID();
        int item_quantity = 10;
        BoxItem boxItem = new BoxItem(box, item, item_quantity);
        boxItem.setId(box_item_id);

        when(boxItemRepository.findById(box_item_id)).thenReturn(Optional.of(boxItem));
        BoxItem removedBoxItem = boxItemService.removeItemInBox(box_item_id.toString());

        verify(boxItemRepository, times(1)).delete(boxItem);
        assertEquals(boxItem, removedBoxItem);
    }
}
