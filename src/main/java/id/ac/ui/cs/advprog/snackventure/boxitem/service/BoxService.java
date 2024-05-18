package id.ac.ui.cs.advprog.snackventure.boxitem.service;

import id.ac.ui.cs.advprog.snackventure.boxitem.model.Box;
import id.ac.ui.cs.advprog.snackventure.boxitem.model.BoxItem;
import java.util.List;

public interface BoxService {
    public Box createBox(String name, String description, String image_url, int price, String country);
    public List<Box> getAllBox();
    public Box getBoxById(String id);
    public Box updateBox(String id, String name, String description, String image_url, int price, String country);
    public Box setBoxRating(String id, float rating);
    public Box deleteBox(String id);
}
