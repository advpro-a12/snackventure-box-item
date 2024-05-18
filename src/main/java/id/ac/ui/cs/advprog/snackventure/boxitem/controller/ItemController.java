package id.ac.ui.cs.advprog.snackventure.boxitem.controller;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import id.ac.ui.cs.advprog.snackventure.boxitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@EnableAsync
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<Item>> createItem(@RequestBody Map<String, Object> requestBody) {
        String itemName = requestBody.get("name").toString();
        String itemDescription = requestBody.get("description").toString();
        String itemImageUrl = requestBody.get("imageUrl").toString();

        Item item = itemService.createItem(itemName, itemDescription, itemImageUrl);
        return CompletableFuture.completedFuture(ResponseEntity.ok(item));
    }

    @GetMapping("/")
    public CompletableFuture<ResponseEntity<List<Item>>> listItem() {
        List<Item> items = itemService.getAllItems();
        return CompletableFuture.completedFuture(ResponseEntity.ok(items));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Item>> getItem(@PathVariable String id) {
        Item item = itemService.getItemById(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(item));
    }

    @PutMapping("/{id}/update")
    public CompletableFuture<ResponseEntity<Item>> updateItem(@PathVariable String id, @RequestBody Map<String, Object> requestBody) {
        String itemName = requestBody.get("name").toString();
        String itemDescription = requestBody.get("description").toString();
        String itemImageUrl = requestBody.get("imageUrl").toString();

        Item item = itemService.updateItem(id, itemName, itemDescription, itemImageUrl);
        return CompletableFuture.completedFuture(ResponseEntity.ok(item));
    }

    @DeleteMapping("/{id}/delete")
    public CompletableFuture<ResponseEntity<Item>> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
