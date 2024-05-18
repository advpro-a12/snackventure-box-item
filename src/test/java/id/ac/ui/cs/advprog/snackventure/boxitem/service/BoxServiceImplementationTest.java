package id.ac.ui.cs.advprog.snackventure.boxitem.service;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Box;
import id.ac.ui.cs.advprog.snackventure.boxitem.repository.BoxRepository;
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
public class BoxServiceImplementationTest {

    @InjectMocks
    private BoxServiceImplementation boxService;

    @Mock
    private BoxRepository boxRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBox(){
        String name = "SugarSnap Chocolate Bar";;
        String description = "A sweet and delicious chocolate bar";
        String image_url = "/Chocolate.png";
        int price = 50_000;
        String country = "Indonesia";
        Box box = new Box(name, description, image_url, price, country);
        when(boxRepository.save(any(Box.class))).thenReturn(box);

        Box savedBox = boxService.createBox(name, description, image_url, price, country);

        verify(boxRepository, times(1)).save(any(Box.class));
        assertEquals(box, savedBox);
    }

    @Test
    public void testGetAllBoxs(){
        Box box1 = new Box();
        Box box2 = new Box();
        when(boxRepository.findAll()).thenReturn(Arrays.asList(box1, box2));

        List<Box> box = boxService.getAllBox();

        verify(boxRepository, times(1)).findAll();
        assertEquals(2, box.size());
    }

    @Test
    public void testGetBoxById(){
        Box box = new Box();
        UUID id = UUID.randomUUID();
        box.setId(id);
        when(boxRepository.findById(id)).thenReturn(Optional.of(box));

        Box foundBox = boxService.getBoxById(id.toString());

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

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            this.boxService.getBoxById(invalidId.toString());
        });

        verify(boxRepository, times(1)).findById(invalidId);
        assertEquals("Invalid box Id: " + invalidId.toString(), exception.getMessage());
    }

    @Test
    public void testUpdateBox(){
        UUID id = UUID.randomUUID();
        String name = "SugarSnap Chocolate Bar";
        String description = "A sweet and delicious chocolate bar";
        String imageUrl = "/Chocolate.png";
        int price = 50_000;
        String country = "Indonesia";
        Box box = new Box();
        box.setId(id);
        box.setName(name);
        box.setDescription(description);
        box.setImageUrl(imageUrl);
        box.setPrice(price);
        box.setCountry(country);

        String updatedName = "Banana Blaster";
        String updatedDescription = "A sweet and delicious chocolate bar";
        String updatedImageUrl = "/Chocolate.png";
        int updatedPrice = 100_000;
        String updatedCountry = "Malaysia";
        Box updatedBox = new Box();
        updatedBox.setName(updatedName);
        updatedBox.setDescription(updatedDescription);
        updatedBox.setImageUrl(updatedImageUrl);
        updatedBox.setPrice(updatedPrice);
        updatedBox.setCountry(updatedCountry);

        when(boxRepository.findById(id)).thenReturn(Optional.of(box));
        when(boxRepository.save(any(Box.class))).thenReturn(updatedBox);

        Box returnedBox = boxService.updateBox(
                id.toString(),
                updatedName,
                updatedDescription,
                updatedImageUrl,
                updatedPrice,
                updatedCountry
        );

        verify(boxRepository, times(1)).save(any(Box.class));
        assertEquals(returnedBox.getName(), updatedName);
        assertEquals(returnedBox.getDescription(), updatedDescription);
        assertEquals(returnedBox.getImageUrl(), updatedImageUrl);
    }

    @Test
    public void testSetBoxRating(){
        UUID id = UUID.randomUUID();
        float rating = 0.0f;
        Box box = new Box();
        box.setId(id);
        box.setAvgRating(rating);

        float new_rating = 4.5f;
        Box new_box = new Box();
        new_box.setAvgRating(new_rating);

        when(boxRepository.findById(id)).thenReturn(Optional.of(box));
        when(boxRepository.save(any(Box.class))).thenReturn(new_box);
        boxService.setBoxRating(id.toString(), new_rating);

        verify(boxRepository, times(1)).save(any(Box.class));
        assertEquals(new_rating, new_box.getAvgRating());
    }

    @Test
    public void testDeleteBox(){
        UUID id = UUID.randomUUID();
        Box box = new Box();
        box.setId(id);

        when(boxRepository.findById(id)).thenReturn(Optional.of(box));
        boxService.deleteBox(id.toString());

        verify(boxRepository, times(1)).delete(any(Box.class));
        assertEquals(id, box.getId());
    }
}
