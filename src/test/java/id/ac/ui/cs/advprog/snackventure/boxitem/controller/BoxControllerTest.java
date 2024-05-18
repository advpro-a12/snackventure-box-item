package id.ac.ui.cs.advprog.snackventure.boxitem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.Box;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.BoxItem;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import id.ac.ui.cs.advprog.snackventure.boxitem.service.BoxItemService;
import id.ac.ui.cs.advprog.snackventure.boxitem.service.BoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class BoxControllerTest {
    @InjectMocks
    private BoxController boxController;

    @Mock
    private BoxService boxService;

    @Mock
    private BoxItemService boxItemService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(boxController).build();
    }

    @Test
    public void testCreateBox() throws Exception {
        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");

        when(boxService.createBox(anyString(), anyString(), anyString(), anyInt(), anyString())).thenReturn(box);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "item");
        requestBody.put("description", "description");
        requestBody.put("imageUrl", "image url");
        requestBody.put("price", String.valueOf(10_000));
        requestBody.put("country", "country");

        MvcResult mvcResult = mockMvc.perform(post("/subscription-box/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetAllBox() throws Exception {
        List<Box> box_list = new ArrayList<>();
        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");
        box_list.add(box);

        when(boxService.getAllBox()).thenReturn(box_list);

        MvcResult mvcResult = mockMvc.perform(get("/subscription-box/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetBoxById() throws Exception {
        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");

        when(boxService.getBoxById(anyString())).thenReturn(box);

        MvcResult mvcResult = mockMvc.perform(get("/subscription-box/1f36d862-6e0b-47a3-aa17-e02ec74a0246")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateBox() throws Exception {
        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");

        when(boxService.updateBox(anyString(), anyString(), anyString(), anyString(), anyInt(), anyString())).thenReturn(box);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "item");
        requestBody.put("description", "description");
        requestBody.put("imageUrl", "image url");
        requestBody.put("price", String.valueOf(10_000));
        requestBody.put("country", "country");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/subscription-box/1f36d862-6e0b-47a3-aa17-e02ec74a0246/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testRateBox() throws Exception {
        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");

        when(boxService.setBoxRating(anyString(), anyFloat())).thenReturn(box);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("rating", String.valueOf(4.5f));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/subscription-box/1f36d862-6e0b-47a3-aa17-e02ec74a0246/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteItem() throws Exception {
        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");

        when(boxService.deleteBox(anyString())).thenReturn(box);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/subscription-box/1f36d862-6e0b-47a3-aa17-e02ec74a0246/delete")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetAllItemsInBox() throws Exception {
        Item item = new Item();
        item.setId(UUID.fromString("2f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        item.setName("item");
        item.setDescription("description");
        item.setImageUrl("image url");

        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");

        List<BoxItem> boxItems = new ArrayList<>();
        BoxItem boxItem = new BoxItem(box, item, 10);
        boxItem.setId(UUID.fromString("3f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        boxItems.add(boxItem);

        when(boxItemService.listItemsInBox(anyString())).thenReturn(boxItems);

        MvcResult mvcResult = mockMvc.perform(get("/subscription-box/1f36d862-6e0b-47a3-aa17-e02ec74a0246/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testAddItemInBox() throws Exception {
        Item item = new Item();
        item.setId(UUID.fromString("2f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        item.setName("item");
        item.setDescription("description");
        item.setImageUrl("image url");

        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");

        BoxItem boxItem = new BoxItem(box, item, 10);
        boxItem.setId(UUID.fromString("3f36d862-6e0b-47a3-aa17-e02ec74a0246"));

        when(boxItemService.addItemToBox(anyString(), anyString(), anyInt())).thenReturn(boxItem);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("item", "2f36d862-6e0b-47a3-aa17-e02ec74a0246");
        requestBody.put("quantity", String.valueOf(10));
        requestBody.put("subscription_box", "1f36d862-6e0b-47a3-aa17-e02ec74a0246");


        MvcResult mvcResult = mockMvc.perform(post("/subscription-box/1f36d862-6e0b-47a3-aa17-e02ec74a0246/add-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testEditItemInBox() throws Exception {
        Item item = new Item();
        item.setId(UUID.fromString("2f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        item.setName("item");
        item.setDescription("description");
        item.setImageUrl("image url");

        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");

        BoxItem boxItem = new BoxItem(box, item, 10);
        boxItem.setId(UUID.fromString("3f36d862-6e0b-47a3-aa17-e02ec74a0246"));

        when(boxItemService.updateItemInBox(anyString(), anyString(), anyInt())).thenReturn(boxItem);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("item", "2f36d862-6e0b-47a3-aa17-e02ec74a0246");
        requestBody.put("quantity", String.valueOf(10));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(
                "/subscription-box/1f36d862-6e0b-47a3-aa17-e02ec74a0246/edit-item/3f36d862-6e0b-47a3-aa17-e02ec74a0246")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testRemoveItemInBox() throws Exception {
        Item item = new Item();
        item.setId(UUID.fromString("2f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        item.setName("item");
        item.setDescription("description");
        item.setImageUrl("image url");

        Box box = new Box();
        box.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        box.setName("box");
        box.setDescription("description");
        box.setImageUrl("image url");
        box.setPrice(10_000);
        box.setCountry("country");

        BoxItem boxItem = new BoxItem(box, item, 10);
        boxItem.setId(UUID.fromString("3f36d862-6e0b-47a3-aa17-e02ec74a0246"));

        when(boxItemService.removeItemInBox(anyString())).thenReturn(boxItem);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(
                                "/subscription-box/1f36d862-6e0b-47a3-aa17-e02ec74a0246/remove-item/3f36d862-6e0b-47a3-aa17-e02ec74a0246")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
