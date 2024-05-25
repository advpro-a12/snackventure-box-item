package id.ac.ui.cs.advprog.snackventure.boxitem.controller;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Box;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.BoxItem;
import id.ac.ui.cs.advprog.snackventure.boxitem.service.BoxItemService;
import id.ac.ui.cs.advprog.snackventure.boxitem.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@EnableAsync
@RequestMapping("/subscription-box")
public class BoxController {

    @Autowired
    private BoxService boxService;

    @Autowired
    private BoxItemService boxItemService;

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<Box>> createBox(@RequestBody Map<String, Object> requestBody) {
        String boxName = requestBody.get("name").toString();
        String boxDescription = requestBody.get("description").toString();
        String boxImageUrl = requestBody.get("imageUrl").toString();
        int boxPrice = Integer.parseInt(requestBody.get("price").toString());
        String boxCountry = requestBody.get("country").toString();

        Box box = boxService.createBox(boxName, boxDescription, boxImageUrl, boxPrice, boxCountry);
        return CompletableFuture.completedFuture(ResponseEntity.ok(box));
    }

    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/")
    public CompletableFuture<ResponseEntity<List<Box>>> listBox() {
        List<Box> box = boxService.getAllBox();
        return CompletableFuture.completedFuture(ResponseEntity.ok(box));
    }

    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Box>> getBox(@PathVariable String id) {
        Box box = boxService.getBoxById(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(box));
    }

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping ("/{id}/update")
    public CompletableFuture<ResponseEntity<Box>> updateBox(@PathVariable String id, @RequestBody Map<String, Object> requestBody) {
        String boxName = requestBody.get("name").toString();
        String boxDescription = requestBody.get("description").toString();
        String boxImageUrl = requestBody.get("imageUrl").toString();
        int boxPrice = Integer.parseInt(requestBody.get("price").toString());
        String boxCountry = requestBody.get("country").toString();

        Box box = boxService.updateBox(id, boxName, boxDescription, boxImageUrl, boxPrice, boxCountry);
        return CompletableFuture.completedFuture(ResponseEntity.ok(box));
    }

    @Async
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PutMapping("/{id}/rate")
    public CompletableFuture<ResponseEntity<Box>> setBoxRating(@PathVariable String id, @RequestBody Map<String, Object> requestBody) {
        float boxRating = Float.parseFloat(requestBody.get("rating").toString());
        Box box = boxService.setBoxRating(id, boxRating);
        return CompletableFuture.completedFuture(ResponseEntity.ok(box));
    }

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public CompletableFuture<ResponseEntity<Box>> deleteBox(@PathVariable String id) {
        boxService.deleteBox(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}/items")
    public CompletableFuture<ResponseEntity<List<Map<String, Object>>>> listItemsInBox(@PathVariable String id) {
        List<BoxItem> items = boxItemService.listItemsInBox(id);
        List<Map<String, Object>> response = items.stream().map(obj -> {
            Map<String, Object> modifiedJson = new HashMap<>();
            modifiedJson.put("box_item_id", obj.getId());
            modifiedJson.put("item_id", obj.getItem().getId());
            modifiedJson.put("item_name", obj.getItem().getName());
            modifiedJson.put("item_description", obj.getItem().getDescription());
            modifiedJson.put("item_imageUrl", obj.getItem().getImageUrl());
            modifiedJson.put("item_quantity", obj.getItemQuantity());
            return modifiedJson;
        }).collect(Collectors.toList());

        return CompletableFuture.completedFuture(ResponseEntity.ok(response));
    }

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/add-item")
    public CompletableFuture<ResponseEntity<BoxItem>> addItemToBox(@PathVariable String id, @RequestBody Map<String, Object> requestBody) {
        String item = requestBody.get("item").toString();
        int quantity = Integer.parseInt(requestBody.get("quantity").toString());
        String box = requestBody.get("subscription_box").toString();

        BoxItem boxItem = boxItemService.addItemToBox(box, item, quantity);
        return CompletableFuture.completedFuture(ResponseEntity.ok(boxItem));
    }

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{box_id}/edit-item/{box_item_id}")
    public CompletableFuture<ResponseEntity<BoxItem>> editItemInBox(@PathVariable String box_id, @PathVariable String box_item_id, @RequestBody Map<String, Object> requestBody) {
        String item = requestBody.get("item").toString();
        int quantity = Integer.parseInt(requestBody.get("quantity").toString());

        BoxItem boxItem = boxItemService.updateItemInBox(box_item_id, item, quantity);
        return CompletableFuture.completedFuture(ResponseEntity.ok(boxItem));
    }

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{box_id}/remove-item/{box_item_id}")
    public CompletableFuture<ResponseEntity<BoxItem>> removeItemInBox(@PathVariable String box_id, @PathVariable String box_item_id) {
        boxItemService.removeItemInBox(box_item_id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
