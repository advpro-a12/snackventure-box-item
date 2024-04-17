package id.ac.ui.cs.advprog.snackventure.boxitem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoxItemController {
    @GetMapping("/")
    public String boxItem() {
        return "Hello Admin! \nFrom BoxItem";
    }
}
