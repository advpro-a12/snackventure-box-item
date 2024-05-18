package id.ac.ui.cs.advprog.snackventure.boxitem.service;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.BoxItem;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;

import java.util.List;

public interface BoxItemService {
    public List<BoxItem> listItemsInBox(String box_id);
    public BoxItem addItemToBox(String box_id, String item_id, int item_quantity);
    public BoxItem updateItemInBox(String box_item_id, String item_id, int item_quantity);
    public BoxItem removeItemInBox(String box_item_id);
}
