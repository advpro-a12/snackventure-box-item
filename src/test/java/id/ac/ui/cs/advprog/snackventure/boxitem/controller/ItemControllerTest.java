package id.ac.ui.cs.advprog.snackventure.boxitem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.Item;
import id.ac.ui.cs.advprog.snackventure.boxitem.service.ItemService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ItemControllerTest {
    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void testCreateItem() throws Exception {
        Item item = new Item();
        item.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        item.setName("item");
        item.setDescription("description");
        item.setImageUrl("image url");

        when(itemService.createItem(anyString(), anyString(), anyString())).thenReturn(item);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", "1f36d862-6e0b-47a3-aa17-e02ec74a0246");
        requestBody.put("name", "item");
        requestBody.put("description", "description");
        requestBody.put("imageUrl", "image url");

        MvcResult mvcResult = mockMvc.perform(post("/item/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetAllItems() throws Exception {
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        item.setName("item");
        item.setDescription("description");
        item.setImageUrl("image url");
        items.add(item);

        when(itemService.getAllItems()).thenReturn(items);

        MvcResult mvcResult = mockMvc.perform(get("/item/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetItemById() throws Exception {
        Item item = new Item();
        item.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        item.setName("item");
        item.setDescription("description");
        item.setImageUrl("image url");

        when(itemService.getItemById(anyString())).thenReturn(item);

        MvcResult mvcResult = mockMvc.perform(get("/item/1f36d862-6e0b-47a3-aa17-e02ec74a0246")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateItem() throws Exception {
        Item item = new Item();
        item.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        item.setName("item");
        item.setDescription("description");
        item.setImageUrl("image url");

        when(itemService.updateItem(anyString(), anyString(), anyString(), anyString())).thenReturn(item);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", "1f36d862-6e0b-47a3-aa17-e02ec74a0246");
        requestBody.put("name", "item");
        requestBody.put("description", "description");
        requestBody.put("imageUrl", "image url");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/item/1f36d862-6e0b-47a3-aa17-e02ec74a0246/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteItem() throws Exception {
        Item item = new Item();
        item.setId(UUID.fromString("1f36d862-6e0b-47a3-aa17-e02ec74a0246"));
        item.setName("item");
        item.setDescription("description");
        item.setImageUrl("image url");

        when(itemService.deleteItem(anyString())).thenReturn(item);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/item/1f36d862-6e0b-47a3-aa17-e02ec74a0246/delete")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
