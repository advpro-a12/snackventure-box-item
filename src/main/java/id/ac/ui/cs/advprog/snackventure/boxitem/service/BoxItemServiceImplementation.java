package id.ac.ui.cs.advprog.snackventure.boxitem.service;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Box;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.BoxItem;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import id.ac.ui.cs.advprog.snackventure.boxitem.repository.BoxItemRepository;
import id.ac.ui.cs.advprog.snackventure.boxitem.repository.BoxRepository;
import id.ac.ui.cs.advprog.snackventure.boxitem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoxItemServiceImplementation implements BoxItemService{

    @Autowired
    private BoxItemRepository boxItemRepository;
    @Autowired
    private BoxRepository boxRepository;
    @Autowired
    private ItemRepository itemRepository;

    public Box getBox(String id){
        UUID box_id = UUID.fromString(id);
        return this.boxRepository.findById(box_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid box Id: " + id));
    }

    public Item getItem(String id){
        UUID item_id = UUID.fromString(id);
        return this.itemRepository.findById(item_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id: " + id));
    }

    public BoxItem getBoxItem(String id){
        UUID box_item_id = UUID.fromString(id);
        return this.boxItemRepository.findById(box_item_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid box-item Id: " + id));
    }

    @Override
    public List<BoxItem> listItemsInBox(String box_item_id){
        UUID id = UUID.fromString(box_item_id);
        return boxItemRepository.findAllItemsInBox(id);
    }

    @Override
    public BoxItem addItemToBox(String box_id, String item_id, int itemQuantity){
        Box box = this.getBox(box_id);
        Item item = this.getItem(item_id);
        BoxItem boxItem = new BoxItem(box, item, itemQuantity);
        return boxItemRepository.save(boxItem);
    }

    @Override
    public BoxItem updateItemInBox(String box_item_id, String item_id, int itemQuantity){
        BoxItem boxItem = this.getBoxItem(box_item_id);
        Item item = this.getItem(item_id);
        boxItem.setItem(item);
        boxItem.setItemQuantity(itemQuantity);
        return boxItemRepository.save(boxItem);
    }

    @Override
    public BoxItem removeItemInBox(String box_item_id){
        BoxItem boxItem = getBoxItem(box_item_id);
        boxItemRepository.delete(boxItem);
        return boxItem;
    }
}
