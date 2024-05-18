package id.ac.ui.cs.advprog.snackventure.boxitem.service;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import java.util.List;

public interface ItemService {
    public Item createItem(String name, String description, String image_url);
    public List<Item> getAllItems();
    public Item getItemById(String id);
    public Item updateItem(String id, String name, String description, String image_url);
    public Item deleteItem(String id);
}
