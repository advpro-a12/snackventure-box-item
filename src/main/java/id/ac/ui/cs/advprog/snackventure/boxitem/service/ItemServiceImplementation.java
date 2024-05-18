package id.ac.ui.cs.advprog.snackventure.boxitem.service;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import id.ac.ui.cs.advprog.snackventure.boxitem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImplementation implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item createItem(String itemName, String itemDescription, String itemImageUrl){
        Item item = new Item(itemName, itemDescription, itemImageUrl);
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(String id){
        UUID item_id = UUID.fromString(id);
        return itemRepository.findById(item_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id: " + id));
    }

    @Override
    public Item updateItem(String id, String name, String description, String image_url){
        UUID item_id = UUID.fromString(id);
        Item item = this.getItemById(String.valueOf(item_id));
        item.setName(name);
        item.setDescription(description);
        item.setImageUrl(image_url);
        return itemRepository.save(item);
    }

    @Override
    public Item deleteItem(String id){
        UUID item_id = UUID.fromString(id);
        Item item = this.getItemById(String.valueOf(item_id));
        itemRepository.delete(item);
        return item;
    }
}
