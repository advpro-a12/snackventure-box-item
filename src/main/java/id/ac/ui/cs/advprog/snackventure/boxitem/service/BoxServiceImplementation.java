package id.ac.ui.cs.advprog.snackventure.boxitem.service;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Box;
import id.ac.ui.cs.advprog.snackventure.boxitem.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoxServiceImplementation implements BoxService{

    @Autowired
    private BoxRepository boxRepository;

    @Override
    public Box createBox(String boxName, String boxDescription, String boxImageUrl, int boxPrice, String boxCountry){
        Box box = new Box(boxName, boxDescription, boxImageUrl, boxPrice, boxCountry);
        return boxRepository.save(box);
    }

    @Override
    public List<Box> getAllBox(){
        return boxRepository.findAll();
    }

    @Override
    public Box getBoxById(String id){
        UUID box_id = UUID.fromString(id);
        return boxRepository.findById(box_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid box Id: " + id));
    }

    @Override
    public Box updateBox(String id, String name, String description, String image_url, int price, String country){
        UUID box_id = UUID.fromString(id);
        Box box = this.getBoxById(String.valueOf(box_id));
        box.setName(name);
        box.setDescription(description);
        box.setImageUrl(image_url);
        box.setPrice(price);
        box.setCountry(country);
        return boxRepository.save(box);
    }

    @Override
    public Box setBoxRating(String id, float rating){
        UUID box_id = UUID.fromString(id);
        Box box = this.getBoxById(String.valueOf(box_id));
        box.setAvgRating(rating);
        return boxRepository.save(box);
    }

    @Override
    public Box deleteBox(String id){
        UUID box_id = UUID.fromString(id);
        Box box = this.getBoxById(String.valueOf(box_id));
        boxRepository.delete(box);
        return box;
    }
}
